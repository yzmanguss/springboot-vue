package com.yunyun.financemanager.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhaoqin
 */
@Getter
@Setter
public class HomeStatisticsVO {

    /**
     * 月签订金额，单位：分
     */
    private Long mouthSignedAmount;

    /**
     * 月签订合同数量
     */
    private Long mouthSignedContractCount;

    /**
     * 月应收金额
     */
    private Long mouthAmount;

    /**
     * 月交付合同数量
     */
    private Long mouthDeliverContractCount;

}
