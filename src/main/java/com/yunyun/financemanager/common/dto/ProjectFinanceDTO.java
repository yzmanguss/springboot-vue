package com.yunyun.financemanager.common.dto;

import com.yunyun.financemanager.project.qo.ProjectFinance;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectFinanceDTO {

    private Long total;

    private List<ProjectFinance> projectFinances;

}
