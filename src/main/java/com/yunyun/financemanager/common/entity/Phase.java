package com.yunyun.financemanager.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分期款项
 *
 * @author xlc
 */
@ApiModel("分期款项的实体类")
@Data
@TableName("phase")
public class Phase implements Serializable {
    @ApiModelProperty(value = "id", name = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "插入时间", example = "1601358287482")
    private LocalDateTime insertDate;

    @ApiModelProperty(value = "修改时间", example = "1601358287482")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "分期款项的排列序号", name = "phaseIndexId", example = "1")
    @NotNull
    @Length(max = 4)
    private Integer phaseIndex;

    @ApiModelProperty(value = "分期款项的金额", name = "amount", example = "1000000")
    @NotNull
    @Min(0)
    private Long amount;

    @ApiModelProperty(value="期款日期",name="startDate",example="1601358287482")
    @NotNull
    private LocalDate startDate;

    @ApiModelProperty(value="完成时间",name="startDate",example="1601358287482")
    private LocalDate finishDate;

    @ApiModelProperty(value = "合同id", name = "contractId", example = "1")
    @Length(max = 20)
    private Long contractId;

    private static final long serialVersionUID = 1L;
}