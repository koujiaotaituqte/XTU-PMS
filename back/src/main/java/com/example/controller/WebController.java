package com.example.controller;

import com.example.common.Result;
import com.example.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Resource
    AdminService adminService;

    //get请求接口
    @GetMapping("/")//接口路径
    public Result index() {
        return Result.success("hello world");
    }

    @GetMapping("/admin")
    public Result admin(String name) {
        String admin =adminService.admin(name);
        return Result.success(admin);
    }

}
