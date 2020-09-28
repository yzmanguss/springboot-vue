package com.yunyun.financemanager.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty(value = "id", name = "id", example = "1")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "合同名称", name = "contractName", example = "")
    private String contractName;

    @ApiModelProperty(value = "插入者", name = "insertBy", example = "1")
    private String insertBy;

    @ApiModelProperty(value = "修改者", name = "updateBy", example = "1")
    private String updateBy;


    @ApiModelProperty(value = "合同编号", name = "contractNumber", example = "12131")
    private Integer contractNumber;

    @ApiModelProperty(value = "合同状态", name = "contractState", notes = "0:、1:、2:", example = "1")
    private Integer contractState;

    @ApiModelProperty(value = "客户名称", name = "customerName", example = "七里香科技公司")
    private String customerName;

    @ApiModelProperty(value = "联系人姓名", name = "customerContractName", example = "七里香科技公司")
    private String customerContactName;

    @ApiModelProperty(value = "联系人电话", name = "customerContractPhone", example = "18273319421")
    private String customerContactPhone;

    @ApiModelProperty(value = "合同金额-分", name = "amount", example = "10000.00")
    private Long amount;

    @ApiModelProperty(value = "签订时间", name= "signTime", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date signTime;

    @ApiModelProperty(value = "分期款项", name="phase")
    @TableField(exist = false)
    private List<Phase> phases;

    @ApiModelProperty(value = "开始时间", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "完成时间", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date finishTime;

    @ApiModelProperty(value = "插入时间", example = "2020-7-25 08:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date insertTime;

    @ApiModelProperty(value = "修改时间", example = "2020-7-26 09:00:00")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}