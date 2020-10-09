package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * project
 */
@Data
@ApiModel("项目")
public class Project implements Serializable {

    @TableId
    private Long id;

    @ApiModelProperty("项目名")
    @NotNull
    private String projectName;

    private Long insertBy;

    private Long updateBy;


    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime insertTime;

    @TableField(insertStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;

    /**
     * 关联合同
     */
    @NotNull
    @ApiModelProperty("关联合同id")
    private Long contractId;

    /**
     * 负责人
     */
    @NotNull
    @ApiModelProperty("负责人id")
    private Long leaderId;

    /**
     * 参与人员
     */
    @NotNull
    @ApiModelProperty("参与人员（以,分隔）")
    private String members;

    /**
     * 签订日期
     */
    @NotNull
    @ApiModelProperty("签订时间")
    private LocalDate signDate;

    /**
     * 计划开始时间
     */
    @NotNull
    @ApiModelProperty("计划开始时间")
    private LocalDate expectedStartDate;

    /**
     * 计划完成时间
     */
    @NotNull
    @ApiModelProperty("计划完成时间")
    private LocalDate expectedFinishDate;

    /**
     * 计划总工作量-单位:人天
     */
    @NotNull
    @ApiModelProperty("计划总工作量-单位:人天")
    private Long expectedWorkload;

    /**
     * 计划需求分析节点时间
     */
    @NotNull
    @ApiModelProperty("计划需求分析节点时间")
    private LocalDate expectedRequirementNodeDate;

    /**
     * 计划设计节点时间
     */
    @NotNull
    @ApiModelProperty("计划设计节点时间")
    private LocalDate expectedDesignNodeDate;

    /**
     * 计划开发节点时间
     */
    @NotNull
    @ApiModelProperty("计划开发节点时间")
    private LocalDate expectedDevelopNodeDate;

    /**
     * 计划测试节点时间
     */
    @NotNull
    @ApiModelProperty("计划测试节点时间")
    private LocalDate expectedTestNodeDate;

    /**
     * 计划开发成本
     */
    @NotNull
    @ApiModelProperty("计划开发成本")
    private Long expectedDevelopCost;

    /**
     * 计划商务成本
     */
    @NotNull
    @ApiModelProperty("计划商务成本")
    private Long expectedBusinessCost;

    /**
     * 计划维护节点-一般是交付一年后
     */
    @ApiModelProperty("计划维护节点-一般是交付一年后")
    private LocalDate expectedServiceNodeDate;

    /**
     * 需求分析工作
     */
    @ApiModelProperty("需求分析工作")
    private Long requirementWorkload;

    /**
     * 设计工作量
     */
    @ApiModelProperty("设计工作量")
    private Long designWorkload;

    /**
     * 开发工作量
     */
    @ApiModelProperty("开发工作量")
    private Long developWorkload;

    /**
     * 测试工作量
     */
    @ApiModelProperty("测试工作量")
    private Long testWorkload;

    /**
     * 维护工作量
     */
    @ApiModelProperty("维护工作量")
    private Long serviceWorkload;

    /**
     * 需求分析完成节点
     */
    @ApiModelProperty("需求分析完成节点")
    private LocalDate requirementNodeDate;

    /**
     * 设计完成节点
     */
    @ApiModelProperty("设计完成节点")
    private LocalDate designNodeDate;

    /**
     * 开发完成节点
     */
    @ApiModelProperty("开发完成节点")
    private LocalDate developNodeDate;

    /**
     * 测试完成节点
     */
    @ApiModelProperty("测试完成节点")
    private LocalDate testNodeDate;

    /**
     * 维护完成节点
     */
    @ApiModelProperty("维护完成节点")
    private LocalDate serviceNodeDate;

    /**
     * 交付时间
     */
    @ApiModelProperty("交付时间")
    private LocalDate deliverDate;

    /**
     * 回款项
     */
    private String phaseId;

    private static final long serialVersionUID = 1L;
}