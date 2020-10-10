package com.yunyun.financemanager.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunyun.financemanager.common.entity.NormalCost;
import com.yunyun.financemanager.common.entity.Project;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.common.enums.WorkTypeEnum;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.ProjectMapper;
import com.yunyun.financemanager.project.mapper.WorkLoadMapper;
import com.yunyun.financemanager.project.service.MemberService;
import com.yunyun.financemanager.project.service.WorkLoadService;
import com.yunyun.financemanager.project.vo.Paticipants;
import com.yunyun.financemanager.project.vo.WorkloadVo;
import com.yunyun.financemanager.system.mapper.NormalCostMapper;
import com.yunyun.financemanager.system.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yangzhongming
 */
@Service
public class WorkLoadServiceImpl extends ServiceImpl<WorkLoadMapper, WorkLoad> implements WorkLoadService {
    @Resource
    private WorkLoadMapper workLoadMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private AccountService accountService;

    @Resource
    private MemberService memberService;

    @Resource
    private NormalCostMapper normalCostMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse<Void> addWorkLoad(WorkloadVo workloadVo) {
        Assert.notNull(workloadVo, "workload  is null");
        Assert.notNull(workloadVo.getPaticipants(), "paticipants is null");
        Paticipants[] paticipants = workloadVo.getPaticipants();
        Project project = projectMapper.selectById(workloadVo.getProjectId());
        Assert.notNull(project, "项目id不存在");
        Long testWorkload = project.getTestWorkload();
        Long serviceWorkload = project.getServiceWorkload();
        Long developWorkload = project.getDevelopWorkload();
        Long designWorkload = project.getDesignWorkload();
        Long requirementWorkload = project.getRequirementWorkload();
        NormalCost normalCost = normalCostMapper.selectOne(null);
        Long amount = normalCost.getAmount();
        for (Paticipants participate : paticipants) {
            //校验时间冲突
            QueryWrapper<WorkLoad> wrapper = new QueryWrapper<>();
            wrapper.eq("member_id", participate.getMemberId())
                    .eq("project_id", workloadVo.getProjectId());
            List<WorkLoad> workLoadValidate = workLoadMapper.selectList(wrapper);
            if (workLoadValidate != null && !workLoadValidate.isEmpty()) {
                for (WorkLoad workLoad : workLoadValidate) {
                    QueryWrapper<WorkLoad> wrapperValidate = new QueryWrapper<>();
                    wrapperValidate.between("start_date", participate.getStartDate(), participate.getFinishDate());
                    wrapperValidate.between("finish_date", participate.getStartDate(), participate.getFinishDate());
                    List<WorkLoad> workLoads = workLoadMapper.selectList(wrapperValidate);
                    if (workLoads != null && !workLoads.isEmpty()) {
                        throw new IllegalArgumentException("参与人员冲突");
                    }
                }
            }
            WorkLoad workLoad = new WorkLoad();
            workLoad.setInsertBy(accountService.getLoginUserId());
            LocalDateTime startDate = participate.getStartDate();
            LocalDateTime finishDate = participate.getFinishDate();
            workLoad.setStartDate(startDate);
            workLoad.setFinishDate(finishDate);
            workLoad.setWorkLoad(Duration.between(startDate, finishDate).toDays());
            workLoad.setMemberId(participate.getMemberId());
            workLoad.setWorkTypeId(participate.getTypeId());
            Long workload = memberService.selMemberDailyWageById(participate.getMemberId()).getDailyWage();
            workLoad.setDailyWage(workload);
            workLoad.setProjectId(workloadVo.getProjectId());
            workLoad.setDailyOfficeCost(amount);
            workLoadMapper.insert(workLoad);
            int type = participate.getTypeId().intValue();
            switch (type) {
                case WorkTypeEnum.REQUIREMENT:
                    requirementWorkload += workload;
                    break;
                case WorkTypeEnum.DESIGN:
                    designWorkload += workload;
                    break;
                case WorkTypeEnum.TEST:
                    testWorkload += workload;
                    break;
                case WorkTypeEnum.DEVELOP:
                    developWorkload += workload;
                    break;
                case WorkTypeEnum.SERVICE:
                    serviceWorkload += workload;
                    break;
                default:
                    throw new IllegalArgumentException("typeID 不存在");
            }
        }
        project.setUpdateBy(accountService.getLoginUserId());
        project.setTestWorkload(testWorkload);
        project.setServiceWorkload(serviceWorkload);
        project.setDesignWorkload(designWorkload);
        project.setDevelopWorkload(developWorkload);
        project.setRequirementWorkload(requirementWorkload);
        projectMapper.updateById(project);
        return ApiResponse.ok();
    }
}
