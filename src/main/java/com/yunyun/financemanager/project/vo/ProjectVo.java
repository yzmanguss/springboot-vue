package com.yunyun.financemanager.project.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDate;

/**
 * @author yangzhongming
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "项目对象")
public class ProjectVo {

    @ApiModelProperty(value = "项目id")
    private Long id;

    @ApiModelProperty(value = "项目名")
    private String projectName;
    /**
     * 关联合同
     */
    @ApiModelProperty(value = "合同名")
    private String  contract;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人姓名")
    private String  leader;

    /**
     * 参与人员
     */
    @ApiModelProperty(value = "参与人员名")
    private String members;

    /**
     * 签订日期
     */

    @ApiModelProperty(value = "项目签订时间")
    private LocalDate signDate;

    /**
     * 计划开始时间
     */
    @ApiModelProperty(value = "计划开始时间")
    private LocalDate expectedStartDate;

    /**
     * 计划完成时间
     */
    @ApiModelProperty(value = "计划完成时间")
    private LocalDate expectedFinishDate;

    /**
     * 计划总工作量-单位:人天
     */
    @ApiModelProperty(value = "计划工作量")
    private Long expectedWorkload;

    /**
     * 计划需求分析节点时间
     */
    @ApiModelProperty(value = "计划需求分析时间节点")
    private LocalDate expectedRequirementNodeDate;

    /**
     * 计划设计节点时间
     */
    @ApiModelProperty(value = "计划设计时间节点")
    private LocalDate expectedDesignNodeDate;

    /**
     * 计划开发节点时间
     */
    @ApiModelProperty(value = "计划开发设计节点")
    private LocalDate expectedDevelopNodeDate;

    /**
     * 计划测试节点时间
     */
    @ApiModelProperty(value = "计划测试时间")
    private LocalDate expectedTestNodeDate;

    /**
     * 计划开发成本
     */
    @ApiModelProperty(value = "计划开发成本")
    private Long expectedDevelopCost;

    /**
     * 计划商务成本
     */
    @ApiModelProperty(value = "计划商务成本")
    private Long expectedBusinessCost;

    /**
     * 计划维护节点-一般是交付一年后
     */
    @ApiModelProperty(value = "计划维护时间节点")
    private LocalDate expectedServiceNodeDate;

    /**
     * 实际工作量
     */
    @ApiModelProperty(value = "实际工作量")
    private Long realWorkload;

    /**
     * 需求分析完成节点
     */
    @ApiModelProperty(value = "需求分析完成节点")
    private LocalDate requirementNodeDate;

    /**
     * 设计完成节点
     */
    @ApiModelProperty(value = "设计完成节点")
    private LocalDate designNodeDate;

    /**
     * 开发完成节点
     */
    @ApiModelProperty(value = "开发完成节点")
    private LocalDate developNodeDate;

    /**
     * 测试完成节点
     */
    @ApiModelProperty(value = "测试完成节点")
    private LocalDate testNodeDate;

    /**
     * 维护完成节点
     */
    @ApiModelProperty(value = "维护完成节点")
    private LocalDate serviceNodeDate;

    /**
     * 交付时间
     */
    @ApiModelProperty(value = "交付时间")
    private LocalDate deliverTimeDate;

    /**
     * 回款项
     */
    @ApiModelProperty(value = "回款id")
    private String phaseId;
}
