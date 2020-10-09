package com.yunyun.financemanager.project.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProjectFinance {

    //项目ID
    @ApiModelProperty(value = "项目ID", name = "id")
    private Long id;
    //项目名
    @ApiModelProperty(value = "项目名", name = "name")
    private String projectName;
    //负责人
    @ApiModelProperty(value = "负责人", name = "leader")
    private String leader;
    //计划工作量
    @ApiModelProperty(value = "计划工作量", name = "expectedWorkload")
    private Long expectedWorkload;
    //实际工作量
    @ApiModelProperty(value = "实际工作量", name = "workload")
    private Long workload;
    //合同金额
    @ApiModelProperty(value = "合同金额", name = "amount")
    private Long amount;
    //    商务成本
    @ApiModelProperty(value = "商务成本", name = "expectedBusinessCost")
    private Long expectedBusinessCost;
    //    设计成本
    @ApiModelProperty(value = "设计成本", name = "expectedDevelopCost")
    private Long expectedDevelopCost;
    //    开发成本
    @ApiModelProperty(value = "开发成本", name = "dev_cost")
    private Long dev_cost;
    //    测试成本
    @ApiModelProperty(value = "测试成本", name = "testCost")
    private Long testCost;
    //    报销成本
    @ApiModelProperty(value = "报销成本", name = "reimbursementAmount")
    private Long reimbursementAmount;
    //    总消耗成本
    @ApiModelProperty(value = "总消耗成本", name = "consumeAmount")
    private Long consumeAmount;
    //    剩余利润
    @ApiModelProperty(value = "剩余利润", name = "surplusProfit")
    private Long surplusProfit;

}
