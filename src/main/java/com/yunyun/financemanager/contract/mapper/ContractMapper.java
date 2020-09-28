package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.response.ApiResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xlc
 */
@Mapper
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> listContractByPage(PageRequest pageRequest);

    /**
     * 查询合同是否关联项目
     * @param id
     * @return
     */
    int isContractAssociatedProject(long id);

    /**
     * 通过id查询合同
     * @param id
     * @return
     */
    Contract getContractById(long id);

    //ContractStatisticsVO contractStatistics();
}
