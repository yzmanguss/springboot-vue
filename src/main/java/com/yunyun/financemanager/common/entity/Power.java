package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * power
 */
@Data
public class Power implements Serializable {

    @TableId
    private Long id;

    private String powerName;

    private Long insertBy;

    private Long updateBy;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}