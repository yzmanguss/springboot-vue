package com.yunyun.financemanager.project.controller;


import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.impl.ProjectFinanceServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectFinanceController {


    private final ProjectFinanceServiceImpl projectFinanceService;


    @ApiOperation(value = "财务项目管理查询")
    @GetMapping("/ProjectFinance")
    public ApiResponse selectFinanceProject(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam LocalDate endDate , @RequestParam String name) {
        ProjectFinanceDTO projectFinanceDTO = projectFinanceService.selectFinanceProjects(startDate, endDate,name);
        return ApiResponse.ok(projectFinanceDTO.getProjectFinances(), projectFinanceDTO.getTotal());
    }


}
