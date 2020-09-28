package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.system.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余聪
 * @date 2020/9/28
 */

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public ApiResponse<Void> setMemberEnable(Long id) {
        memberMapper.setMemberEnable(id);
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<Void> setMemberDisable(Long id) {
        memberMapper.setMemberDisable(id);
        return ApiResponse.ok();
    }

}
