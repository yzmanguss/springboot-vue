package com.yunyun.financemanager.home.service;

import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.common.vo.HomeToDoVO;

/**
 * @author zhaoqin
 */
public interface HomeService {

    HomeStatisticsVO statisticsCard();

    HomeToDoVO getHomeToDo();
}
