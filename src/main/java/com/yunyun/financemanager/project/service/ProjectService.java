package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;
import java.util.List;

/**
 * @author  yangzhongming
 * @date 2020-09-28 10:27
 */
public interface ProjectService {

    /**
     * 获取项目列表
     * @param pageLimit page对象
     * @return  符合条件的项目列表
     */
   ApiResponse<List<ProjectVo>> getProjectList(PageLimit pageLimit);

    /**
     *添加项目
     * @param project 项目
     * @return 添加成功与否
     */
   ApiResponse<Void> addPeoject(Project project);

    /**
     * 获取项目详情
     * @param id  项目id
     * @return 对应id的项目
     */
   ApiResponse<ProjectVo> getProjectDetail(String id);

    /**
     * 项目结项
     * @param project 结项的项目
     * @return  结项成功与否
     */
   ApiResponse<Void> conclusionProject(Project project);

    /**
     * 根据项目id删除
     * @param id 项目id
     * @return 删除成功与否
     */
   ApiResponse<Void> deleteProject(String id);


}
