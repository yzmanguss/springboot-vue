package com.yunyun.financemanager.project.controller;

import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.qo.ProjectNames;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨忠明
 * @date 2020-09-28 14:41
 */
@Api(tags = "项目管理")
@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation("查询项目列表")
    @GetMapping("/queryProject")
    public ApiResponse queryProject(@Validated PageLimit pageLimit){
        return projectService.getProjectList(pageLimit);
    }


    @ApiOperation("新增项目")
    @PostMapping("/addProject")
    public ApiResponse addPeoject(Project project){
        return projectService.addPeoject(project);
    }

    @ApiOperation("删除项目")
    @GetMapping("/deleteProject")
    public ApiResponse deleteProject(String id){
        return projectService.deleteProject(id);
    }


    @ApiOperation("模糊查询项目的名字")
    @GetMapping("/projectNames")
    public ApiResponse<List<ProjectNames>> selectProjectNames(@RequestParam(value = "name") String name) {
        return ApiResponse.ok(projectService.selectProjectNames(name));
    }

    @ApiOperation("查询财务项目列表")
    @GetMapping("/queryFinanceProject")
    public ApiResponse<ProjectVo> queryFinanceProject(@Validated PageLimit pageLimit){



        return projectService.getProjectList(pageLimit);
    }

}
