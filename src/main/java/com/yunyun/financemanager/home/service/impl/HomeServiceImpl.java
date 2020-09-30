package com.yunyun.financemanager.home.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.common.vo.HomeToDoVO;
import com.yunyun.financemanager.common.vo.LineChartVO;
import com.yunyun.financemanager.common.vo.ProjectStatisticsVO;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
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

    @Override
    public ProjectStatisticsVO getProjectStatisticsData(Integer type) {
        if (type.equals(0)) {
            return getYearProjectStatistics();
        } else if (type.equals(1)) {
            return getMonthProjectStatistics();
        } else {
            throw new IllegalArgumentException("Wrong Type: " + type);
        }
    }

    private ProjectStatisticsVO getMonthProjectStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        int projectCountOfMonth = CountProjectBySignDateBetween(firstDayOfMonth, lastDayOfMonth);

        List<Contract> contracts = listContractsBySignDateBetween(firstDayOfMonth, lastDayOfMonth);

        Long amount = contracts.stream()
                .map(Contract::getAmount)
                .reduce(0L, Long::sum);

        Map<Integer, LineChartVO> lineChartMap = contractService.getMonthAmountGroupByDay(today.getYear(), today.getMonthValue());

        Collection<LineChartVO> lineChartData = getLineChartData(lineChartMap, today.getDayOfMonth());

        ProjectStatisticsVO projectStatisticsVO = new ProjectStatisticsVO();
        projectStatisticsVO.setProjectCount(projectCountOfMonth);
        projectStatisticsVO.setProjectAmount(amount);
        projectStatisticsVO.setLineChartData(lineChartData);

        return projectStatisticsVO;
    }

    private int CountProjectBySignDateBetween(LocalDate startDate, LocalDate endDate) {
        return projectService.count(Wrappers.<Project>lambdaQuery()
                .between(Project::getSignDate, startDate, endDate));
    }

    private ProjectStatisticsVO getYearProjectStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfYear = today.with(TemporalAdjusters.firstDayOfYear());
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        // 获取今年签订的项目
        int projectCount = CountProjectBySignDateBetween(firstDayOfYear, lastDayOfYear);

        // 获取今年签订的合同
        List<Contract> contracts = listContractsBySignDateBetween(firstDayOfYear, lastDayOfYear);

        Long amount = contracts.stream()
                .map(Contract::getAmount)
                .reduce(0L, Long::sum);

        Map<Integer, LineChartVO> lineChartMap = contractService.getYearAmountGroupByMonth(today.getYear());
        int monthValue = today.getMonthValue();

        Collection<LineChartVO> lineChartData = getLineChartData(lineChartMap, monthValue);

        ProjectStatisticsVO projectStatisticsVO = new ProjectStatisticsVO();
        projectStatisticsVO.setProjectCount(projectCount);
        projectStatisticsVO.setProjectAmount(amount);
        projectStatisticsVO.setLineChartData(lineChartData);

        return projectStatisticsVO;
    }

    private Collection<LineChartVO> getLineChartData(Map<Integer, LineChartVO> lineChartMap, int max) {
        for (int i = 1; i <= max; i++) {
            if (!lineChartMap.containsKey(i)) {
                LineChartVO lineChartVO = new LineChartVO();
                lineChartVO.setIndex(i);
                lineChartVO.setValue(0);
                lineChartMap.put(i, lineChartVO);
            }
        }

        Collection<LineChartVO> lineChartData = lineChartMap.values();
        return lineChartData;
    }

    private List<Contract> listContractsBySignDateBetween(LocalDate startDate, LocalDate endDate) {
        return contractService.list(Wrappers.<Contract>lambdaQuery()
                .between(Contract::getSignDate, startDate, endDate));
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
