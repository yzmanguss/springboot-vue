package com.yunyun.financemanager.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhaoqin
 */
@Slf4j
public abstract class FileUtils {

    private static final String FOLDER_PATH = "upload";

    public static String save(MultipartFile file) {
        Assert.isTrue(!file.isEmpty(), "文件为空");
        String originalFilename = file.getOriginalFilename();
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + originalFilename;
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            boolean mkdirs = folder.mkdirs();
            Assert.isTrue(mkdirs, "创建文件夹失败");
        }
        File uploadFile = new File(folder.getAbsolutePath() + File.separator + fileName);
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException("保存文件失败", e);
        }
        return uploadFile.getAbsolutePath();
    }

}
