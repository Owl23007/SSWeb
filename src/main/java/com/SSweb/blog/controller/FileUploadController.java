package com.SSweb.blog.controller;


import com.SSweb.blog.pojo.Result;
import com.SSweb.blog.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/upload")
    public Result onUpload(MultipartFile file) throws Exception {
        String originFileName = file.getOriginalFilename();
        // 保证文件名的唯一性
        String fileName = UUID.randomUUID().toString() + originFileName.substring(originFileName.lastIndexOf("."));
        // 将文件存储在阿里云OSS中
        String url = AliOssUtil.uploadFile(fileName, file.getInputStream());
        if(url == null)
            return Result.error("上传失败");
        return Result.success(url);
    }
}
