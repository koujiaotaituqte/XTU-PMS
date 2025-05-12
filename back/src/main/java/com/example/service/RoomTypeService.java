package com.example.service;

import com.example.entity.RoomType;
import com.example.exception.CustomException;
import com.example.mapper.RoomTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Transactional
    public void add(RoomType roomType) {
        // 校验房型名称唯一性
        if (roomTypeMapper.existsByTitle(roomType.getTitle())) {
            throw new CustomException("房型名称已存在");
        }
        roomTypeMapper.insert(roomType);
    }

    @Transactional
    public void update(RoomType roomType) {
        roomTypeMapper.updateById(roomType);
    }

    @Transactional
    public void deleteById(Integer id) {
        // 检查是否关联房间
        if (roomTypeMapper.hasRooms(id) > 0) {
            throw new CustomException("房型已被房间使用，无法删除");
        }
        roomTypeMapper.deleteById(id);
    }
    @Transactional(readOnly = true)
    public List<RoomType> selectAll(RoomType roomType) {
        return roomTypeMapper.selectAll(roomType);
    }
    @Transactional(readOnly = true)
    public PageInfo<RoomType> selectPage(Integer pageNum, Integer pageSize, RoomType roomType) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoomType> list = roomTypeMapper.selectAll(roomType);
        return new PageInfo<>(list);
    }

    public boolean existsById(Integer id) {
        if (roomTypeMapper.existsByTitle(id)){
            throw new CustomException("房型名称已存在");
        }
        return false;
    }
}