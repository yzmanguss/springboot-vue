package com.yunyun.financemanager.system.mapper;

import com.yunyun.financemanager.common.entity.Reimbursement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReimbursementMapper {

    int insertReimbursement(Reimbursement reimbursement);

}