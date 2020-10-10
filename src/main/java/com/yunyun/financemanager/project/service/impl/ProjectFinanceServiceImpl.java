package com.yunyun.financemanager.project.service.impl;

import com.yunyun.financemanager.common.dto.ProjectFinanceDTO;
import com.yunyun.financemanager.common.entity.*;
import com.yunyun.financemanager.contract.mapper.ContractMapper;
import com.yunyun.financemanager.project.mapper.MemberMapper;
import com.yunyun.financemanager.project.mapper.ProjectFinanceMapper;
import com.yunyun.financemanager.project.qo.ProjectFinance;
import com.yunyun.financemanager.project.service.ProjectFinanceService;
import com.yunyun.financemanager.system.mapper.ReimbursementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectFinanceServiceImpl implements ProjectFinanceService {


    private final ProjectFinanceMapper projectFinanceMapper;


    private final ReimbursementMapper reimbursementMapper;


    private final MemberMapper memberMapper;

    private final ContractMapper contractMapper;


    /**
     *通过条件查询项目
     * @param id 项目id
     * @return   ProjectFinance
     *
     *
     */

    @Override
    public ProjectFinance selectProjectName(int id) {


        Project project = projectFinanceMapper.selectProjectName(id);

        List<WorkLoad> workLoads = projectFinanceMapper.selectWorkLoadByProjectId(id);
        long longKF = 0L;
        long longCS = 0L;
        String mName = "";
        if(workLoads != null) {
            for (WorkLoad w : workLoads) {
                if (w.getWorkTypeId() == 4) {
                    BigInteger dw = new BigInteger(w.getDailyWage().toString());
                    BigInteger wl = new BigInteger(w.getWorkLoad().toString());
                    longKF = longKF + dw.multiply(wl).longValue();
                } else if (w.getWorkTypeId() == 3) {

                    BigInteger dw = new BigInteger(w.getDailyWage().toString());
                    BigInteger wl = new BigInteger(w.getWorkLoad().toString());
                    longCS = longCS + dw.multiply(wl).longValue();
                } else if (w.getMemberId().equals(project.getLeaderId())) {

                    mName = memberMapper.selectMemberNameById(project.getLeaderId());

                }
            }
        }


        Contract contractById = contractMapper.getContractById(project.getContractId());

        Long ra = reimbursementMapper.selectReimburseAmountSumByProjectId(id);
        if (ra == null){
            ra = 0L;
        }
        ProjectFinance projectFinance = new ProjectFinance();

        projectFinance.setId(project.getId());
        projectFinance.setProjectName(project.getProjectName());
        projectFinance.setLeader(mName);
        projectFinance.setExpectedWorkload(project.getExpectedWorkload());
        projectFinance.setWorkload(project.getDesignWorkload() + project.getDevelopWorkload() + project.getTestWorkload() + project.getServiceWorkload());
        projectFinance.setAmount(contractById.getAmount());
        projectFinance.setExpectedDevelopCost(project.getExpectedDevelopCost());
        projectFinance.setExpectedBusinessCost(project.getExpectedBusinessCost());
        projectFinance.setDev_cost(longKF);
        projectFinance.setTestCost(longCS);
        projectFinance.setReimbursementAmount(ra);
        projectFinance.setContract(contractById.getContractName());

        if (projectFinance.getExpectedWorkload()-projectFinance.getWorkload() < 0){
            projectFinance.setCostEarlyWarning(true);
        }

        if (projectFinance.getSurplusProfit() <0){
            projectFinance.setFinancialEarlyWarning(true);
        }


        projectFinance.setConsumeAmount(
                projectFinance.getExpectedBusinessCost() + project.getExpectedDevelopCost() + projectFinance.getDev_cost()
                        + projectFinance.getTestCost() + projectFinance.getReimbursementAmount()
        );
        projectFinance.setSurplusProfit(projectFinance.getAmount() - projectFinance.getConsumeAmount());

        return projectFinance;
    }


    /**
     *通过条件查询项目
     * @param startDate 项目开始时间
     * @param  endDate 结束时间
     * @param pageSize  分页大小
     * @param pageStart 分页第几页
     * @param name  项目名称
     * @return   财务项目Dto对象
     */
    @Override
    public ProjectFinanceDTO selectFinanceProjects(LocalDate startDate, LocalDate endDate, String name, int pageStart, int pageSize) {

        List<Project> projects = projectFinanceMapper.selectFinanceProjects(startDate, endDate, name, pageStart, pageSize);
        Long count = projectFinanceMapper.selectCount(startDate, endDate, name, pageStart, pageSize);

        List<ProjectFinance> pf = new ArrayList<>();

        for (Project p : projects) {
            ProjectFinance projectFinance = selectProjectName(p.getId().intValue());

            pf.add(projectFinance);
        }

        ProjectFinanceDTO projectFinanceDTO = new ProjectFinanceDTO();
        projectFinanceDTO.setTotal(count);
        projectFinanceDTO.setProjectFinances(pf);


        return projectFinanceDTO;
    }

}
