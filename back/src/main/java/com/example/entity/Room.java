package com.example.entity;

public class Room {
    private Integer id;
    private Integer roomtypeId;
    private String roomtypeTitle; // 新增房型名称字段
    private Integer status;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomtypeId() {
        return roomtypeId;
    }

    public void setRoomtypeId(Integer roomtypeId) {
        this.roomtypeId = roomtypeId;
    }

    public String getRoomtypeTitle() {
        return roomtypeTitle;
    }

    public void setRoomtypeTitle(String roomtypeTitle) {
        this.roomtypeTitle = roomtypeTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}