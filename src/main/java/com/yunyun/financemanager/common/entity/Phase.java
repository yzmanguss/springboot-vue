package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * phase
 */
@Data
public class Phase implements Serializable {
    private Long id;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    /**
     * 序号
     */
    private Byte phaseIndex;

    /**
     * 金额-分
     */
    private Long amount;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 完成时间
     */
    private LocalDateTime finishTime;

    /**
     * 合同ID
     */
    private Long contractId;

    private static final long serialVersionUID = 1L;
}