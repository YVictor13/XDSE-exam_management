package com.example.demo.model.helper;

/**
 * @author:guan
 * @2020/9/5 11:58
 * 文件信息：
 */
public class GraduateCancelHelper {
    private String examCardNum;
    private String graduateId;
    private String name;
    private String photoPath;

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum;
    }

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "GraduateCancelHelper{" +
                "examCardNum='" + examCardNum + '\'' +
                ", graduateId='" + graduateId + '\'' +
                ", name='" + name + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
