package com.yunyun.financemanager.contract.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.contract.mapper.PhaseMapper;
import com.yunyun.financemanager.contract.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author xlc
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private PhaseMapper phaseMapper;


    @Override
    public Page<Contract> listContractByPage(ContractQuery contractQuery) {
        int pageNum = contractQuery.getPageNum();
        int pageSize = contractQuery.getPageSize();
        Page<Contract> page = PageHelper.startPage(pageNum, pageSize);
        contractMapper.listContractByPage(contractQuery);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
     public ApiResponse<Void> deleteContractById(Long id) {

        int count =contractMapper.isContractAssociatedProject(id);
        if(count > 0) {
            return ApiResponse.failure("该合同已有关联项目，不能删除");
        }

        //删除分期款项
        int delete = phaseMapper.deleteByContractId(id);

        int result = contractMapper.deleteById(id);
        if (result > 0 && delete > 0 ) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("删除失败");
        }
    }

    @Override
    public ApiResponse<Contract> getContractById(Long id) {
        Contract contract =  contractMapper.getContractById(id);

        return ApiResponse.ok(contract);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<Void> addContract(Contract contract) {
        int result = contractMapper.insert(contract);

        contract.getPhases().forEach(x -> x.setContractId(contract.getId()));

        //添加分期款项
        int insert = phaseMapper.insertBatch(contract.getPhases());

        if (result > 0 && insert > 0 ) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<Void> editContract(Contract contract) {

        //删除原来的分期款项
        int delete = phaseMapper.deleteByContractId(contract.getId());

        //新增分期款项
        contract.getPhases().forEach(x -> x.setContractId(contract.getId()));

        int insert = phaseMapper.insertBatch(contract.getPhases());

        //修改合同
        int result = contractMapper.updateById(contract);
        if (delete > 0 && result > 0 && insert > 0 ) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("修改失败");
        }
    }


//    @Override
//    public ApiResponse<Contract> queryContractNameById(Long id) {
//        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
//        wrapper.select("name").eq("id",id);
//        Contract contract = contractMapper.selectOne(wrapper);
//        return contract;
//    }
//
//    @Override
//    public Long queryContractIdByName(String name) {
//        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
//        wrapper.select("id").eq("name",name);
//        Contract contract = contractMapper.selectOne(wrapper);
//        return contract.getId();
//    }

//    @Override
//    public ContractStatisticsVO contractStatistics() {
//        ContractStatisticsVO contractStatisticsVO = new ContractStatisticsVO();
//        contractStatisticsVO = contractMapper.contractStatistics();
//        int phaseAmountMonth = phaseMapper.phaseStatistics();
//        contractStatisticsVO.setPhaseAmountMonth(phaseAmountMonth);
//        return contractStatisticsVO;
//    }
}
