package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.ReimbursementType;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.mapper.ReimbursementTypeMapper;
import com.yunyun.financemanager.system.service.ReimbursementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 余聪
 * @date 2020/9/28
 */

@Validated
@RestController
public class ReimbursementTypeController {

    @Autowired
    ReimbursementTypeService reimbursementTypeService;


//    @PostMapping("/addReimbursementType")
//    public ApiResponse<String> addReimbursementType(@RequestBody @Validated ReimbursementType reimbursementType) {
//
//    }

}
