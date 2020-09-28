package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;

/**
 * @author 杨忠明
 * @date 2020-09-28 11:14
 */
public interface MemberService {

    /**
     * 查询所有的成员
     * @return
     */
    ApiResponse<Member> selAllMenmbers();


    /**
     *通过id查询日办公成本
     * @param id
     * @return
     */
    Member selMemberDailyWageById(String id);

    /**
     * 通过memberName  查询id
     * @param memberName
     * @return
     */
    String selMemberIdByName(String memberName);

}
