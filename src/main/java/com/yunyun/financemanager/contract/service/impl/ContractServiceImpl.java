package com.yunyun.financemanager.contract.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.Phase;
import com.yunyun.financemanager.common.query.ContractQuery;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.vo.ContractVO;
import com.yunyun.financemanager.common.vo.LineChartVO;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.contract.mapper.PhaseMapper;
import com.yunyun.financemanager.contract.service.ContractService;
import com.yunyun.financemanager.system.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author xlc
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {
    @Resource
    private ContractMapper contractMapper;

    @Resource
    private PhaseMapper phaseMapper;

    @Resource
    private AccountService accountService;

    @Override
    public Page<ContractVO> listContractByPage(ContractQuery contractQuery) {
        int pageNum = contractQuery.getPageNum();
        int pageSize = contractQuery.getPageSize();
        Page<ContractVO> page = PageHelper.startPage(pageNum, pageSize);
        contractMapper.listContractByPage(contractQuery);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<Void> deleteContractById(Long id) {

//        int count = contractMapper.isContractAssociatedProject(id);
//        if (count > 0) {
//            return ApiResponse.failure("该合同已有关联项目，不能删除");
//        }

        //删除分期款项
        int delete = phaseMapper.deleteByContractId(id);

        int result = contractMapper.deleteById(id);
        if (result > 0 && delete > 0) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("删除失败");
        }
    }

    @Override
    public ApiResponse<Contract> getContractById(Long id) {
        Contract contract = contractMapper.getContractById(id);

        return ApiResponse.ok(contract);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<Void> addContract(Contract contract) {
        Long insertBy = accountService.getLoginUserId();
        contract.setInsertBy(insertBy);
        int result = contractMapper.insert(contract);

        List<Phase> phases = contract.getPhases();
        //添加分期款项
        phases.forEach(x -> x.setContractId(contract.getId()));
        int insertBatchPhase = phaseMapper.insertBatchPhase(phases);

        if (result > 0 && insertBatchPhase > 0) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("添加失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse<Void> editContract(Contract contract) {

        //删除原来的分期款项
        phaseMapper.deleteByContractId(contract.getId());

        //新增分期款项
        List<Phase> phases = contract.getPhases();
        //添加分期款项
        phases.forEach(x -> x.setContractId(contract.getId()));
        int insertBatchPhase = phaseMapper.insertBatchPhase(phases);

        //修改合同
        Long updateBy = accountService.getLoginUserId();
        contract.setUpdateBy(updateBy);
        int result = contractMapper.updateById(contract);
        if (result > 0 && insertBatchPhase > 0) {
            return ApiResponse.ok();
        } else {
            return ApiResponse.failure("修改失败");
        }
    }

    @Override
    public Long getContractSignedAmount(LocalDate startDate, LocalDate endDate) {
        List<Contract> list = this.list(Wrappers.<Contract>lambdaQuery()
                .between(Contract::getSignDate, startDate, endDate));
        return list.stream()
                .map(Contract::getAmount)
                .reduce(0L, Long::sum);
    }

    @Override
    public Long getSignedContractCount(LocalDate startDate, LocalDate endDate) {
        int count = this.count(Wrappers.<Contract>lambdaQuery()
                .between(Contract::getSignDate, startDate, endDate));
        return (long) count;
    }

    @Override
    public List<Contract> selectContractNames(String name) {
        return this.list(Wrappers.<Contract>lambdaQuery()
                .like(StringUtils.hasText(name), Contract::getContractName, name)
                .orderByDesc(Contract::getId)
                .last("LIMIT 10"));
    }

    @Override
    public Map<Integer, LineChartVO> getYearAmountGroupByMonth(int year) {
        return contractMapper.selectYearAmountGroupByMonth(year);
    }

    @Override
    public Map<Integer, LineChartVO> getMonthAmountGroupByDay(int year, int month) {
        return contractMapper.getMonthAmountGroupByDay(year, month);
    }

}
