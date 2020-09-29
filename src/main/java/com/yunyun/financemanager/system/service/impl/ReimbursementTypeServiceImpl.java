package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyun.financemanager.common.entity.ReimbursementType;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.response.ResponseCode;
import com.yunyun.financemanager.system.mapper.ReimbursementTypeMapper;
import com.yunyun.financemanager.system.service.ReimbursementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余聪
 * @date 2020/9/29
 */

@Service
public class ReimbursementTypeServiceImpl implements ReimbursementTypeService {

    @Autowired
    ReimbursementTypeMapper reimbursementTypeMapper;

    @Override
    public ApiResponse<Void> addReimbursementType(ReimbursementType reimbursementType) {
        QueryWrapper<ReimbursementType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", reimbursementType.getName());
        if (reimbursementTypeMapper.selectOne(queryWrapper) != null) {
            return ApiResponse.failure(ResponseCode.BAD_REQUEST, "报销类型名称已存在！");
        } else {
            reimbursementTypeMapper.insert(reimbursementType);
            return ApiResponse.ok();
        }
    }
}
