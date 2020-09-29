package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;

/**
 * @author  杨忠明
 * @date 2020-09-28 10:27
 */
public interface ProjectService {

    /**
     * 获取项目列表
     * @return
     */
   ApiResponse<ProjectVo> getProjectList(PageLimit pageLimit);

    /**
     *
     * @param project
     * @return
     */
   ApiResponse addPeoject(Project project);


    /**
     * 根据项目id删除
     * @param id
     * @return
     */
   ApiResponse deleteProject(String id);


}
