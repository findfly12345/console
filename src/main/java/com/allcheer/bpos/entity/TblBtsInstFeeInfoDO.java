package com.allcheer.bpos.entity;

public class TblBtsInstFeeInfoDO {
    private String instId;

    private String instMerId;

    private String instTermId;

    private String calcMode;

    private String remark;

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

    public String getCalcMode() {
        return calcMode;
    }

    public void setCalcMode(String calcMode) {
        this.calcMode = calcMode == null ? null : calcMode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}