package com.allcheer.bpos.entity;

public class TblBtsInstMccFeeInfoDO {
    private String instId;

    private String mccType;

    private String calcMode;

    private String remark;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getMccType() {
        return mccType;
    }

    public void setMccType(String mccType) {
        this.mccType = mccType == null ? null : mccType.trim();
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