package com.yunyun.financemanager.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.vo.LineChartVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author xlc
 */
public interface ContractService extends IService<Contract> {

    /**
     * 根据条件查询合同
     *
     * @param contractQuery
     * @return
     */
    Page<Contract> listContractByPage(ContractQuery contractQuery);

    /**
     * 根据id删除单个合同
     *
     * @param id
     * @return
     */
    ApiResponse<Void> deleteContractById(Long id);

    /**
     * 根据id查询单个合同
     *
     * @param id
     * @return
     */
    ApiResponse<Contract> getContractById(Long id);

    /**
     * 保存合同信息
     *
     * @param contract
     */
    ApiResponse<Void> addContract(Contract contract);

    /**
     * 修改合同信息
     *
     * @param contract
     */
    ApiResponse<Void> editContract(Contract contract);

    /**
     * 获取时间范围内签订合同的金额
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 总金额
     */
    Long getContractSignedAmount(LocalDate startDate, LocalDate endDate);

    /**
     * 获取时间范围内签订合同的数量
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 合同数量
     */
    Long getSignedContractCount(LocalDate startDate, LocalDate endDate);

//    /**
//     * 首页合同统计数据
//     * @param
//     * @return
//     */
//   ContractStatisticsVO contractStatistics();


    /**
     * 模糊查询合同名
     * @author hhr
     * @param name 合同名
     * @return 合同对象集
     */
    List<Contract> selectContractNames(String name);

    Map<Integer, LineChartVO> getYearAmountGroupByMonth(int year);

    Map<Integer, LineChartVO> getMonthAmountGroupByDay(int year, int month);
}
