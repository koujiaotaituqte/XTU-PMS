package com.example.controller;

import com.example.common.Result;
import com.example.entity.RoomType;
import com.example.service.RoomTypeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    @PostMapping("/add")
    public Result add(@RequestBody RoomType roomType) {
        if (roomType.getId() == null) {
            return Result.error("400","ID不能为空");
        }
        if (roomTypeService.existsById(roomType.getId())) {
            return Result.error("400", "房型ID已存在");
        }
        roomTypeService.add(roomType);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody RoomType roomType) {
        roomTypeService.update(roomType);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        roomTypeService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result selectAll(RoomType roomType) {
        List<RoomType> roomTypeList = roomTypeService.selectAll(roomType);
        return Result.success(roomTypeList);
    }

    @GetMapping("/selectPage")
    public Result selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            RoomType roomType) {
        PageInfo<RoomType> pageInfo = roomTypeService.selectPage(pageNum, pageSize, roomType);
        return Result.success(pageInfo);
    }
}