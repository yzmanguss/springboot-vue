package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.contract.service.PhaseService;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.mapper.ProjectMapper;
import com.yunyun.financemanager.project.service.EarlyWarningService;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.service.WorkLoadService;
import com.yunyun.financemanager.project.utils.ProjectUtils;
import com.yunyun.financemanager.project.vo.AddProjectVo;
import com.yunyun.financemanager.project.vo.ContractVo;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import com.yunyun.financemanager.system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhongming
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private EarlyWarningService earlyWarningService;

    private final PhaseService phaseService;

    private final WorkLoadService workLoadService;

    @Override
    public ApiResponse<List<ProjectVo>> getProjectList(PageLimit pageLimit) {
        System.out.println(pageLimit);
        Integer pageStart = (pageLimit.getPageNow() - 1) * pageLimit.getPageSize();
        String keyWord = pageLimit.getKeyWord();
        if ("".equals(keyWord)) {
            keyWord = null;
        }
        List<Project> projectList = projectMapper.getProjectList(pageStart, pageLimit.getPageSize(), pageLimit.getStartDate(), pageLimit.getEndDate(), pageLimit.getState(), keyWord);
        List<ProjectVo> projectVoList = new ArrayList<>();
        long size = projectMapper.getProjectListCount(pageStart, pageLimit.getPageSize(), pageLimit.getStartDate(), pageLimit.getEndDate(), pageLimit.getState(), keyWord);
        for (Project project : projectList) {
            ProjectVo projectVo = new ProjectVo();
            String[] memberIds = project.getMembers().split(",");
            projectVo.setContract(contractMapper.selectById(project.getContractId()).getContractName());
            String members = ProjectUtils.memberIdToname(memberIds, memberMapper);
            String leaderName = memberMapper.selectById(project.getLeaderId()).getMemberName();
            long realWorkload = project.getRequirementWorkload() + project.getDesignWorkload() + project.getDevelopWorkload() + project.getServiceWorkload() + project.getTestWorkload();
            projectVo.setId(project.getId());
            projectVo.setProjectName(project.getProjectName());
            projectVo.setMembers(members);
            projectVo.setLeader(leaderName);
            projectVo.setExpectedWorkload(project.getExpectedWorkload());
            projectVo.setRealWorkload(realWorkload);
            projectVo.setEarlyWarning(earlyWarningService.selectEarlyWarning(project.getId(),project.getContractId()));
            projectVoList.add(projectVo);

        }
        return ApiResponse.ok(projectVoList, size);
    }

    @Override
    public ApiResponse<Void> addProject(AddProjectVo project) {
        Project project1 = new Project();
        project1.setProjectName(project.getProjectName());
        project1.setContractId(project.getContractId());
        project1.setLeaderId(project.getLeaderId());
        project1.setMembers(project.getMembers());
        Contract contract = contractMapper.selectById(project.getContractId());
        Assert.notNull(contract, "合同id不存在");
        project1.setSignDate(contract.getSignDate());
        project1.setExpectedStartDate(project.getExpectedStartDate());
        project1.setExpectedFinishDate(project.getExpectedFinishDate());
        project1.setExpectedWorkload(project.getExpectedWorkload());
        project1.setExpectedRequirementNodeDate(project.getExpectedRequirementNodeDate());
        project1.setExpectedDesignNodeDate(project.getExpectedDesignNodeDate());
        project1.setExpectedDevelopNodeDate(project.getExpectedDevelopNodeDate());
        project1.setExpectedTestNodeDate(project.getExpectedTestNodeDate());
        project1.setExpectedDevelopCost(project.getExpectedDevelopCost());
        project1.setExpectedBusinessCost(project.getExpectedBusinessCost());
        project1.setInsertBy(accountService.getLoginUserId());
        int insert = projectMapper.insert(project1);
        Assert.isTrue(insert > 0, "添加失败");
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<ProjectVo> getProjectDetail(Long id) {
        Project project = projectMapper.selectById(id);
        Assert.notNull(project, "未找到项目，id：" + id);
        ProjectVo projectVo = new ProjectVo();
        BeanUtils.copyProperties(project, projectVo);

        List<Phase> phases = phaseService.list(Wrappers.<Phase>lambdaQuery()
                .eq(Phase::getContractId, project.getContractId()));
        projectVo.setPhases(phases);

        List<WorkLoad> workLoads = workLoadService.list(Wrappers.<WorkLoad>lambdaQuery()
                .eq(WorkLoad::getProjectId, project.getId()));
        Long workloadSum = workLoads.stream()
                .map(WorkLoad::getWorkLoad)
                .reduce((long) 0, Long::sum);
        projectVo.setRealWorkload(workloadSum);

        return ApiResponse.ok(projectVo);
    }

    @Override
    public ApiResponse<Void> conclusionProject(Project project) {
        project.setUpdateBy(accountService.getLoginUserId());
        int i = projectMapper.updateById(project);
        Assert.isTrue(i > 0, "结项失败");
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<Void> deleteProject(String id) {
        int delete = projectMapper.deleteById(id);
        Assert.isTrue(delete > 0, "删除失败");
        return ApiResponse.ok();
    }

    @Override
    public Long getDeliverProjectCount(LocalDate startDate, LocalDate endDate) {
        int count = this.count(Wrappers.<Project>lambdaQuery()
                .between(Project::getDeliverDate, startDate, endDate));
        return (long) count;
    }

    @Override
    public ApiResponse<List<ContractVo>> getContractNamelike() {
        List<ContractVo> contractNamelikeLimit = contractMapper.getContractNamelikeLimit();
        Assert.notNull(contractNamelikeLimit, "合同查询失败");
        Assert.notEmpty(contractNamelikeLimit, "合同查询失败");
        long size = contractNamelikeLimit.size();
        return ApiResponse.ok(contractNamelikeLimit, size);
    }

    @Override
    public ApiResponse<List<ContractVo>> getContractNamelike(String keyWord) {
        List<ContractVo> contractNamelike = contractMapper.getContractNamelikeByName(keyWord);
        Assert.notNull(contractNamelike, "合同查询失败");
        Assert.notEmpty(contractNamelike, "合同查询失败");
        long size = contractNamelike.size();
        return ApiResponse.ok(contractNamelike, size);
    }

    @Override
    public List<Project> listBySignDateBetween(LocalDate startDate, LocalDate endDate) {
        return this.list(Wrappers.<Project>lambdaQuery()
                .between(Project::getSignDate, startDate, endDate));
    }

    @Override
    public boolean updateProject(ProjectVo projectVo) {
        Project project = new Project();
        BeanUtils.copyProperties(projectVo, project);
        project.setUpdateBy(accountService.getLoginUserId());
        int insert = projectMapper.updateById(project);
        List<Phase> phases = projectVo.getPhases();
        phaseService.remove(Wrappers.<Phase>lambdaQuery()
                .eq(Phase::getContractId, project.getContractId()));
        boolean save = phaseService.saveBatch(phases);
        return insert > 0 && save;
    }
}
