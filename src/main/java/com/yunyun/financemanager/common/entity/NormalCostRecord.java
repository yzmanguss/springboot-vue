package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * normal_cost_record
 */
@Data
public class NormalCostRecord implements Serializable {
    @TableId
    private Long id;

    private Long insertBy;

    private Long updateBy;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 变更前成本
     */
    private Long costLast;

    /**
     * 变更后成本
     */
    private Long costNow;

    private Long normalCostId;

    private static final long serialVersionUID = 1L;
}