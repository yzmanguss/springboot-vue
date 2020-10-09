package com.yunyun.financemanager.project.service;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.vo.WorkTypeVo;

import java.util.List;

/**
 * @author yangzhongming
 */
public interface WorkTypeService {


    /**
     * 查询所有的工作类型
     * @return 所有的工作类型
     */
    ApiResponse<List<WorkTypeVo>> queryAllWorkType();

}
