package com.yunyun.financemanager.project.controller;


import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.impl.ProjectFinanceServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/ProjectFinance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectFinanceController {


    private final ProjectFinanceServiceImpl projectFinanceService;


    @ApiOperation(value = "财务项目管理查询")
    @GetMapping("/ProjectFinances")
    public ApiResponse selectFinanceProject(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam(required = false ) @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate endDate , @RequestParam(required = false) String name ,@RequestParam int pageStart ,@RequestParam int pageSize) {

        System.out.println(startDate);
        System.out.println(endDate);
        ProjectFinanceDTO projectFinanceDTO = projectFinanceService.selectFinanceProjects(startDate, endDate,name , pageStart ,pageSize);
        return ApiResponse.ok(projectFinanceDTO.getProjectFinances(), projectFinanceDTO.getTotal());
    }


}
