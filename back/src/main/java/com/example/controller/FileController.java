package com.example.controller;


import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import com.example.exception.CustomException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileController {

    //文件上传
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = System.getProperty("user.dir") + "/files/";
        if(!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);//判断路径是否存在，没有创建
        }
        byte[] bytes = file.getBytes();
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();//文件的原始名称
        //String encodeFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        FileUtil.writeBytes(bytes, filePath + fileName);//写入文件
        String url = "http://localhost:9090/files/download/" + fileName;
        return Result.success(url);
    }


    //文件下载
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws Exception {
        // 找到文件的位置
        String filePath = System.getProperty("user.dir") + "/files/";  // 获取到当前项目的根路径（code2025的绝对路径 D:\项目实战\小白做毕设\代码\code2025）
        String realPath = filePath + fileName;  //  D:\项目实战\小白做毕设\代码\code2025\files\404.jpg
        boolean exist = FileUtil.exist(realPath);
        if (!exist) {
            throw new CustomException("文件不存在");
        }
        // 读取文件的字节流
        byte[] bytes = FileUtil.readBytes(realPath);
        ServletOutputStream os = response.getOutputStream();
        // 输出流对象把文件写出到客户端
        os.write(bytes);
        os.flush();
        os.close();
    }
}
