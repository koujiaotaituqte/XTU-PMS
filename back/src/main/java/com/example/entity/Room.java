package com.example.entity;

import java.math.BigDecimal;

public class Room {
    private Integer id;
    private Integer roomtypeId;  // 对应roomtype_id字段
    private Integer status;      // 0-空闲

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
