package com.yunyun.financemanager.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.AddProjectVo;
import com.yunyun.financemanager.project.vo.ContractVo;
import com.yunyun.financemanager.project.vo.PageLimit;
import com.yunyun.financemanager.project.vo.ProjectVo;

import java.time.LocalDate;
import java.util.List;

/**
 * @author  yangzhongming
 */
public interface ProjectService extends IService<Project> {

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
   ApiResponse<Void> addProject(AddProjectVo project);

    /**
     * 获取项目详情
     * @param id  项目id
     * @return 对应id的项目
     */
   ApiResponse<ProjectVo> getProjectDetail(Long id);

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

    /**
     * 获取时间范围内交付项目的数量
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 项目数量
     */
    Long getDeliverProjectCount(LocalDate startDate, LocalDate endDate);

    /**
     * 根据关键字 模糊查詢合同id 和name
     * @param keyWord  關鍵字
     * @return 符合條件的合同id和 name
     */
    ApiResponse<List<ContractVo>> getContractNamelike(String keyWord);

    /**
     * 查詢最新 10条 合同id 和name
     * @return 前10条合同id和 name
     */
    ApiResponse<List<ContractVo>> getContractNamelike();


    List<Project> listBySignDateBetween(LocalDate startDate, LocalDate endDate);

    boolean updateProject(ProjectVo projectVo);
}
