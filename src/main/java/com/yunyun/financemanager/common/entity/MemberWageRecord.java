package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * member_wage_record
 */
@Data
public class MemberWageRecord implements Serializable {

    @TableId
    private Long id;

    private Long insertBy;

    private Long updateBy;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 变更前日薪
     */
    private Long dailyWageLast;

    /**
     * 当前日薪
     */
    private Long dailyWageNow;

    /**
     * 员工ID
     */
    private Long memberId;

    private static final long serialVersionUID = 1L;
}