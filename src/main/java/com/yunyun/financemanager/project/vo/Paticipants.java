package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author  yangzhongming
 * @date 2020-09-29 14:59
 */
@Data
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

