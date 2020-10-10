package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.project.qo.EarlyWarning;



public interface EarlyWarningService {

     EarlyWarning selectEarlyWarning(Long id,long contractId);
}
