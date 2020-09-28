package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * work_load
 */
@Data
public class WorkLoad implements Serializable {

    @TableId
    private Long id;

    private Long insertBy;

    private Long updateBy;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 完成时间
     */
    private LocalDateTime finishDate;

    /**
     * 工作量-根据时间自动计算
     */
    private Long workLoad;

    /**
     * 员工ID
     */
    private Long memberId;

    /**
     * 类型ID
     */
    private Long workTypeId;

    /**
     * 员工日薪-分
     */
    private Long dailyWage;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 日办公成本-分
     */
    private Long dailyOfficeCost;

    private static final long serialVersionUID = 1L;
}