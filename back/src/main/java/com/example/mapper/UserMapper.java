package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> selectAll(User user);
    //这里是定义接口方法，实现在usermapper.xml文件
    void insert(User user);

    @Select("select * from user where username = #{username}")//换一个sql写法
    User selectByUsername(String username);

    void updateById(User user);

    @Delete("delete from user where id = #{id}")
    void deleteById(Integer id);
}