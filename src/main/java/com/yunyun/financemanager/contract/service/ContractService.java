package com.yunyun.financemanager.contract.service;


import com.github.pagehelper.Page;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.response.ApiResponse;


import java.sql.Date;


/**
 * @author xlc
 */
public interface ContractService {

    Page<Contract> listContractByPage(PageRequest pageRequest);

    ApiResponse<String> deleteContractById(Integer id);

    /**
     * 根据id查询单个合同
     * @param id
     * @return
     */
    ApiResponse<Contract> getContractById(Integer id);

    /**
     * 保存合同信息
     * @param contract
     */
    ApiResponse<String> addContract(Contract contract);

    /**
     * 修改合同信息
     * @param contract
     */
    ApiResponse<String> editContract(Contract contract);


    /**
     *
     * 模糊查询合同名
     * @param keyWord
     * @return
     */
    ApiResponse<String> queryAllContractNames(String keyWord);


    /**
     * 通过id查询合同名
     * @param id
     * @return
     */
    String queryContractNameById(String id);

    /**
     * 通过合同名查询id
     * @param name
     * @return
     */
    Long queryContractIdByName(String name);

//    /**
//     * 首页合同统计数据
//     * @param
//     * @return
//     */
//   ContractStatisticsVO contractStatistics();
}
