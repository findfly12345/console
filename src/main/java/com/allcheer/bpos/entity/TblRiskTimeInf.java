package com.allcheer.bpos.entity;

public class TblRiskTimeInf {
    private String riskSeq;

    private String riskType;

    private String riskDetail;

    private String beginTime;

    private String endTime;

    private String stat;

    private String riskRemark;

    public String getRiskSeq() {
        return riskSeq;
    }

    public void setRiskSeq(String riskSeq) {
        this.riskSeq = riskSeq == null ? null : riskSeq.trim();
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType == null ? null : riskType.trim();
    }

    public String getRiskDetail() {
        return riskDetail;
    }

    public void setRiskDetail(String riskDetail) {
        this.riskDetail = riskDetail == null ? null : riskDetail.trim();
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

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }

    public String getRiskRemark() {
        return riskRemark;
    }

    public void setRiskRemark(String riskRemark) {
        this.riskRemark = riskRemark == null ? null : riskRemark.trim();
    }
}