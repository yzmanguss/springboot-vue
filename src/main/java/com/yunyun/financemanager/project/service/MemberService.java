package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;

import java.util.List;

/**
 * @author yangzhongming
 */
public interface MemberService {

    /**
     * 查询所有的成员
     * @return 返回所有的成员
     */
    ApiResponse<List<Member>> selAllMenmbers();


    /**
     *通过id查询日薪
     * @param id 成员id
     * @return   对应成员的日薪
     */
    Member selMemberDailyWageById(Long id);


}
