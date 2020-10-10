package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.project.vo.ProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author yangzhongming
 */
@Mapper
@Repository
public interface ProjectMapper extends BaseMapper<Project> {

    /**
     * 获取项目列表
     *
     * @param pageNow   当前页
     * @param pageSize  页大小
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param state     项目状态
     * @param keyWord   关键字
     * @return 符合条件的项目
     */
    List<Project> getProjectList(@Param("pageNow") Integer pageNow, @Param("pageSize") Integer pageSize, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("state") Integer state, @Param("keyWord") String keyWord);

    /**
     * 获取项目列表总条数
     *
     * @param pageNow   当前页
     * @param pageSize  页大小
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param state     项目状态
     * @param keyWord   关键字
     * @return 符合条件的项目
     */
    Long getProjectListCount(@Param("pageNow") Integer pageNow, @Param("pageSize") Integer pageSize, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("state") Integer state, @Param("keyWord") String keyWord);

    List<Project> selectProjectNames(String name);

    ProjectVo selectVO(Long id);
}