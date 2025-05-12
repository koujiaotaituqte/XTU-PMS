package com.example.service;

import com.example.entity.BookRoom;
import com.example.exception.CustomException;
import com.example.mapper.BookRoomMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookRoomService {

    @Resource
    private BookRoomMapper bookRoomMapper;

    public void add(BookRoom bookRoom) {


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