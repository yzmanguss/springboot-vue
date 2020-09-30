package com.yunyun.financemanager.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.NormalCost;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.mapper.NormalCostMapper;
import com.yunyun.financemanager.system.service.NormalCostSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 余聪
 */
@Service
public class NormalCostSettingServiceImpl extends ServiceImpl<NormalCostMapper, NormalCost> implements NormalCostSettingService {

    @Autowired
    NormalCostMapper normalCostMapper;

    @Override
    public ApiResponse<Void> setAmount(Long amount) {
        UpdateWrapper<NormalCost> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("amount", amount);
        normalCostMapper.update(null, updateWrapper);
        return ApiResponse.ok();
    }
}
