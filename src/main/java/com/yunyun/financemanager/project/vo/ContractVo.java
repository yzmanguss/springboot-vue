package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangzhongming
 * @date 2020-10-09 18:29
 */
@Data
@ApiModel("新增項目-合同vo")
public class ContractVo {

    @ApiModelProperty("合同id")
    private Long id;

    @ApiModelProperty("合同名")
    private String name;
}
