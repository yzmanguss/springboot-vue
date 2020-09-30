package com.yunyun.financemanager.project.controller;



import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.qo.ProjectFinance;
import com.yunyun.financemanager.project.service.impl.ProjectFinanceServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProjectFinanceController {



    @Autowired
    private ProjectFinanceServiceImpl projectFinanceService;


    @ApiOperation(value = "财务项目管理")
    @GetMapping("/ProjectFinance")
    public ApiResponse selectFinanceProject(@RequestParam int start){
        List<ProjectFinance> projectFinances = projectFinanceService.selectFinanceProjects(start);
        int size = projectFinanceService.selectCount();
        Map<String,Object> map = new HashMap<>();
        map.put("projectFinances",projectFinances);
        map.put("size",size);
       return ApiResponse.ok(map);
    }




}
