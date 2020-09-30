package com.yunyun.financemanager.project.service.impl;

import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzhongming
 * @date 2020-09-28 11:16
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public ApiResponse<List<Member>> selAllMenmbers() {
        List<Member> members = memberMapper.selectList(null);
        Assert.state(members !=null && !members.isEmpty(),"查询成员失败");
        return ApiResponse.ok(members);
    }

    @Override
    public Member selMemberDailyWageById(Long id) {
        Member member = memberMapper.selectById(id);
        Assert.state(member != null,"成员id 不存在");
        return member;
    }

}
