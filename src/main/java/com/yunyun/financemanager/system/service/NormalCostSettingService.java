package com.yunyun.financemanager.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.NormalCost;
import com.yunyun.financemanager.common.response.ApiResponse;

/**
 * @author 余聪
 */
public interface NormalCostSettingService extends IService<NormalCost> {

    ApiResponse<Void> setAmount(Long amount);
}
