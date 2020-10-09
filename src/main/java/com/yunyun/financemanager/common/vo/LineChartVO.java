package com.yunyun.financemanager.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhaoqin
 */
@Getter
@Setter
@ApiModel("首页折线图VO")
public class LineChartVO {

    @ApiModelProperty("序号")
    private Integer index;

    @ApiModelProperty("值")
    private Integer value;

}
