package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * contract
 */
@Data
public class Contract implements Serializable {

    @TableId
    private Long id;

    /**
     * 合同名称
     */
    private String contractName;

    private Long insertBy;

    private Long updateBy;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 合同编号
     */
    private String contractNumber;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 总金额-分
     */
    private Long amount;

    /**
     * 签订时间
     */
    private LocalDate signDate;

    /**
     * 开始时间
     */
    private LocalDate startDate;

    /**
     * 完成时间
     */
    private LocalDate finishDate;

    /**
     * 合同状态
     */
    private Boolean contractStatus;

    /**
     * 联系人姓名
     */
    private String customerContactName;

    /**
     * 联系人电话
     */
    private String customerContactPhone;

    private static final long serialVersionUID = 1L;
}