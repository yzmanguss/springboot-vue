package com.yunyun.financemanager.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 首页代办事项VO
 *
 * @author zhaoqin
 */
@Getter
@Setter
@ApiModel("首页代办事项VO")
public class HomeToDoVO {

    @ApiModelProperty("上周录入工作量")
    private Long lastWeekInputWorkLoad;

    @ApiModelProperty("超期项目数")
    private Integer overdueProjectCount;

    @ApiModelProperty("预警项目数")
    private Integer warningProjectCount;

}
