package com.yunyun.financemanager.contract.controller;

import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.contract.service.PhaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/phase")
public class PhaseController {

    @Autowired
    private PhaseService phaseService;

    @ApiOperation(value = "添加回款")
    @PutMapping("/finishDate")
    public ApiResponse<Void> setFinishDate(@RequestParam Phase phase) {

        phaseService.insertPhase(phase);

        return ApiResponse.ok();
    }
}
