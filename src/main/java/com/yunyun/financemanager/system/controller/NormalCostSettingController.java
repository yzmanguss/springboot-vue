package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.NormalCost;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.NormalCostSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余聪
 * @date 2020/9/29
 */

@Api(tags = "系统管理-办公成本设置")
@Validated
@RestController
public class NormalCostSettingController {

    @Autowired
    NormalCostSettingService normalCostSettingService;

    @ApiOperation("更改办公成本")
    @PostMapping("/setAmount")
    public ApiResponse<Void> setCost(@ApiParam(value = "办公成本(amount)实体类") @Validated @RequestBody NormalCost normalCost) {
        return normalCostSettingService.setAmount(normalCost.getAmount());
    }

}
