package com.yunyun.financemanager.project.qo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EarlyWarning {

    //    项目id
    @ApiModelProperty(value = " 项目id", name = "id")
    private  Long id;

    //    财务预警
    @ApiModelProperty(value = " 财务预警", name = "financialEarlyWarning")
    private boolean financialEarlyWarning =false;


    //    工作量预警
    @ApiModelProperty(value = " 工作量预警", name = "costEarlyWarning")
    private boolean costEarlyWarning =false;


}