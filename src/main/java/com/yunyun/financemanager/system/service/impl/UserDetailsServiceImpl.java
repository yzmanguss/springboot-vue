package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yunyun.financemanager.common.entity.Account;
import com.yunyun.financemanager.common.enums.RoleEnum;
import com.yunyun.financemanager.common.userdetails.CustomUserDetails;
import com.yunyun.financemanager.system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoqin
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getOne(Wrappers.<Account>lambdaQuery()
                .eq(Account::getPhone, username));
        if (account == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setAccount(account);

        RoleEnum roleEnum = RoleEnum.of(account.getType());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(roleEnum.name()));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }

}
