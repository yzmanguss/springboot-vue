package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 * reimbursement
 */
@Data
public class Reimbursement implements Serializable {

    @TableId
    @ApiModelProperty(value = "ID", name = "id")
    private Long id;

    @ApiModelProperty(value = "名字", example = "打车费")
    @NotBlank
    @Length(max = 50)
    private String name;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime insertTime;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private Long realAmount;

    /**
     * 报销金额
     */
    @ApiModelProperty(value = "报销金额", example = "2000")
    @NotNull
    @Min(1L)
    private Long reimburseAmount;

    /**
     * 凭据照片
     */
    @ApiModelProperty(value = "凭据照片，图片路径")
    @NotBlank
    private String pics;

    /**
     * 发生时间
     */
    @ApiModelProperty(value = "发生时间", name = "occurTime", example = "2156432445646")
    @NotNull
    @Past
    private LocalDateTime occurTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 相关人员
     */
    @ApiModelProperty(value = "相关人员")
    @NotNull
    private Long member;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID")
    @NotNull
    private Long projectId;

    /**
     * 报销类型ID
     */
    @ApiModelProperty(value = "报销类型ID", example = "1")
    @NotNull
    private Long reimbursementTypeId;

    private static final long serialVersionUID = 1L;
}