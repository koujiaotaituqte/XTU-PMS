package com.example.mapper;

import com.example.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper {

    List<Notice> selectAll(Notice notice);
    //这里是定义接口方法，实现在noticemapper.xml文件
    void insert(Notice notice);

    void updateById(Notice notice);

    @Delete("delete from notice where id = #{id}")
    void deleteById(Integer id);
}