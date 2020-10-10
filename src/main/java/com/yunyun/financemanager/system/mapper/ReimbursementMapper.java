package com.yunyun.financemanager.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunyun.financemanager.common.entity.Reimbursement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReimbursementMapper extends BaseMapper<Reimbursement> {

    int insertReimbursement(Reimbursement reimbursement);

    Long selectReimburseAmountSumByProjectId(int id);
}