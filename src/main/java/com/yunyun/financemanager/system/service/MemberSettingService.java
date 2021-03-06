package com.yunyun.financemanager.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.Member;
import com.yunyun.financemanager.common.response.ApiResponse;

/**
 * @author 余聪
 */
public interface MemberSettingService extends IService<Member> {

    /**
     * 设置公司人员为启用
     * @param id 人员id
     */
    ApiResponse<Void> setMemberEnable(Long id);

    /**
     * 设置公司人员为禁用
     * @param id 人员id
     */
    ApiResponse<Void> setMemberDisable(Long id);

}
