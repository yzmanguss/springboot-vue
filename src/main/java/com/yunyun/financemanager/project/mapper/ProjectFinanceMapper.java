package com.yunyun.financemanager.project.mapper;


import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectFinanceMapper {

     Project selectProjectName(int id);

//     根据projectId查询workload员工

     List<WorkLoad> selectWorkLoadByProjectId(int id);


     List<Project> selectFinanceProjects(int start,int end);

     int selectCount();

}
