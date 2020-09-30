package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.vo.LineChartVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author xlc
 */
@Mapper
@Repository
public interface ContractMapper extends BaseMapper<Contract> {

    List<Contract> listContractByPage(ContractQuery contractQuery);

    int isContractAssociatedProject(long id);

    Contract getContractById(long id);

    @MapKey("index")
    Map<Integer, LineChartVO> selectYearAmountGroupByMonth(int year);

    List<Contract> selectContractNames(String name);

    @MapKey("index")
    Map<Integer, LineChartVO> getMonthAmountGroupByDay(@Param("year") int year, @Param("month") int month);
}
