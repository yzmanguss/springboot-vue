package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.project.qo.EarlyWarning;



/**
 * @author hhr
 */
public interface EarlyWarningService {

     /**
      * 查询预警
      * @return 预警查询对象
      */
     EarlyWarning selectEarlyWarning(Long id,long contractId);
}
