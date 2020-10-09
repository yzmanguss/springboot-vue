package com.yunyun.financemanager.common.dto;


import com.yunyun.financemanager.common.entity.Reimbursement;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReimbursementDTO {


    @ApiModelProperty(value = "报销", name = "reimbursement")
    private Reimbursement reimbursement;

    @ApiModelProperty(value = "图片", name = "photo")
    private   MultipartFile photo;

}
