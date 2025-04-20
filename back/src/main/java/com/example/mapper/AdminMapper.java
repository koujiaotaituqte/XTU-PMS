package com.example.mapper;

import com.example.entity.Admin;

import java.util.List;

public interface AdminMapper {

    List<Admin> selectAll(Admin admin);
    //这里是定义接口方法，实现在adminmapper.xml文件
    void insert(Admin admin);
}