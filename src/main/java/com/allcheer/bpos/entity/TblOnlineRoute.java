package com.allcheer.bpos.entity;

public class TblOnlineRoute {
    private String onlineRouteSeq;

    private String instId;

    private String merId;

    private String gateLevel;

    private String destGateId;

    private String destMerId;

    private String feeRate;

    private String stat;

    public String getOnlineRouteSeq() {
        return onlineRouteSeq;
    }

    public void setOnlineRouteSeq(String onlineRouteSeq) {
        this.onlineRouteSeq = onlineRouteSeq == null ? null : onlineRouteSeq.trim();
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId == null ? null : merId.trim();
    }

    public String getGateLevel() {
        return gateLevel;
    }

    public void setGateLevel(String gateLevel) {
        this.gateLevel = gateLevel == null ? null : gateLevel.trim();
    }

    public String getDestGateId() {
        return destGateId;
    }

    public void setDestGateId(String destGateId) {
        this.destGateId = destGateId == null ? null : destGateId.trim();
    }

    public String getDestMerId() {
        return destMerId;
    }

    public void setDestMerId(String destMerId) {
        this.destMerId = destMerId == null ? null : destMerId.trim();
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate == null ? null : feeRate.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }
}