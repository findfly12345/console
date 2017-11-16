package com.allcheer.bpos.entity;

public class TblFeeInfoDO {
	
    private String gateId;

    private String posMerId;

    private String posTermId;

    private String calcMode;

    private String remark;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    public String getPosMerId() {
        return posMerId;
    }

    public void setPosMerId(String posMerId) {
        this.posMerId = posMerId == null ? null : posMerId.trim();
    }

    public String getPosTermId() {
        return posTermId;
    }

    public void setPosTermId(String posTermId) {
        this.posTermId = posTermId == null ? null : posTermId.trim();
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