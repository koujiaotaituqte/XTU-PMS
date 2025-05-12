package com.example.service;

import com.example.entity.BookRoom;
import com.example.exception.CustomException;
import com.example.mapper.BookRoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class BookRoomService {

    @Resource
    private BookRoomMapper bookRoomMapper;

    public void add(BookRoom bookRoom) {
        // 检查时间冲突
        if (bookRoomMapper.hasTimeConflict(
                bookRoom.getRoomtypeId(),
                bookRoom.getStartTime(),
                bookRoom.getEndTime()
        )) {
            throw new CustomException("该时间段已被预订");
        }
        bookRoomMapper.insert(bookRoom);
    }

    public void update(BookRoom bookRoom) {
        bookRoomMapper.updateById(bookRoom);
    }

    public void deleteById(Integer id) {
        bookRoomMapper.deleteById(id);
    }

    public List<BookRoom> selectAll(BookRoom bookRoom) {
        return bookRoomMapper.selectAll(bookRoom);
    }

    public PageInfo<BookRoom> selectPage(Integer pageNum, Integer pageSize, BookRoom bookRoom) {
        PageHelper.startPage(pageNum, pageSize);
        List<BookRoom> list = bookRoomMapper.selectAll(bookRoom);
        return new PageInfo<>(list);
    }
}