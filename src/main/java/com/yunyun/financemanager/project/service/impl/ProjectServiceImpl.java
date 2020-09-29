package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.mapper.ProjectMapper;
import com.yunyun.financemanager.project.qo.ProjectNames;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import com.yunyun.financemanager.system.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨忠明
 * @date 2020-09-28 10:28
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AccountService accountService;


    @Override
    public ApiResponse getProjectList(PageLimit pageLimit) {
        List<Project> projectList = projectMapper.getProjectList(pageLimit.getPageNow(), pageLimit.getPageSize(), pageLimit.getStartDate(), pageLimit.getEndDate(), pageLimit.getState(), pageLimit.getKeyWord());
        List<ProjectVo> projectVoList = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            ProjectVo projectVo = new ProjectVo();
            String[] memberIds = project.getMembers().split(",");
            //memberID ==> member name
            StringBuilder members = new StringBuilder();
            for (int j = 0; j < memberIds.length; j++) {
                String memberName = memberMapper.selectById(memberIds[j]).getMemberName();
                members.append(memberName);
                if (j != memberIds.length - 1) {
                    members.append(",");
                }
            }
            String leaderName = memberMapper.selectById(project.getLeaderId()).getMemberName();
            //todo  合同id===合同名
            long realWorkload = project.getRequirementWorkload() + project.getDesignWorkload() + project.getDevelopWorkload() + project.getServiceWorkload() + project.getTestWorkload();
            projectVo.setId(project.getId());
            projectVo.setMembers(members.toString());
            projectVo.setLeader(leaderName);
            projectVo.setExpectedWorkload(project.getExpectedWorkload());
            projectVo.setRealWorkload(realWorkload);
            projectVoList.add(projectVo);
        }
        return ApiResponse.ok(projectVoList, (long) projectList.size());
    }

    @Override
    public ApiResponse addPeoject(Project project) {
        int insert = projectMapper.insert(project);
        project.setInsertBy(accountService.getLoginUserId());
        Assert.state(insert > 0, "添加失败");
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse deleteProject(String id) {
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


    //    模糊查询项目名
    @Override
    public List<ProjectNames> selectProjectNames(String name) {
        List<Project> projects = projectMapper.selectProjectNames(name);
        List<ProjectNames> names = new ArrayList<>();
        for (Project p : projects) {
            ProjectNames projectNames = new ProjectNames();
            projectNames.setId(p.getId());
            projectNames.setName(p.getProjectName());
            names.add(projectNames);
        }
        return names;
    }
}
