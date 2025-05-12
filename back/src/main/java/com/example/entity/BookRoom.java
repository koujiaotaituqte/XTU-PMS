package com.example.entity;

import java.time.LocalDate;

public class BookRoom {
    private Integer id;
    private Integer roomtypeId;
    private Integer userId;
    private LocalDate startTime;
    private Integer day;
    private LocalDate endTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}