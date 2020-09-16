package com.example.demo.model;

public class ECNChangeLog {
    private String id;

    private String oldEcn;

    private String newEcn;

    private Integer changeState;

    private String cityOpinion;

    private String rootOpinion;

    private String handleTime;

    private Integer resultState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Integer getChangeState() {
        return changeState;
    }

    public void setChangeState(Integer changeState) {
        this.changeState = changeState;
    }

    public String getCityOpinion() {
        return cityOpinion;
    }

    public void setCityOpinion(String cityOpinion) {
        this.cityOpinion = cityOpinion == null ? null : cityOpinion.trim();
    }

    public String getRootOpinion() {
        return rootOpinion;
    }

    public void setRootOpinion(String rootOpinion) {
        this.rootOpinion = rootOpinion == null ? null : rootOpinion.trim();
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime == null ? null : handleTime.trim();
    }

    public Integer getResultState() {
        return resultState;
    }

    public void setResultState(Integer resultState) {
        this.resultState = resultState;
    }
}