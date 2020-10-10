package com.yunyun.financemanager.project.controller;

import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author yangzhongming
 */
@Api(tags = "工作量")
@RestController
@CrossOrigin
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @ApiOperation("查询所有成员")
    @GetMapping("/selAllMembers")
    public ApiResponse<List<Member>> getProjectDetail(){
        return memberService.selAllMenmbers();
    }
}
