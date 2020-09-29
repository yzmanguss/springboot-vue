package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.WorkloadVo;

/**
 * @author yangzhongming
 * @date 2020-09-29 10:57
 */

public interface WorkLoadService {

    /**
     * 添加工作量
     * @param workloadVo  工作量对象
     * @return  添加工作量成功与否
     */
    ApiResponse<Void> addWorkLoad(WorkloadVo workloadVo);
}
