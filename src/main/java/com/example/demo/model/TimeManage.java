package com.example.demo.model;

public class TimeManage {
    private Integer userId;

    private String beginTime;

    private String endTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public TimeManage(Integer userId, String beginTime, String endTime) {
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}