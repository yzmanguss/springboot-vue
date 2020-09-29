package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * reimbursement_type
 * @author Administrator
 */
@Data
public class ReimbursementType implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String note;

    private static final long serialVersionUID = 1L;
}