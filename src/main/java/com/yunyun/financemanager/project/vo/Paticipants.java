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


    @ApiModelProperty(value = "工作类型id 1：需求 2：设计 3：测试 4：开发 5：维护")
    Long typeId;

    @ApiModelProperty(value = "成员id")
    Long memberId;

    @ApiModelProperty(value = "开始时间")
    LocalDateTime startDate;

    @ApiModelProperty(value = "结束时间")
    LocalDateTime finishDate;

}

