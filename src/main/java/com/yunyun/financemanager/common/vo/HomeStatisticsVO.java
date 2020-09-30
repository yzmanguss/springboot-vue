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
@ApiModel("首页卡片数据VO")
public class HomeStatisticsVO {

    /**
     * 月签订金额，单位：分
     */
    @ApiModelProperty("月签订金额，单位：分")
    private Long mouthSignedAmount;

    /**
     * 月签订合同数量
     */
    @ApiModelProperty("月签订合同数量")
    private Long mouthSignedContractCount;

    /**
     * 月应收金额
     */
    @ApiModelProperty("月应收金额，单位：分")
    private Long mouthAmount;

    /**
     * 月交付合同数量
     */
    @ApiModelProperty("月交付合同数量")
    private Long mouthDeliverContractCount;

}
