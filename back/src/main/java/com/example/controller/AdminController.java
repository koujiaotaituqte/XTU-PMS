package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping("/selectAll")  //   完整的请求路径：http://ip:port/admin/selectAll
    public Result selectAll() {
        List<Admin> adminList = adminService.selectAll();
        return Result.success(adminList);
    }

    //pageNum当前页面
    //pageSize 每页个数
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue ="1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestParam(required = false ) String name)   {
        PageInfo<Admin> pageInfo =adminService.selectPage(pageNum,pageSize,name);
        return Result.success(pageInfo);
    }
}