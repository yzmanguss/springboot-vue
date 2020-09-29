package com.yunyun.financemanager.contract.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yunyun.financemanager.contract.service.PhaseService;
import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.contract.mapper.PhaseMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xlc
 */
@Service
public class PhaseServiceImpl extends ServiceImpl<PhaseMapper, Phase> implements PhaseService {
    @Override
    public Long getAmountByStartDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Phase> phases = this.list(Wrappers.<Phase>lambdaQuery()
                .between(Phase::getStartDate, startDate, endDate));
        return phases.stream()
                .map(Phase::getAmount)
                .reduce(0L, Long::sum);
    }
}
