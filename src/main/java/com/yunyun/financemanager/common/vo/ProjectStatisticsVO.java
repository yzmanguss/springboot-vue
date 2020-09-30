package com.yunyun.financemanager.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author zhaoqin
 */
@Getter
@Setter
@Schema(name = "首页项目统计VO")
public class ProjectStatisticsVO {

    @Schema(name = "项目数")
    private Integer projectCount;

    @Schema(name = "项目总额")
    private Long projectAmount;

    @Schema(name = "折线图数据")
    private Collection<LineChartVO> lineChartData;

}
