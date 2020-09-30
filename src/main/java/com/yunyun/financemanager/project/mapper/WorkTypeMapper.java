package com.yunyun.financemanager.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.project.vo.WorkTypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yangzhongming
 */
@Mapper
@Repository
public interface WorkTypeMapper extends BaseMapper<WorkTypeVo> {

}