package com.example.mapper;

import com.example.entity.RoomType;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoomTypeMapper {

    // 动态查询所有房型（XML实现）
    List<RoomType> selectAll(RoomType roomType);

    // 新增房型（XML实现）
    void insert(RoomType roomType);

    // 根据ID更新房型（XML实现）
    void updateById(RoomType roomType);

    // 根据ID删除房型（注解方式）
    //@Delete("DELETE FROM roomtype WHERE id = #{id}")
    void deleteById(Integer id);

    // 校验房型名称是否存在（XML实现）
    boolean existsByTitle(@Param("title") String title, @Param("id") Integer id);

    // 检查房型是否被房间使用（XML实现）
    int hasRooms(Integer roomTypeId);

    // 检查ID是否存在
    @Select("SELECT COUNT(*) FROM roomtype WHERE id = #{id}")
    int existsById(Integer id);
}