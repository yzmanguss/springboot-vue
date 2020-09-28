package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Account;
import com.yunyun.financemanager.system.mapper.AccountMapper;
import com.yunyun.financemanager.system.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author zhaoqin
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
