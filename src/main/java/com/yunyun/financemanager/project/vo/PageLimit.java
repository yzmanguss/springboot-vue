package com.yunyun.financemanager.project.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author yangzhongming
 * @date 2020-09-28 16:54
 */
@Data
@ApiModel(value = "分页对象")
public class PageLimit {

    @ApiModelProperty(value = "当前页 --require")
    @NotNull
    Integer pageNow;

    @ApiModelProperty(value = "页面大小--require")
    @NotNull
    Integer pageSize;

    @ApiModelProperty(value = "开始时间-- 不传，则没有此条件")
    LocalDate startDate;

    @ApiModelProperty(value = "结束时间-- 不传，则没有此条件")
    LocalDate endDate;

    @ApiModelProperty(value = "项目状态-- 不传，则没有此条件")
    Integer state;

    @ApiModelProperty(value = "关键字 -- 不传，则没有此条件")
    String keyWord;

}
