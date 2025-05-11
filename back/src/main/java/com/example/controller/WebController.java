package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Resource
    AdminService adminService;
    @Resource
    UserService userService;

    //get请求接口
    @GetMapping("/")//接口路径
    public Result index() {
        return Result.success("hello world");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbaccount = null;
        if("ADMIN".equals(account.getRole())) {
            dbaccount = adminService.login(account);
        }else if("USER".equals(account.getRole())) {
            dbaccount = userService.login(account);
        }else {
            throw new CustomException("非法请求");
        }
        return Result.success(dbaccount);
    }


    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PutMapping("/updatepassword")
    public Result updatePassword(@RequestBody Account account) {
        if("ADMIN".equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if("USER".equals(account.getRole())) {
            userService.updatePassword(account);
        }
        return Result.success();
    }
}

