package com.yunyun.financemanager.project.service.impl;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.project.mapper.WorkTypeMapper;
import com.yunyun.financemanager.project.service.WorkTypeService;
import com.yunyun.financemanager.project.vo.WorkTypeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangzhongming
 */
@Service
public class WorkTypeServiceImpl implements WorkTypeService {

    @Resource
    private WorkTypeMapper workTypeMapper;

    @Override
    public ApiResponse<List<WorkTypeVo>> queryAllWorkType() {
        List<WorkTypeVo> workTypes = workTypeMapper.selectList(null);
        if (!workTypes.isEmpty()){
            throw new IllegalArgumentException("没有workType的数据");
        }
        return ApiResponse.ok(workTypes);
    }
}
