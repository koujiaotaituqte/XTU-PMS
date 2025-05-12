package com.example.controller;

import com.example.common.Result;
import com.example.entity.BookRoom;
import com.example.service.BookRoomService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookroom")
public class BookRoomController {

    @Resource
    private BookRoomService bookRoomService;

    @PostMapping("/add")
    public Result add(@RequestBody BookRoom bookRoom) {
        bookRoomService.add(bookRoom);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody BookRoom bookRoom) {
        bookRoomService.update(bookRoom);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        bookRoomService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(BookRoom bookRoom) {
        List<BookRoom> bookRoomList = bookRoomService.selectAll(bookRoom);
        return Result.success(bookRoomList);
    }

    @GetMapping("/selectPage")
    public Result selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            BookRoom bookRoom) {
        PageInfo<BookRoom> pageInfo = bookRoomService.selectPage(pageNum, pageSize, bookRoom);
        return Result.success(pageInfo);
    }
}