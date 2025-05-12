package com.example.controller;

import com.example.common.Result;
import com.example.entity.Room;
import com.example.service.RoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Result add(@RequestBody Room room) {
        roomService.add(room);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Room room) {
        roomService.update(room);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        roomService.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result findAll(
            @RequestParam(required = false) Integer id, // 新增房间号参数
            @RequestParam(required = false) Integer roomtypeId,
            @RequestParam(required = false) Integer status) {
        Room param = new Room();
        param.setId(id); // 设置房间号
        param.setRoomtypeId(roomtypeId);
        param.setStatus(status);
        return Result.success(roomService.selectAll(param));
    }

    @GetMapping("/page")
    public Result findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer roomtypeId,
            @RequestParam(required = false) Integer status) {
        Room param = new Room();
        param.setRoomtypeId(roomtypeId);
        param.setStatus(status);
        PageInfo<Room> pageInfo = roomService.selectPage(pageNum, pageSize, param);
        return Result.success(pageInfo);
    }
}