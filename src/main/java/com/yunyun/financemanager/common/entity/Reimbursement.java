package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * reimbursement
 */
@Data
public class Reimbursement implements Serializable {

    @TableId
    @ApiModelProperty(value = "ID", name = "id")
    private Long id;

    @ApiModelProperty(value = "名字", name = "name")
    private String name;


    private LocalDateTime insertTime;

    private LocalDateTime updateTime;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额", name = "realAmount")
    private Long realAmount;

    /**
     * 报销金额
     */
    @ApiModelProperty(value = "报销金额", name = "reimburseAmount")
    private Long reimburseAmount;

    /**
     * 凭据照片
     */
    @ApiModelProperty(value = "凭据照片", name = "pics;")
    private String pics;

    /**
     * 发生时间
     */
    @ApiModelProperty(value = "发生时间", name = "occurTime")
    private LocalDateTime occurTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "note")
    private String note;

    /**
     * 相关人员
     */
    @ApiModelProperty(value = "相关人员", name = "members")
    private Long members;

    /**
     * 项目ID
     */
    @ApiModelProperty(value = "项目ID", name = "projectId")
    private Long projectId;

    /**
     * 类型ID
     */
    @ApiModelProperty(value = "类型ID", name = "reimbursementTypeId")
    private Long reimbursementTypeId;

    private static final long serialVersionUID = 1L;
}