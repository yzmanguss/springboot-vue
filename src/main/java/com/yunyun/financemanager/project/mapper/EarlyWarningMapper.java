package com.yunyun.financemanager.project.mapper;

import com.yunyun.financemanager.project.qo.CostEarlyWarning;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EarlyWarningMapper {

    CostEarlyWarning selectEarlyWarning(int id);
}
