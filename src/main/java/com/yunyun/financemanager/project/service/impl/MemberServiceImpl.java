package com.yunyun.financemanager.project.service.impl;

import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzhongming
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public ApiResponse<List<Member>> selAllMenmbers() {
        List<Member> members = memberMapper.selectList(null);
        Assert.notNull(members,"查询成员失败");
        if (!members.isEmpty()){
            throw new IllegalArgumentException("查询成员失败");
        }
        return ApiResponse.ok(members);
    }

    @Override
    public Member selMemberDailyWageById(Long id) {
        Member member = memberMapper.selectById(id);
        Assert.notNull(member,"成员id 不存在");
        return member;
    }

}
