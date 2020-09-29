package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.impl.ReimbursementServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hhr
 */
@RestController
public class ReimbursementController {

    @Autowired
    ReimbursementServiceImpl reimbursementService;

    @ApiOperation("插入报销")
    @PostMapping("/Reimbursement")
    public ApiResponse<Void> insertReimbursement(@RequestBody Reimbursement reimbursement) {
        reimbursementService.insertReimbursement(reimbursement);
        return ApiResponse.ok();
    }
}
