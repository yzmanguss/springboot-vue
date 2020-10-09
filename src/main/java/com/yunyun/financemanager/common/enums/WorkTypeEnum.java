package com.yunyun.financemanager.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yangzhongming
 * @date 2020-10-09 12:33
 */
@ApiModel(value = "工作类型")
public interface WorkTypeEnum {

    /**
     * 需求
     */
    @ApiModelProperty("需求")
    int REQUIREMENT = 1;
    /**
     * 设计
     */
    @ApiModelProperty("设计")
    int DESIGN = 2;
    /**
     * 测试
     */
    @ApiModelProperty("测试")
    int TEST = 3;
    /**
     * 开发
     */
    @ApiModelProperty("开发")
    int DEVELOP = 4;
    /**
     * 维护
     */
    @ApiModelProperty("维护")
    int SERVICE = 5;

}
