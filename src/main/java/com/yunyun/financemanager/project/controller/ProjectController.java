package com.yunyun.financemanager.project.controller;

import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzhongming
 * @date 2020-09-28 14:41
 */
@Api(tags = "项目管理")
@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @ApiOperation("查询项目列表")
    @GetMapping("/queryProject")
    public ApiResponse<List<ProjectVo>> queryProject(@Validated PageLimit pageLimit){
        return projectService.getProjectList(pageLimit);
    }

    @ApiOperation("新增项目")
    @PostMapping("/addProject")
    public ApiResponse<Void> addPeoject(Project project){
        return projectService.addPeoject(project);
    }


    @ApiOperation("删除项目")
    @DeleteMapping("/deleteProject/{id}")
    public ApiResponse<Void> deleteProject(@PathVariable String id){
        return projectService.deleteProject(id);
    }

    @ApiOperation("项目详情")
    @GetMapping("/getProjectDetail/{id}")
    public ApiResponse<ProjectVo> getProjectDetail(@PathVariable String id){
        return projectService.getProjectDetail(id);
    }



}
