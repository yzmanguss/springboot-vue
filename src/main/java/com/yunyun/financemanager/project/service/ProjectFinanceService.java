package com.yunyun.financemanager.project.service;


import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.project.qo.ProjectFinance;

import java.util.List;


public interface ProjectFinanceService {

    ProjectFinance selectProjectName(int id);

    List<ProjectFinance>  selectFinanceProjects(int start);

    int selectCount();

}
