package com.example.mapper;

import com.example.entity.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoomMapper {

    // 动态查询
    List<Room> selectAll(Room room);

    // 新增房间（返回自增ID）
   // @Insert("INSERT INTO room (roomtype_id, status) VALUES (#{roomtypeId}, #{status})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Room room);

    // 根据ID更新
   // @Update("UPDATE room SET roomtype_id=#{roomtypeId}, status=#{status} WHERE id=#{id}")
    void updateById(Room room);

    // 根据ID删除
    //@Delete("DELETE FROM room WHERE id = #{id}")
    void deleteById(Integer id);

    // 校验房型是否存在
    //@Select("SELECT COUNT(*) FROM roomtype WHERE id = #{roomtypeId}")
    boolean existsRoomType(Integer roomtypeId);

    boolean existsRoomId(Integer id);
    // 检查房间号是否存在（根据需求添加字段后使用）
    // @Select("SELECT COUNT(*) FROM room WHERE room_number = #{roomNumber}")
    // boolean existsByRoomNumber(String roomNumber);

}