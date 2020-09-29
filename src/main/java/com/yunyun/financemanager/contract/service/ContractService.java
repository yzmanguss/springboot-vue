package com.yunyun.financemanager.contract.service;


import com.github.pagehelper.Page;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.ContractQueryDTO;
import com.yunyun.financemanager.common.response.ApiResponse;




/**
 * @author xlc
 */
public interface ContractService {

    /**
     * 根据条件查询合同
     * @param contractQueryDTO
     * @return
     */
    Page<Contract> listContractByPage(ContractQueryDTO contractQueryDTO);

    /**
     * 根据id删除单个合同
     * @param id
     * @return
     */
    ApiResponse<Void> deleteContractById(Long id);

    /**
     * 根据id查询单个合同
     * @param id
     * @return
     */
    ApiResponse<Contract> getContractById(Long id);

    /**
     *
     * 保存合同信息
     * @param contract
     */
    ApiResponse<Void> addContract(Contract contract);

    /**
     * 修改合同信息
     * @param contract
     */
    ApiResponse<Void> editContract(Contract contract);








//    /**
//     * 首页合同统计数据
//     * @param
//     * @return
//     */
//   ContractStatisticsVO contractStatistics();
}
