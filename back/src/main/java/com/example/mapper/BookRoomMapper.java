package com.example.mapper;

import com.example.entity.BookRoom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BookRoomMapper {

    // 动态查询所有预订（XML实现）
    List<BookRoom> selectAll(BookRoom bookRoom);

    // 新增预订（XML实现）
    void insert(BookRoom bookRoom);

    // 根据ID更新预订（XML实现）
    void updateById(BookRoom bookRoom);

    // 根据ID删除预订（注解方式）
   // @Delete("DELETE FROM bookroom WHERE id = #{id}")
    void deleteById(Integer id);

    // 检查时间冲突（XML实现）
    boolean hasTimeConflict(Integer roomtypeId, LocalDateTime start, LocalDateTime end);
}