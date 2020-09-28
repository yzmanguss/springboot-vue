package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * reimbursement_type
 * @author Administrator
 */
@Data
public class ReimbursementType implements Serializable {

    @TableId
    @NotNull
    @Positive
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}