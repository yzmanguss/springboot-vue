package com.yunyun.financemanager.system.service;

import com.yunyun.financemanager.common.entity.ReimbursementType;
import com.yunyun.financemanager.common.response.ApiResponse;

/**
 * @author 余聪
 */
public interface ReimbursementTypeService {

    /**
     * 添加报销类型
     * @param reimbursementType 报销类型实体
     */
    ApiResponse<Void> addReimbursementType(ReimbursementType reimbursementType);
}
