package com.yunyun.financemanager.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.response.ResponseCode;
import com.yunyun.financemanager.common.vojo.MemberStateVO;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.system.service.MemberSettingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 余聪
 */
@Api(tags = "系统管理-公司人员录入")
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
     */
    @ApiOperation("人员列表")
    @GetMapping("/members")
    public ApiResponse<List<Member>> listMembers(@ApiParam(value = "当前页码数") @RequestParam(defaultValue = "1") Integer pageNum,
                                                 @ApiParam(value = "每页数据条数") @RequestParam(defaultValue = "5") Integer pageSize,
                                                 @ApiParam(value = "模糊查询参数") String keyword) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("member_name", keyword)
                    .or()
                    .like("daily_wage", keyword);
        }
        queryWrapper.orderByAsc("id");
        Page<Member> page = memberMapper.selectPage(new Page<>(pageNum, pageSize, true), queryWrapper);
        return ApiResponse.ok(page.getRecords(), (long) page.getRecords().size());
    }

    /**
     * 更改人员状态
     * @param memberStateVO 人员id和状态码包装类
     */
    @ApiOperation("更改人员状态")
    @PostMapping("/change_member_state")
    public ApiResponse<Void> changeMemberState(@ApiParam(value = "人员id和状态值(0/1)的封装") @RequestBody MemberStateVO memberStateVO) {
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
     */
    @ApiOperation("添加人员")
    @PutMapping("/add_member")
    public ApiResponse<Void> addMember(@ApiParam(value = "人员姓名、日薪、状态值(0/1)的封装") @RequestBody @Validated Member memberDTO) {
        memberSettingService.save(memberDTO);
        return ApiResponse.ok();
    }

}
