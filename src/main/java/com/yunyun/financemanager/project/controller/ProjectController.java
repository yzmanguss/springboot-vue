package com.yunyun.financemanager.project.controller;

import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.qo.ProjectNames;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.vo.AddProjectVo;
import com.yunyun.financemanager.project.vo.ContractVo;
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
    public ApiResponse<List<ProjectVo>> queryProject(@Validated @RequestBody PageLimit pageLimit) {
        return projectService.getProjectList(pageLimit);
    }

    @ApiOperation("新增项目")
    @PostMapping("/addProject")
    public ApiResponse<Void> addProject(@Validated @RequestBody AddProjectVo project){
        return projectService.addProject(project);
    }

    @ApiOperation("删除项目")
    @DeleteMapping("/deleteProject/{id}")
    public ApiResponse<Void> deleteProject(@PathVariable String id) {
        return projectService.deleteProject(id);
    }

    @ApiOperation("项目详情")
    @GetMapping("/getProjectDetail/{id}")
    public ApiResponse<ProjectVo> getProjectDetail(@PathVariable String id) {
        return projectService.getProjectDetail(id);
    }

    @ApiOperation("模糊查询项目的名字")
    @GetMapping("/projectNames")
    public ApiResponse<List<ProjectNames>> selectProjectNames(@RequestParam(value = "name") String name) {
//        return ApiResponse.ok(projectService.selectProjectNames(name));
        return null;
    }

    @ApiOperation("结项")
    @PostMapping("/conclusionProject")
    public ApiResponse<Void> conclusionProject(@RequestBody Project project) {
        return projectService.conclusionProject(project);
    }

    @ApiOperation("根据关键字模糊查詢合同id和name")
    @GetMapping("/getContractNamelikeByName")
    public ApiResponse<List<ContractVo>> getContractNamelikeByName(String keyWord) {
        return projectService.getContractNamelike(keyWord);
    }

    @ApiOperation("查詢前10条 合同id和name")
    @GetMapping("/getContractNamelikeLimit")
    public ApiResponse<List<ContractVo>> getContractNamelikeLimit() {
        return projectService.getContractNamelike();
    }

    @ApiOperation("查询财务项目列表")
    @GetMapping("/queryFinanceProject")
    public ApiResponse<ProjectVo> queryFinanceProject(@Validated PageLimit pageLimit) {

//        return projectService.getProjectList(pageLimit);
        return null;
    }

}
