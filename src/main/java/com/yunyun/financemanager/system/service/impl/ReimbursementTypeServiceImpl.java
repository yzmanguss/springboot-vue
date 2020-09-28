package com.yunyun.financemanager.system.service.impl;

import com.yunyun.financemanager.common.entity.ReimbursementType;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.mapper.ReimbursementTypeMapper;
import com.yunyun.financemanager.system.service.ReimbursementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余聪
 * @date 2020/9/28
 */

@Service
public class ReimbursementTypeServiceImpl implements ReimbursementTypeService {

    @Autowired
    ReimbursementTypeMapper reimbursementTypeMapper;

    @Override
    public ApiResponse<String> addReimbursementType(ReimbursementType reimbursementType) {



        return null;
    }
}
