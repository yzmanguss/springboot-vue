package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.Account;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoqin
 */
@Api(tags = "账户管理")
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @ApiOperation("添加账户")
    @PostMapping
    public ApiResponse<Void> save(@RequestBody @Validated Account account) {
        accountService.save(account);
        return ApiResponse.ok();
    }

}
