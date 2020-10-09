package com.yunyun.financemanager.project.service;


import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.project.qo.ProjectFinance;

import java.time.LocalDate;


public interface ProjectFinanceService {

    ProjectFinance selectProjectName(int id );

    /**
     *通过条件查询项目
     * @param startDate 项目开始时间
     * @param  endDate 结束时间
     * @param pageSize  分页大小
     * @param pageStart 分页第几页
     * @param name  项目名称
     * @return   财务项目Dto对象
     */
    ProjectFinanceDTO selectFinanceProjects(LocalDate startDate, LocalDate endDate,String name ,int pageStart ,int pageSize);


}
