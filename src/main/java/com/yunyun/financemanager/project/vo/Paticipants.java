package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author  yangzhongming
 */
@Data
@ApiModel(value = "参与人员对象")
public class Paticipants {


    @ApiModelProperty(value = "工作类型id")
    Long typeId;

    @ApiModelProperty(value = "成员id")
    Long memberId;

    @ApiModelProperty(value = "开始时间")
    LocalDateTime startDate;

    @ApiModelProperty(value = "结束时间")
    LocalDateTime finishDate;

}

