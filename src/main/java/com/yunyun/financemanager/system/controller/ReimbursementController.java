package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.impl.ReimbursementServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hhr
 */
@Api(tags = "报销")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReimbursementController {

    private final ReimbursementServiceImpl reimbursementService;

    @ApiOperation("插入报销")
    @PostMapping(value = "/Reimbursement")
    public ApiResponse<Void> insertReimbursement(@RequestBody @Validated Reimbursement reimbursement) {
        reimbursementService.insertReimbursement(reimbursement);
        return ApiResponse.ok();
    }
}
