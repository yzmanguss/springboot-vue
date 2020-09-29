package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    @NotNull
    @Positive
    @DecimalMax(value = "100000")
    private Long amount;

    private static final long serialVersionUID = 1L;
}