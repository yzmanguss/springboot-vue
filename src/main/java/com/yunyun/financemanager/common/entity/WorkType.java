package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}