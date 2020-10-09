package com.yunyun.financemanager.home.controller;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.common.vo.HomeToDoVO;
import com.yunyun.financemanager.common.vo.ProjectStatisticsVO;
import com.yunyun.financemanager.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoqin
 */
@Api(tags = "首页数据接口")
@RestController
@RequestMapping("/home")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController {

    private final HomeService homeService;

    @ApiOperation("查询首页卡片统计数据")
    @GetMapping("/statistics-card")
    public ApiResponse<HomeStatisticsVO> statisticsCard() {
        HomeStatisticsVO statisticsVO = homeService.statisticsCard();
        return ApiResponse.ok(statisticsVO);
    }

    @ApiOperation("查询首页待办数据")
    @GetMapping("/to-do")
    public ApiResponse<HomeToDoVO> getHomeToDo() {
        HomeToDoVO homeToDo = homeService.getHomeToDo();
        return ApiResponse.ok(homeToDo);
    }

    @ApiOperation("获取首页项目统计数据")
    @GetMapping("/project-statistics")
    public ApiResponse<ProjectStatisticsVO> getProjectStatisticsData(@RequestParam(defaultValue = "0") Integer type) {
        ProjectStatisticsVO projectStatisticsData = homeService.getProjectStatisticsData(type);
        return ApiResponse.ok(projectStatisticsData);
    }

}
