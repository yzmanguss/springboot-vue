package com.yunyun.financemanager.home.controller;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.vo.HomeStatisticsVO;
import com.yunyun.financemanager.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @ApiOperation("查询首页统计数据")
    @GetMapping("/statistics")
    public ApiResponse<HomeStatisticsVO> test() {
        HomeStatisticsVO statisticsVO = homeService.statistics();
        return ApiResponse.ok(statisticsVO);
    }

}
