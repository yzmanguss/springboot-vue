package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分期款项
 * @author xlc
 */
@ApiModel("分期款项的实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("phase")
public class Phase implements Serializable {
    @ApiModelProperty(value = "id", name = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "插入时间", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime insertDate;

    @ApiModelProperty(value = "修改时间", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime updateDate;


    @ApiModelProperty(value = "分期款项的排列序号", name = "phaseIndexId", example = "1")
    private Integer phaseIndex;

    @ApiModelProperty(value = "分期款项的金额", name = "amount", example = "1000000")
    private Long amount;

    @ApiModelProperty(value="开始时间",name="startTime",example="2020-7-25 16:40:30")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime startTime;

    @ApiModelProperty(value="完成时间",name="startTime",example="2020-7-25 16:40:30")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "合同id", name = "contractId", example = "1")
    private Long contractId;

    private static final long serialVersionUID = 1L;
}