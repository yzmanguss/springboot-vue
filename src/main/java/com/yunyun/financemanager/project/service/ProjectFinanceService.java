package com.yunyun.financemanager.project.service;


import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.project.qo.ProjectFinance;

import java.time.LocalDate;


public interface ProjectFinanceService {

    ProjectFinance selectProjectName(int id );

    ProjectFinanceDTO selectFinanceProjects(LocalDate startDate, LocalDate endDate,String name);

    int selectCount();

}
