package com.yunyun.financemanager.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.Phase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author xlc
 */
@Service
public interface PhaseService extends IService<Phase> {
    /**
     * 获取时间范围内应收款金额
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 金额
     */
    Long getAmountByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
