package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunyun.financemanager.common.entity.Contract;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.project.mapper.EarlyWarningMapper;
import com.yunyun.financemanager.project.mapper.WorkLoadMapper;
import com.yunyun.financemanager.project.qo.CostEarlyWarning;
import com.yunyun.financemanager.project.qo.EarlyWarning;
import com.yunyun.financemanager.project.service.EarlyWarningService;
import com.yunyun.financemanager.system.mapper.ReimbursementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EarlyWarningServiceImpl implements EarlyWarningService {


    private final EarlyWarningMapper earlyWarningMapper;

    private final WorkLoadMapper workLoadMapper;

    private final ReimbursementMapper reimbursementMapper;

    private final ContractMapper contractMapper;

    @Override
    public EarlyWarning selectEarlyWarning(Long id,long contractId) {
        //返回对象
        EarlyWarning earlyWarning = new EarlyWarning();

        //工作量预警
        CostEarlyWarning costEarlyWarning = earlyWarningMapper.selectEarlyWarning(id.intValue());
        if(costEarlyWarning.getCostEarlyWarning() < 0){
            earlyWarning.setCostEarlyWarning(true);
        }
        earlyWarning.setId(earlyWarning.getId());
        //员工成本
        QueryWrapper<WorkLoad> wrapper = new QueryWrapper<>();
        wrapper.eq("project_id",id);
        List<WorkLoad> workLoads = workLoadMapper.selectList(wrapper);
        long sum = 0L;
        for( WorkLoad item :workLoads){
           sum = sum + (item.getDailyWage()+item.getDailyOfficeCost()) * item.getWorkLoad();
        }
        //报销成本
        Long ras = reimbursementMapper.selectReimburseAmountSumByProjectId(id.intValue());
        if(ras == null){
            ras = 0l;
        }
        //合同金额
        QueryWrapper<Contract>  contractWrapper = new QueryWrapper<>();
        contractWrapper.eq("id",contractId);
        Contract contract = contractMapper.selectOne(contractWrapper);

        earlyWarning.setId(costEarlyWarning.getId());
        //财务预警
        if(contract.getAmount()-(sum+ras)<0){
            earlyWarning.setFinancialEarlyWarning(true);
        }

        return earlyWarning;

    }
}
