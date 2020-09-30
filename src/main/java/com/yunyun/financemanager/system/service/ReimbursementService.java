package com.yunyun.financemanager.system.service;

import com.yunyun.financemanager.common.entity.Reimbursement;
import org.springframework.web.multipart.MultipartFile;

public interface ReimbursementService {

    int insertReimbursement(Reimbursement reimbursement , MultipartFile photo);
}
