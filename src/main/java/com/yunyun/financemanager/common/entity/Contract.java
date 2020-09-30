package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 合同类
 *
 * @author xlc
 *
 */
@ApiModel("合同的实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id", name = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "合同名称", name = "contractName", example = "合同")
    @NotBlank
    @Length(max = 50)
    private String contractName;

    @ApiModelProperty(value = "插入者", name = "insertBy", example = "1")
    private Long insertBy;

    @ApiModelProperty(value = "修改者", name = "updateBy", example = "1")
    private Long updateBy;

    @ApiModelProperty(value = "合同编号", name = "contractNumber", example = "12131")
    @NotNull
    @Length(max = 50)
    private String contractNumber;

    @ApiModelProperty(value = "合同状态", name = "contractStatus", example = "1")
    @Range(min = 0, max = 3)
    private Integer contractStatus;

    @ApiModelProperty(value = "客户名称", name = "customerName", example = "七里香科技公司")
    @NotBlank
    @Length(max = 50)
    private String customerName;

    @ApiModelProperty(value = "联系人姓名", name = "customerContractName", example = "七里香科技公司")
    @NotBlank
    @Length(max = 50)
    private String customerContactName;

    @ApiModelProperty(value = "联系人电话", name = "customerContractPhone", example = "18273319421")
    @NotBlank
    @Length(max = 11)
    private String customerContactPhone;

    @ApiModelProperty(value = "合同金额-分", name = "amount", example = "1000000")
    @NotNull
    @Length(max = 20)
    private Long amount;

    @ApiModelProperty(value = "签订日期", name= "signDate", example = "1601358287482")
    @NotNull
    private LocalDate signDate;

    @ApiModelProperty(value = "分期款项", name="phases")
    @Valid
    @Size(min = 1)
    @TableField(exist = false)
    private List<Phase> phases;

    @ApiModelProperty(value = "开始日期", example = "1601358287482")
    @NotNull
    private LocalDate startDate;

    @ApiModelProperty(value = "完成日期", example = "1601358287482")
    @NotNull
    private LocalDate finishDate;

    @ApiModelProperty(value = "插入时间", example = "1601358287482")
    private LocalDateTime insertTime;

    @ApiModelProperty(value = "修改时间", example = "1601358287482")
    private LocalDateTime updateTime;
}