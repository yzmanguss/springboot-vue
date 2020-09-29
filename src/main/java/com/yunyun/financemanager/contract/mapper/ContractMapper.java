package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author xlc
 */
@Mapper
@Repository
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> listContractByPage(ContractQuery contractQuery);

    int isContractAssociatedProject(long id);

    Contract getContractById(long id);

    //ContractStatisticsVO contractStatistics();


    List<Contract> selectContractNames(String name);
}
