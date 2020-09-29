package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xlc
 */
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> listContractByPage(ContractQuery contractQuery);

    int isContractAssociatedProject(long id);

    Contract getContractById(long id);

    //ContractStatisticsVO contractStatistics();
}
