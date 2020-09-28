package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 杨忠明
 * @date 2020-09-28 11:16
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public ApiResponse<Member> selAllMenmbers() {
        return null;
    }

    @Override
    public Member selMemberDailyWageById(String id) {
        return null;
    }

    @Override
    public String selMemberIdByName(String memberName) {
        return null;
    }
}
