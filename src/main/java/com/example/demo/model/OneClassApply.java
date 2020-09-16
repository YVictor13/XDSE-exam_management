package com.example.demo.model;

public class OneClassApply {
    private Integer id;

    private String oldEcn;

    private String newEcn;

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

    public String getOldEcn() {
        return oldEcn;
    }

    public void setOldEcn(String oldEcn) {
        this.oldEcn = oldEcn == null ? null : oldEcn.trim();
    }

    public String getNewEcn() {
        return newEcn;
    }

    public void setNewEcn(String newEcn) {
        this.newEcn = newEcn == null ? null : newEcn.trim();
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
        return "OneClassApply{" +
                "id=" + id +
                ", oldEcn='" + oldEcn + '\'' +
                ", newEcn='" + newEcn + '\'' +
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