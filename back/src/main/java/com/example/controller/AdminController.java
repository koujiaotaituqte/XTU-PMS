package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) { //@RequestBody接受前端传来的json参数
        adminService.add(admin);
        return Result.success();
    }
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }


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
                             Admin admin)   {
        PageInfo<Admin> pageInfo =adminService.selectPage(pageNum,pageSize,admin);
        return Result.success(pageInfo);
    }
}