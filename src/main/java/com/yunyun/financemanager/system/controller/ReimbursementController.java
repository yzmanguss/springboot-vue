package com.yunyun.financemanager.system.controller;

import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.system.service.impl.ReimbursementServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


/**
 * @author hhr
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReimbursementController {


   private final ReimbursementServiceImpl reimbursementService;

    @ApiOperation("插入报销")
    @PostMapping("/Reimbursement")
    public ApiResponse<Void> insertReimbursement(@RequestBody Reimbursement reimbursement , @RequestParam(value = "photo"
            ,required = false) MultipartFile photo , HttpSession session) {

        System.out.println(reimbursement.toString());
        System.out.println(photo);



//        if (photo != null) {
//            reimbursementService.insertReimbursement(reimbursement,photo);
//        }
        return ApiResponse.ok();
    }
}
