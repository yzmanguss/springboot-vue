package com.yunyun.financemanager.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;

import java.time.LocalDate;

/**
 * @author 杨忠明
 */
public interface ProjectService extends IService<Project> {

    /**
     * 获取项目列表
     *
     */
    ApiResponse<ProjectVo> getProjectList(PageLimit pageLimit);

    ApiResponse addPeoject(Project project);

    /**
     * 根据项目id删除
     *
     */
    ApiResponse deleteProject(String id);

    /**
     * 获取时间范围内交付项目的数量
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 项目数量
     */
    Long getDeliverProjectCount(LocalDate startDate, LocalDate endDate);

}
