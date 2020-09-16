package com.example.demo.model.helper;

/**
 * @author:guan
 * @2020/9/8 10:25
 * 文件信息：
 */
public class StuInfoInOutHelper {
    private String examCardNum;
    private String name;
    private String rollOutCity;
    private String rollInCity;
    private String endTime;



    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollOutCity() {
        return rollOutCity;
    }

    public void setRollOutCity(String rollOutCity) {
        this.rollOutCity = rollOutCity;
    }

    public String getRollInCity() {
        return rollInCity;
    }

    public void setRollInCity(String rollInCity) {
        this.rollInCity = rollInCity;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "StuInfoInOutHelper{" +
                "examCardNum='" + examCardNum + '\'' +
                ", name='" + name + '\'' +
                ", rollOutCity='" + rollOutCity + '\'' +
                ", rollInCity='" + rollInCity + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
