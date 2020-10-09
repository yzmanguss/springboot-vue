package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.WorkLoad;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yangzhongming
 */
@Mapper
@Repository
public interface WorkLoadMapper extends BaseMapper<WorkLoad> {

}