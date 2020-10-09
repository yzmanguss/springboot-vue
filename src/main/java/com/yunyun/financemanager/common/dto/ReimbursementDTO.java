package com.yunyun.financemanager.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Data
@ApiModel
public class ReimbursementDTO {

    @ApiModelProperty(value = "名字", example = "打车费")
    @NotBlank
    @Length(max = 50)
    private String name;

    /**
     * 报销金额
     */
    @ApiModelProperty(value = "报销金额", name = "reimburseAmount", example = "2000")
    @NotNull
    private Long reimburseAmount;

    /**
     * 发生时间
     */
    @ApiModelProperty(value = "发生时间", name = "occurTime", example = "2156432445646")
    @NotNull
    @Past
    private LocalDateTime occurTime;

    /**
     * 报销类型ID
     */
    @ApiModelProperty(value = "报销类型ID", name = "reimbursementTypeId", example = "1")
    @NotNull
    private Long reimbursementTypeId;

    @ApiModelProperty(value = "图片", name = "photo")
    @NotNull
    private MultipartFile photo;

}
