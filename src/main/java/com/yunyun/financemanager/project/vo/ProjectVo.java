package com.yunyun.financemanager.project.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author yangzhongming
 * @date 2020-09-28 11:45
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectVo {
    private Long id;

    private String projectName;
    /**
     * 关联合同
     */
    private String  contract;

    /**
     * 负责人
     */
    private String  leader;

    /**
     * 参与人员
     */
    private String members;

    /**
     * 签订日期
     */
    private LocalDate signDate;

    /**
     * 计划开始时间
     */
    private LocalDate expectedStartDate;

    /**
     * 计划完成时间
     */
    private LocalDate expectedFinishDate;

    /**
     * 计划总工作量-单位:人天
     */
    private Long expectedWorkload;

    /**
     * 计划需求分析节点时间
     */
    private LocalDate expectedRequirementNodeDate;

    /**
     * 计划设计节点时间
     */
    private LocalDate expectedDesignNodeDate;

    /**
     * 计划开发节点时间
     */
    private LocalDate expectedDevelopNodeDate;

    /**
     * 计划测试节点时间
     */
    private LocalDate expectedTestNodeDate;

    /**
     * 计划开发成本
     */
    private Long expectedDevelopCost;

    /**
     * 计划商务成本
     */
    private Long expectedBusinessCost;

    /**
     * 计划维护节点-一般是交付一年后
     */
    private LocalDate expectedServiceNodeDate;

    /**
     * 实际工作量
     */
    private Long realWorkload;

    /**
     * 需求分析完成节点
     */
    private LocalDate requirementNodeDate;

    /**
     * 设计完成节点
     */
    private LocalDate designNodeDate;

    /**
     * 开发完成节点
     */
    private LocalDate developNodeDate;

    /**
     * 测试完成节点
     */
    private LocalDate testNodeDate;

    /**
     * 维护完成节点
     */
    private LocalDate serviceNodeDate;

    /**
     * 交付时间
     */
    private LocalDate deliverTimeDate;

    /**
     * 回款项
     */
    private String phaseId;
}
