package com.example.service;

import com.example.entity.Room;
import com.example.exception.CustomException;
import com.example.mapper.RoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {

    private final RoomMapper roomMapper;

    public RoomService(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    public void add(Room room) {
        // 校验房型是否存在
        if (!roomMapper.existsRoomType(room.getRoomtypeId())) {
            throw new CustomException("房型不存在");
        }
        // 校验状态值
        if (room.getStatus() != 0 && room.getStatus() != 1) {
            throw new CustomException("状态值不合法（0-空闲，1-使用中）");
        }
        roomMapper.insert(room);
    }

    public void update(Room room) {
        // 校验房型是否存在
        if (!roomMapper.existsRoomType(room.getRoomtypeId())) {
            throw new CustomException("房型不存在");
        }
        roomMapper.updateById(room);
    }

    public void deleteById(Integer id) {
        roomMapper.deleteById(id);
    }

    public List<Room> selectAll(Room room) {
        return roomMapper.selectAll(room);
    }

    public PageInfo<Room> selectPage(Integer pageNum, Integer pageSize, Room room) {
        PageHelper.startPage(pageNum, pageSize);
        List<Room> list = roomMapper.selectAll(room);
        return new PageInfo<>(list);
    }
}