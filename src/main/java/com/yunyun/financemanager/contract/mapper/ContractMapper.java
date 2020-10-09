package com.yunyun.financemanager.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.vo.LineChartVO;
import com.yunyun.financemanager.project.vo.ContractVo;
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

    /**
     * 查询前10条合同id name
     * @return  前10条合同id name
     */
    List<ContractVo> getContractNamelikeLimit();


    /**
     * 根据关键字查询合同id和name
     * @return  符合条件的合同id和name
     */
    List<ContractVo> getContractNamelikeByName(@Param("name") String name);
}
