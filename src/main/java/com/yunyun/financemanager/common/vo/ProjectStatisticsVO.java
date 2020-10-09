package com.yunyun.financemanager.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @author zhaoqin
 */
@Getter
@Setter
@ApiModel("首页项目统计VO")
public class ProjectStatisticsVO {

    @ApiModelProperty("项目数")
    private Integer projectCount;

    @ApiModelProperty("项目总额")
    private Long projectAmount;

    @ApiModelProperty("折线图数据")
    private Collection<LineChartVO> lineChartData;

}
