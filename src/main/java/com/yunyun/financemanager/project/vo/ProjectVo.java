package com.yunyun.financemanager.project.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yunyun.financemanager.common.entity.Phase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author yangzhongming
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "项目对象")
public class ProjectVo {

    @ApiModelProperty(value = "项目id", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "项目名", required = true)
    @NotBlank
    @Length(max = 50)
    private String projectName;

    /**
     * 关联合同
     */
    @ApiModelProperty(value = "合同名")
    private String contract;

    @ApiModelProperty(value = "关联合同Id")
    private Long contractId;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人姓名")
    private String leader;

    @ApiModelProperty(value = "负责人ID", required = true)
    @NotNull
    private Long leaderId;

    /**
     * 参与人员Id
     */
    @ApiModelProperty(value = "参与人员ID", required = true)
    @NotBlank
    @Pattern(regexp = "^(\\d+,)*(\\d+)$")
    private String members;

    private String memberNames;

    /**
     * 签订日期
     */
    @ApiModelProperty(value = "项目签订时间")
    @Past
    private LocalDate signDate;

    /**
     * 计划开始时间
     */
    @ApiModelProperty(value = "计划开始时间")
    @NotNull
    private LocalDate expectedStartDate;

    /**
     * 计划完成时间
     */
    @NotNull
    @ApiModelProperty(value = "计划完成时间")
    private LocalDate expectedFinishDate;

    /**
     * 计划总工作量-单位:人天
     */
    @ApiModelProperty(value = "计划工作量")
    @NotNull
    @Min(1L)
    private Long expectedWorkload;

    /**
     * 计划需求分析节点时间
     */
    @ApiModelProperty(value = "计划需求分析时间节点")
    @NotNull
    private LocalDate expectedRequirementNodeDate;

    /**
     * 计划设计节点时间
     */
    @ApiModelProperty(value = "计划设计时间节点")
    @NotNull
    private LocalDate expectedDesignNodeDate;

    /**
     * 计划开发节点时间
     */
    @ApiModelProperty(value = "计划开发设计节点")
    @NotNull
    private LocalDate expectedDevelopNodeDate;

    /**
     * 计划测试节点时间
     */
    @ApiModelProperty(value = "计划测试时间")
    @NotNull
    private LocalDate expectedTestNodeDate;

    /**
     * 计划开发成本
     */
    @ApiModelProperty(value = "计划开发成本")
    @NotNull
    private Long expectedDevelopCost;

    /**
     * 计划商务成本
     */
    @ApiModelProperty(value = "计划商务成本")
    @NotNull
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
    private LocalDate deliverDate;

    /**
     * 回款项
     */
    @ApiModelProperty(value = "回款项")
    @Valid
    @Size(min = 1)
    private List<Phase> phases;
}
