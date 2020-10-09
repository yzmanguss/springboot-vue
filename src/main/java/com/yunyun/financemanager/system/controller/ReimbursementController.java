package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.dto.ReimbursementDTO;
import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.impl.ReimbursementServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/Reimbursement", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Void> insertReimbursement(@RequestBody @Validated ReimbursementDTO reimbursementDTO) {

        Reimbursement reimbursement = new Reimbursement();
        BeanUtils.copyProperties(reimbursementDTO, reimbursement);
        reimbursementService.insertReimbursement(reimbursement, reimbursementDTO.getPhoto());

        return ApiResponse.ok();
    }
}
