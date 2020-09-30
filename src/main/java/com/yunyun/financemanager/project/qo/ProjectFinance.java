package com.yunyun.financemanager.project.qo;

import lombok.Data;

@Data
public class ProjectFinance {

    private Long id;
    private String name;
    private String leader;
    private Long expectedWorkload;
    private Long workload;
    private Long amount;
    private Long expectedBusinessCost;
    private Long expectedDevelopCost;
    private Long dev_cost;
    private Long testCost;
    private Long reimbursementAmount;
}
