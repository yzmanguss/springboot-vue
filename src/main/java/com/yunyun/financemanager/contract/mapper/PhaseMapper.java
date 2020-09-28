package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yunyun.financemanager.common.entity.Phase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xlc
 */
@Mapper
public interface PhaseMapper extends BaseMapper<Phase> {

    int insertBatch(@Param("phases") List<Phase> phases);

    int deleteByContractId(Long id);

}
