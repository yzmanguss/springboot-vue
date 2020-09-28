package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * member
 */
@Data
public class Member implements Serializable {

    @TableId
    private Long id;

    private String memberName;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 启用状态-1是0否
     */
    private Boolean enable;

    /**
     * 日薪
     */
    private Long dailyWage;

    private static final long serialVersionUID = 1L;
}