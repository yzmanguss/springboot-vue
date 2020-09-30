package com.yunyun.financemanager.home.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.common.vo.HomeToDoVO;
import com.yunyun.financemanager.contract.service.ContractService;
import com.yunyun.financemanager.contract.service.PhaseService;
import com.yunyun.financemanager.home.service.HomeService;
import com.yunyun.financemanager.project.service.ProjectService;
import com.yunyun.financemanager.project.service.WorkLoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaoqin
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeServiceImpl implements HomeService {

    private final ProjectService projectService;

    private final ContractService contractService;

    private final PhaseService phaseService;

    private final WorkLoadService workLoadService;

    @Override
    public HomeStatisticsVO statisticsCard() {
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

    @Override
    public HomeToDoVO getHomeToDo() {
        Long lastWeekInputWorkLoad = this.getLastWeekInputWorkLoad();
        Integer overdueProjectCount = this.getOverdueProjectCount();
        Integer warningCount = this.getWarningCount();

        HomeToDoVO homeToDoVO = new HomeToDoVO();
        homeToDoVO.setLastWeekInputWorkLoad(lastWeekInputWorkLoad);
        homeToDoVO.setOverdueProjectCount(overdueProjectCount);
        homeToDoVO.setWarningProjectCount(warningCount);

        return homeToDoVO;
    }

    private Integer getWarningCount() {
        AtomicInteger workLoadWarning = new AtomicInteger();
        AtomicInteger costWarning = new AtomicInteger();
        // 获取所有项目
        List<Project> projects = projectService.list();
        projects.forEach(project -> {
            Long projectId = project.getId();
            List<WorkLoad> workLoads = workLoadService.list(Wrappers.<WorkLoad>lambdaQuery()
                    .eq(WorkLoad::getProjectId, projectId));
            Long workLoadSum = workLoads.stream()
                    .map(WorkLoad::getWorkLoad)
                    .reduce(0L, Long::sum);
            if (project.getExpectedWorkload() > workLoadSum) {
                workLoadWarning.incrementAndGet();
            }

            Long expectCost = project.getExpectedBusinessCost() + project.getExpectedDevelopCost();
            Long realCost = workLoads.stream()
                    .map(workLoad -> workLoad.getWorkLoad() * workLoad.getDailyWage()
                            + workLoad.getWorkLoad() * workLoad.getDailyOfficeCost())
                    .reduce(0L, Long::sum);
            if (realCost > expectCost) {
                costWarning.incrementAndGet();
            }
        });
        return workLoadWarning.get() + costWarning.get();
    }

    private Long getLastWeekInputWorkLoad() {
        // 上周的今天
        LocalDate sameDayLastWeek = LocalDate.now().minusWeeks(1);
        LocalDate mondayLastWeek = sameDayLastWeek.with(DayOfWeek.MONDAY);
        LocalDate sundayLastWeek = sameDayLastWeek.with(DayOfWeek.SUNDAY);

        List<WorkLoad> workLoads = workLoadService.list(Wrappers.<WorkLoad>lambdaQuery()
                .between(WorkLoad::getInsertTime, mondayLastWeek, sundayLastWeek));

        return workLoads.stream()
                .map(WorkLoad::getWorkLoad)
                .reduce(0L, Long::sum);
    }

    private Integer getOverdueProjectCount() {
        return projectService.count(Wrappers.<Project>lambdaQuery()
                .isNotNull(Project::getDeliverDate)
                .lt(Project::getExpectedFinishDate, LocalDateTime.now()));
    }

}
