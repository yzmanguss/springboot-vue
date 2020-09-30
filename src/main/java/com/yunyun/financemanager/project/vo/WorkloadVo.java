package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author yangzhongming
 */
@Data
@ApiModel(value = "添加工作量对象")
public class WorkloadVo implements Serializable {
    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "参与人员")
    private Paticipants[] paticipants;

}
