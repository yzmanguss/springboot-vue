package com.yunyun.financemanager.project.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangzhongming
 * @date 2020-09-29 10:21
 */
@Data
@ApiModel(value = "工作量类型")
@TableName(value = "work_type")
public class WorkTypeVo {

    @ApiModelProperty(value = "类型id")
    private Integer id;

    @ApiModelProperty(value = "类型名")
    private String name;
}
