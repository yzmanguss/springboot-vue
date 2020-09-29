package com.yunyun.financemanager.home.service.impl;

import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.contract.service.ContractService;
import com.yunyun.financemanager.contract.service.PhaseService;
import com.yunyun.financemanager.home.service.HomeService;
import com.yunyun.financemanager.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author zhaoqin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeServiceImpl implements HomeService {

    private final ProjectService projectService;

    private final ContractService contractService;

    private final PhaseService phaseService;

    @Override
    public HomeStatisticsVO statistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        Long contractSignedAmount = contractService.getContractSignedAmount(firstDayOfMonth, lastDayOfMonth);
        Long signedContractCount = contractService.getSignedContractCount(firstDayOfMonth, lastDayOfMonth);
        Long amount = phaseService.getAmountByStartDateBetween(firstDayOfMonth, lastDayOfMonth);
        Long deliverProjectCount = projectService.getDeliverProjectCount(firstDayOfMonth, lastDayOfMonth);

        HomeStatisticsVO homeStatisticsVO = new HomeStatisticsVO();
        homeStatisticsVO.setMouthSignedAmount(contractSignedAmount);
        homeStatisticsVO.setMouthSignedContractCount(signedContractCount);
        homeStatisticsVO.setMouthAmount(amount);
        homeStatisticsVO.setMouthDeliverContractCount(deliverProjectCount);

        return homeStatisticsVO;
    }

}
