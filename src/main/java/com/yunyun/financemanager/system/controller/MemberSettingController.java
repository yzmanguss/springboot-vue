package com.yunyun.financemanager.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.response.ResponseCode;
import com.yunyun.financemanager.common.vojo.MemberStateVO;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.system.service.MemberSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 余聪
 * @date 2020/9/28
 */

@Validated
@RestController
public class MemberSettingController {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MemberSettingService memberSettingService;

    /**
     * 公司人员设置页面的人员信息列表
     * @param pageNum 当前页
     * @param pageSize 每页数量
     * @param keyword 查询关键字（姓名或日薪）
     * @return
     */
    @GetMapping("/members")
    public ApiResponse<List<Member>> listMembers(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                                 String keyword) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("member_name", keyword)
                    .or()
                    .like("daily_wage", keyword);
        }
        queryWrapper.orderByAsc("id");
        Page<Member> page = memberMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return ApiResponse.ok(page.getRecords(), page.getTotal());
    }

    /**
     * 更改人员状态
     * @param memberStateVO 人员id和状态码包装类
     * @return
     */
    @PostMapping("/change_member_state")
    public ApiResponse<Void> changeMemberState(@RequestBody MemberStateVO memberStateVO) {
        switch (memberStateVO.getState()) {
            case 0:
                return memberSettingService.setMemberDisable(memberStateVO.getId());
            case 1:
                return memberSettingService.setMemberEnable(memberStateVO.getId());
            default:
                return ApiResponse.failure(ResponseCode.FORBIDDEN, "人员状态码错误！");
        }
    }

    /**
     * 添加公司人员
     * @param memberDTO 人员信息对象
     * @return
     */
    @PutMapping("/add_member")
    public ApiResponse<Void> addMember(@RequestBody @Validated Member memberDTO) {
        memberSettingService.save(memberDTO);
        return ApiResponse.ok();
    }

}
