package com.example.demo.model;

public class TwoClassApply {
    private Integer id;

    private String graduateId;

    private String examCardNum;

    private String oldCourseCode;

    private String newCourseCode;

    private String cityCode;

    private String regionCode;

    private Integer applyState;

    private Integer result;

    private String startDate;

    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGraduateId() {
        return graduateId;
    }

    public void setGraduateId(String graduateId) {
        this.graduateId = graduateId == null ? null : graduateId.trim();
    }

    public String getExamCardNum() {
        return examCardNum;
    }

    public void setExamCardNum(String examCardNum) {
        this.examCardNum = examCardNum == null ? null : examCardNum.trim();
    }

    public String getOldCourseCode() {
        return oldCourseCode;
    }

    public void setOldCourseCode(String oldCourseCode) {
        this.oldCourseCode = oldCourseCode == null ? null : oldCourseCode.trim();
    }

    public String getNewCourseCode() {
        return newCourseCode;
    }

    public void setNewCourseCode(String newCourseCode) {
        this.newCourseCode = newCourseCode == null ? null : newCourseCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    @Override
    public String toString() {
        return "TwoClassApply{" +
                "id=" + id +
                ", graduateId='" + graduateId + '\'' +
                ", examCardNum='" + examCardNum + '\'' +
                ", oldCourseCode='" + oldCourseCode + '\'' +
                ", newCourseCode='" + newCourseCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", applyState=" + applyState +
                ", result=" + result +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}