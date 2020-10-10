package com.yunyun.financemanager.project.qo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProjectNames {

    @ApiModelProperty(value = "项目id", name = "id")
    private Long id;

    @ApiModelProperty(value = "项目名称", name = "name")
    private String name;
}
