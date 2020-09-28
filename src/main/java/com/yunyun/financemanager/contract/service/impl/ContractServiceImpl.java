package com.yunyun.financemanager.contract.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.finance.system.contract.service.PhaseService;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.contract.mapper.PhaseMapper;
import com.yunyun.financemanager.contract.service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * @author xlc
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private PhaseMapper phaseMapper;
    @Resource
    private PhaseService phaseService;


    @Override
    public Page<Contract> listContractByPage(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Page<Contract> page = PageHelper.startPage(pageNum, pageSize);
        contractMapper.listContractByPage(pageRequest);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
     public ApiResponse<String> deleteContractById(Long id) {

        int count =contractMapper.isContractAssociatedProject(id);
        if(count > 0) {
            return ApiResponse.buildErrorMessage("该合同已有关联项目，不能删除");
        }

        //删除分期款项
        int delete = phaseMapper.deleteByContractId(id);

        int result = contractMapper.deleteById(id);
        if (result > 0 && delete > 0 ) {
            return ApiResponse.buildSuccessMessage(CrudMessageEnum.DELETE_SUCCESS.getMessage());
        } else {
            return ApiResponse.buildErrorMessage(CrudMessageEnum.DELETE_FAILED.getMessage());
        }
    }

    @Override
    public ApiResponse<Contract> getContractById(Long id) {
        Contract contract =  contractMapper.getContractById(id);

//        List<Phase> phaseList = phaseMapper.selectByContractId(id);
//        Contract.setPhases(phaseList);
        return ApiResponse.ok(contract);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<String> addContract(Contract contract) {
        int result = contractMapper.insert(contract);

        contract.getPhases().forEach(x -> x.setContractId(contract.getId()));

        //添加分期款项
        int insert = phaseMapper.insertBatch(contract.getPhases());

        if (result > 0 && insert > 0 ) {
            return ApiResponse.buildSuccessMessage(CrudMessageEnum.SAVE_SUCCESS.getMessage());
        } else {
            return ApiResponse.buildErrorMessage(CrudMessageEnum.SAVE_FAILED.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<String> editContract(Contract contract) {

        //删除原来的分期款项
        int delete = phaseMapper.deleteByContractId(contract.getId());

        //新增分期款项
        contract.getPhases().forEach(x -> x.setContractId(contract.getId()));
        int insert = phaseMapper.insertBatch(contract.getPhases());

        //修改合同
        int result = contractMapper.updateById(contract);
        if (result > 0 && insert > 0) {
            return ApiResponse.buildSuccessMessage(CrudMessageEnum.SAVE_SUCCESS.getMessage());
        } else {
            return ApiResponse.buildErrorMessage(CrudMessageEnum.SAVE_FAILED.getMessage());
        }
    }

    @Override
    public ApiResponse<String> queryAllContractNames(String keyWord) {
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        wrapper.select("name","id","sign_time").eq("state",0).like("name",keyWord);
        List<Contract> list = contractMapper.selectList(wrapper);
        return ApiResponse.buildSuccessResponse(list);
    }

    @Override
    public String queryContractNameById(String id) {
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("id",id);
        Contract contract = contractMapper.selectOne(wrapper);
        return contract.getName();
    }

    @Override
    public Long queryContractIdByName(String name) {
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        wrapper.select("id").eq("name",name);
        Contract contract = contractMapper.selectOne(wrapper);
        return contract.getId();
    }

//    @Override
//    public ContractStatisticsVO contractStatistics() {
//        ContractStatisticsVO contractStatisticsVO = new ContractStatisticsVO();
//        contractStatisticsVO = contractMapper.contractStatistics();
//        int phaseAmountMonth = phaseMapper.phaseStatistics();
//        contractStatisticsVO.setPhaseAmountMonth(phaseAmountMonth);
//        return contractStatisticsVO;
//    }
}
