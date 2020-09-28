package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Account;
import com.yunyun.financemanager.common.userdetails.CustomUserDetails;
import com.yunyun.financemanager.system.mapper.AccountMapper;
import com.yunyun.financemanager.system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zhaoqin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AccountMapper accountMapper;

    @Override
    public Long getLoginUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            return userDetails.getAccount().getId();
        } else {
            return 0L;
        }
    }

    @Override
    public Account getByPhone(String phone) {
        return this.getOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getPhone, phone));
    }

    @Override
    public boolean save(Account account) {
        Account dbAccount = this.getByPhone(account.getPhone());
        Assert.isNull(dbAccount, String.format("已存在手机号为 %s 的账户", account.getPhone()));
        account.setInsertBy(this.getLoginUserId());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        System.out.println(account.getPassword());
        int insert = accountMapper.insert(account);
        return insert > 0;
    }

}
