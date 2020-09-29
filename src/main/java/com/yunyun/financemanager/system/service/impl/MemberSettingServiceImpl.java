package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.system.service.MemberSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余聪
 * @date 2020/9/28
 */

@Service
public class MemberSettingServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberSettingService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public ApiResponse<Void> setMemberEnable(Long id) {
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("enable", 1);
        memberMapper.update(null, updateWrapper);
        return ApiResponse.ok();
    }

    @Override
    public ApiResponse<Void> setMemberDisable(Long id) {
        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).set("enable", 0);
        memberMapper.update(null, updateWrapper);
        return ApiResponse.ok();
    }

}
