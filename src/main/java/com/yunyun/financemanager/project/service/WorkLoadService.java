package com.yunyun.financemanager.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunyun.financemanager.common.entity.WorkLoad;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.WorkloadVo;

/**
 * @author zhaoqin
 */
public interface WorkLoadService extends IService<WorkLoad> {
    /**
     * 添加工作量
     * @param workloadVo  工作量对象
     * @return  添加工作量成功与否
     */
    ApiResponse<Void> addWorkLoad(WorkloadVo workloadVo);
}
