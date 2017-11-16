package com.allcheer.bpos.entity;

public class GateParamConfigDO {
    private String gateId;

    private String paramKey;

    private String paramValue;

    private String parmaDesc;

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId == null ? null : gateId.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getParmaDesc() {
        return parmaDesc;
    }

    public void setParmaDesc(String parmaDesc) {
        this.parmaDesc = parmaDesc == null ? null : parmaDesc.trim();
    }
}