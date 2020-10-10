package com.yunyun.financemanager.project.mapper;


import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ProjectFinanceMapper {

     Project selectProjectName(int id );

//     根据projectId查询workload员工

     List<WorkLoad> selectWorkLoadByProjectId(int id);



     List<Project> selectFinanceProjects( LocalDate startDate, LocalDate endDate,String name,int pageStart ,int pageSize);


     Long selectCount(LocalDate startDate, LocalDate endDate,String name);

}
