package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    List<Admin> selectAll(Admin admin);
    //这里是定义接口方法，实现在adminmapper.xml文件
    void insert(Admin admin);

    @Select("select * from admin where username = #{username}")//换一个sql写法
    Admin selectByUsername(String username);

    void updateById(Admin admin);
}