package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.mapper.ProjectMapper;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.utils.ProjectUtils;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import com.yunyun.financemanager.system.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangzhongming
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private ContractMapper contractMapper;

    @Override
    public ApiResponse<List<ProjectVo>> getProjectList(PageLimit pageLimit) {
        Integer pageStart =  (pageLimit.getPageNow()-1)*pageLimit.getPageSize();
        List<Project> projectList = projectMapper.getProjectList(pageStart, pageLimit.getPageSize(), pageLimit.getStartDate(), pageLimit.getEndDate(), pageLimit.getState(), pageLimit.getKeyWord());
        List<ProjectVo> projectVoList = new ArrayList<>();
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
            projectVoList.add(projectVo);
        }
        return ApiResponse.ok(projectVoList, (long) projectList.size());
    }

    @Override
    public ApiResponse<Void> addProject(Project project) {
        project.setInsertBy(accountService.getLoginUserId());
        int insert = projectMapper.insert(project);
        Assert.state(insert > 0, "添加失败");
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<ProjectVo> getProjectDetail(String id) {
        Project project = projectMapper.selectById(id);
        Assert.state(project !=null,"找不到对应的项目");
        System.out.println(project);
        ProjectVo projectVo = new ProjectVo();
        projectVo.setId(project.getId());
        projectVo.setProjectName(project.getProjectName());
        Contract contract = contractMapper.selectById(project.getContractId());
        System.out.println(contract);
        Assert.state(contract!=null,"找不到关联合同");
        projectVo.setContract(contract.getContractName());
        String leaderName = memberMapper.selectById(project.getLeaderId()).getMemberName();
        projectVo.setLeader(leaderName);
        String[] memberIds = project.getMembers().split(",");
        String members = ProjectUtils.memberIdToname(memberIds, memberMapper);
        projectVo.setMembers(members);
        projectVo.setSignDate(project.getSignDate());
        projectVo.setExpectedStartDate(project.getExpectedStartDate());
        projectVo.setExpectedFinishDate(project.getExpectedFinishDate());
        projectVo.setExpectedWorkload(project.getExpectedWorkload());
        projectVo.setExpectedRequirementNodeDate(project.getExpectedRequirementNodeDate());
        projectVo.setExpectedDesignNodeDate(project.getExpectedDesignNodeDate());
        projectVo.setExpectedDevelopNodeDate(project.getExpectedDevelopNodeDate());
        projectVo.setExpectedTestNodeDate(project.getExpectedTestNodeDate());
        projectVo.setExpectedServiceNodeDate(project.getExpectedServiceNodeDate());
        projectVo.setExpectedDevelopCost(project.getExpectedDevelopCost());
        projectVo.setExpectedBusinessCost(project.getExpectedBusinessCost());
        long realWorkload = project.getRequirementWorkload() + project.getDesignWorkload() + project.getDevelopWorkload() + project.getServiceWorkload() + project.getTestWorkload();
        projectVo.setRealWorkload(realWorkload);
        projectVo.setRequirementNodeDate(project.getRequirementNodeDate());
        projectVo.setDesignNodeDate(project.getDesignNodeDate());
        projectVo.setDevelopNodeDate(project.getDevelopNodeDate());
        projectVo.setTestNodeDate(project.getTestNodeDate());
        projectVo.setServiceNodeDate(project.getServiceNodeDate());
        return ApiResponse.ok(projectVo);
    }

    @Override
    public ApiResponse<Void> conclusionProject(Project project) {
        project.setUpdateBy(accountService.getLoginUserId());
        int i = projectMapper.updateById(project);
        Assert.state(i > 0,"结项失败");
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<Void> deleteProject(String id) {
        int delete = projectMapper.deleteById(id);
        Assert.state(delete > 0, "删除失败");
        return ApiResponse.ok();
    }

    @Override
    public Long getDeliverProjectCount(LocalDate startDate, LocalDate endDate) {
        int count = this.count(Wrappers.<Project>lambdaQuery()
                .between(Project::getDeliverDate, startDate, endDate));
        return (long) count;
    }

    @Override
    public List<Project> listBySignDateBetween(LocalDate startDate, LocalDate endDate) {
        return this.list(Wrappers.<Project>lambdaQuery()
                .between(Project::getSignDate, startDate, endDate));
    }
}
