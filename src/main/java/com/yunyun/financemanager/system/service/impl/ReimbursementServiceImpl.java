package com.yunyun.financemanager.system.service.impl;

import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.system.mapper.ReimbursementMapper;
import com.yunyun.financemanager.system.service.ReimbursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReimbursementServiceImpl implements ReimbursementService {

    private final ReimbursementMapper reimbursementMapper;

    @Override
    public int insertReimbursement(Reimbursement reimbursement) {

        return reimbursementMapper.insertReimbursement(reimbursement);
    }
}
