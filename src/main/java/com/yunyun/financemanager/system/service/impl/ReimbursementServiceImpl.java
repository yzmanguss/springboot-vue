package com.yunyun.financemanager.system.service.impl;

import com.yunyun.financemanager.common.entity.Reimbursement;
import com.yunyun.financemanager.system.mapper.ReimbursementMapper;
import com.yunyun.financemanager.system.service.ReimbursementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReimbursementServiceImpl implements ReimbursementService {

    private final ReimbursementMapper reimbursementMapper;

    @Override
    public int insertReimbursement(Reimbursement reimbursement , MultipartFile photo) {
        if (photo.isEmpty()) {
            System.out.println("文件为空");
        }
        String photoName = photo.getOriginalFilename();  // 文件名
        String suffixName = photoName.substring(photoName.lastIndexOf("."));  // 后缀名
        String filePath = "../images/reimbursement/"; // 上传后的路径
        photoName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + photoName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            photo.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reimbursement.setPics(photoName);
        return reimbursementMapper.insertReimbursement(reimbursement);
    }
}
