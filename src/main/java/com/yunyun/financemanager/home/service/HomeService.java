package com.yunyun.financemanager.home.service;

import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.common.vo.HomeToDoVO;
import com.yunyun.financemanager.common.vo.ProjectStatisticsVO;

/**
 * @author zhaoqin
 */
public interface HomeService {

    HomeStatisticsVO statisticsCard();

    HomeToDoVO getHomeToDo();

    ProjectStatisticsVO getProjectStatisticsData(Integer type);

}
