package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * reimbursement
 */
@Data
public class Reimbursement implements Serializable {

    @TableId
    private Long id;

    private String name;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 实际金额
     */
    private Long realAmount;

    /**
     * 报销金额
     */
    private Long reimburseAmount;

    /**
     * 凭据照片
     */
    private Long pics;

    /**
     * 发生时间
     */
    private LocalDateTime occurTime;

    /**
     * 备注
     */
    private String note;

    /**
     * 相关人员
     */
    private Long members;

    /**
     * 项目ID
     */
    private Long projectId;

    /**
     * 类型ID
     */
    private Long reimbursementTypeId;

    private static final long serialVersionUID = 1L;
}