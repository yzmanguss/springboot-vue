package com.yunyun.financemanager.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        Assert.hasText(originalFilename, "文件名不能为空");
        assert originalFilename != null;
        Assert.isTrue(!originalFilename.contains(","), "文件名不能包含逗号");
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

    public static String saveImage(MultipartFile file) {
        Assert.isTrue(!file.isEmpty(), "文件为空");
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            Assert.notNull(bufferedImage, "请上传图片文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return save(file);
    }

}
