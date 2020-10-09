package com.yunyun.financemanager.project.controller;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.WorkLoadService;
import com.yunyun.financemanager.project.service.WorkTypeService;
import com.yunyun.financemanager.project.vo.WorkTypeVo;
import com.yunyun.financemanager.project.vo.WorkloadVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzhongming
 */
@Api(tags = "工作量")
@RestController
@CrossOrigin
@RequestMapping("/workload")
public class WorkLoadController {

    @Resource
    private WorkTypeService workTypeService;

    @Resource
    private WorkLoadService workLoadService;

    @ApiOperation("查询所有的工作类型")
    @GetMapping("/queryAllWorkType")
    public ApiResponse<List<WorkTypeVo>> queryAllWorkType() {
        return workTypeService.queryAllWorkType();
    }

    @ApiOperation("添加工作量")
    @PostMapping("/addWorkLoad")
    public ApiResponse<Void> addWorkLoad(@RequestBody WorkloadVo workloadVo) {
        return workLoadService.addWorkLoad(workloadVo);
    }
}
