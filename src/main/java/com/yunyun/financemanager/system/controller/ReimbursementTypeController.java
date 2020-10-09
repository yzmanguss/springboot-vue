package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.ReimbursementType;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.ReimbursementTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余聪
 */
@Api(tags = "系统管理-报销类型管理")
@Validated
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReimbursementTypeController {

    private final ReimbursementTypeService reimbursementTypeService;

    @ApiOperation("新增报销类型")
    @PostMapping("/addReimbursementType")
    public ApiResponse<Void> addReimbursementType(@ApiParam(name = "reimbursementType", value = "新增的报销类型名称(name)封装类")
                                                      @RequestBody @Validated ReimbursementType reimbursementType) {
        return reimbursementTypeService.addReimbursementType(reimbursementType);
    }

    @ApiOperation("查询所有报销类型")
    @GetMapping("/reimbursement-types")
    public ApiResponse<List<ReimbursementType>> list() {
        List<ReimbursementType> reimbursementTypes = reimbursementTypeService.list();
        return ApiResponse.ok(reimbursementTypes);
    }

}
