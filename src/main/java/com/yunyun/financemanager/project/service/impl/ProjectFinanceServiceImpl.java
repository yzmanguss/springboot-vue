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

    @Override
    public ProjectFinance selectProjectName(int id) {


        Project project = projectFinanceMapper.selectProjectName(id);

        List<WorkLoad> workLoads = projectFinanceMapper.selectWorkLoadByProjectId(id);
        Long longKF = new Long(0);
        Long longCS = new Long(0);
        String mName = "";
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

                mName = memberMapper.selectMemberById(project.getLeaderId());

            }
        }


        Contract contractById = contractMapper.getContractById(project.getContractId());

        Long ra = reimbursementMapper.selectReimburseAmountSumByProjectId(id);

        ProjectFinance projectFinance = new ProjectFinance();

        projectFinance.setId(project.getId());
        projectFinance.setName(project.getProjectName());
        projectFinance.setLeader(mName);
        projectFinance.setExpectedWorkload(project.getExpectedWorkload());
        projectFinance.setWorkload(project.getDesignWorkload() + project.getDevelopWorkload() + project.getTestWorkload() + project.getServiceWorkload());
        projectFinance.setAmount(contractById.getAmount());
        projectFinance.setExpectedDevelopCost(project.getExpectedDevelopCost());
        projectFinance.setExpectedBusinessCost(project.getExpectedBusinessCost());
        projectFinance.setDev_cost(longKF);
        projectFinance.setTestCost(longCS);
        projectFinance.setReimbursementAmount(ra);

        projectFinance.setConsumeAmount(
                projectFinance.getExpectedBusinessCost() + project.getExpectedDevelopCost() + projectFinance.getDev_cost()
                        + projectFinance.getTestCost() + projectFinance.getReimbursementAmount()
        );
        projectFinance.setSurplusProfit(projectFinance.getAmount() - projectFinance.getConsumeAmount());

        return projectFinance;
    }


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
