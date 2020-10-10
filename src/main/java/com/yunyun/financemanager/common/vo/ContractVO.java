package com.yunyun.financemanager.common.vo;

import com.yunyun.financemanager.project.vo.ProjectVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xlc
 */
@Getter
@Setter
@ApiModel("合同VO")
public class ContractVO {

    @ApiModelProperty(value = "id", name = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "合同名称", name = "contractName", example = "合同")
    private String contractName;

    @ApiModelProperty(value = "合同编号", name = "contractNumber", example = "12131")
    private String contractNumber;

    @ApiModelProperty(value = "客户名称", name = "customerName", example = "七里香科技公司")
    private String customerName;

    @ApiModelProperty(value = "合同金额-分", name = "amount", example = "1000000")
    private Long amount;

    @ApiModelProperty(value = "合同状态", name = "contractStatus", example = "1")
    private Integer contractStatus;

    @ApiModelProperty(value = "签订日期", name = "signDate", example = "1601358287482")
    private LocalDate signDate;

    private List<ProjectVo> projects;
}
