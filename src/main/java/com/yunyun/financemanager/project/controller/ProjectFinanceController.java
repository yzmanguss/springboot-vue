package com.yunyun.financemanager.project.controller;


import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.impl.ProjectFinanceServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponse selectFinanceProject(@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate, @RequestParam(required = false) String keyWord, @RequestParam Integer pageNow, @RequestParam Integer pageSize) {
        ProjectFinanceDTO projectFinanceDTO = projectFinanceService.selectFinanceProjects(startDate, endDate, keyWord, (pageNow*pageSize)-pageSize, pageSize);
        return ApiResponse.ok(projectFinanceDTO.getProjectFinances(), projectFinanceDTO.getTotal());
    }


}
