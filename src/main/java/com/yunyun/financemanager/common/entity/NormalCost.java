package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * normal_cost
 */
@Data
public class NormalCost implements Serializable {
    @TableId
    private Long id;

    private String name;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    @TableField("`key`")
    private String key;

    /**
     * 数目-分
     */
    private Long amount;

    private static final long serialVersionUID = 1L;
}