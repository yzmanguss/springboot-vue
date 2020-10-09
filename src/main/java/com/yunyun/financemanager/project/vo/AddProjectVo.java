package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author yangzhongming
 * @since  2020-10-09 16:12
 */
@Data
@ApiModel("新增项目")
public class AddProjectVo {

    private Long insertBy;

    @ApiModelProperty("项目名")
    @NotNull
    private String projectName;

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
}
