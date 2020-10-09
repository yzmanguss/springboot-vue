package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * work_type
 */
@Data
public class WorkType implements Serializable {
    @TableId
    private Long id;

    private String name;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime insertTime;

    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}