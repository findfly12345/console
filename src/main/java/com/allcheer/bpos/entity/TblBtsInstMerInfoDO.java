package com.allcheer.bpos.entity;

public class TblBtsInstMerInfoDO {
    private String instId;

    private String instMerId;

    private String instTermId;

    private String instMerType;

    private String instMerDesc;

    private String instMerStat;

    private String instMerRemark;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getInstMerId() {
        return instMerId;
    }

    public void setInstMerId(String instMerId) {
        this.instMerId = instMerId == null ? null : instMerId.trim();
    }

    public String getInstTermId() {
        return instTermId;
    }

    public void setInstTermId(String instTermId) {
        this.instTermId = instTermId == null ? null : instTermId.trim();
    }

    public String getInstMerType() {
        return instMerType;
    }

    public void setInstMerType(String instMerType) {
        this.instMerType = instMerType == null ? null : instMerType.trim();
    }

    public String getInstMerDesc() {
        return instMerDesc;
    }

    public void setInstMerDesc(String instMerDesc) {
        this.instMerDesc = instMerDesc == null ? null : instMerDesc.trim();
    }

    public String getInstMerStat() {
        return instMerStat;
    }

    public void setInstMerStat(String instMerStat) {
        this.instMerStat = instMerStat == null ? null : instMerStat.trim();
    }

    public String getInstMerRemark() {
        return instMerRemark;
    }

    public void setInstMerRemark(String instMerRemark) {
        this.instMerRemark = instMerRemark == null ? null : instMerRemark.trim();
    }
}