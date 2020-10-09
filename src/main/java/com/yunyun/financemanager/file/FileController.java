package com.yunyun.financemanager.file;

import com.yunyun.financemanager.common.response.ApiResponse;
import com.yunyun.financemanager.common.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author zhaoqin
 */
@Api("文件上传")
@Slf4j
@Validated
@RestController("/files")
public class FileController {

    @ApiOperation("上传单个文件")
    @PostMapping("/upload")
    public ApiResponse<String> uploadFile(MultipartFile file) {
        String path = FileUtils.save(file);
        return ApiResponse.ok(path);
    }

    @ApiOperation("下载文件")
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam @NotBlank String path) {
        File file = new File(path);
        InputStreamResource resource;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("未找到文件, path: " + path);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + file.getName());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .headers(headers)
                .body(resource);
    }

}
