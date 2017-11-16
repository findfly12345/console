package com.allcheer.bpos.entity;

public class TblBtsGateMerInfoDO {
    private String gateId;

    private String posMerId;

    private String posTermId;

    private String posMerType;

    private String posMerDesc;

    private String posMerStat;

    private String posMerProv;

    private String posMerCity;

    private String posMerRemark;

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

    public String getPosMerType() {
        return posMerType;
    }

    public void setPosMerType(String posMerType) {
        this.posMerType = posMerType == null ? null : posMerType.trim();
    }

    public String getPosMerDesc() {
        return posMerDesc;
    }

    public void setPosMerDesc(String posMerDesc) {
        this.posMerDesc = posMerDesc == null ? null : posMerDesc.trim();
    }

    public String getPosMerStat() {
        return posMerStat;
    }

    public void setPosMerStat(String posMerStat) {
        this.posMerStat = posMerStat == null ? null : posMerStat.trim();
    }

    public String getPosMerProv() {
        return posMerProv;
    }

    public void setPosMerProv(String posMerProv) {
        this.posMerProv = posMerProv == null ? null : posMerProv.trim();
    }

    public String getPosMerCity() {
        return posMerCity;
    }

    public void setPosMerCity(String posMerCity) {
        this.posMerCity = posMerCity == null ? null : posMerCity.trim();
    }

    public String getPosMerRemark() {
        return posMerRemark;
    }

    public void setPosMerRemark(String posMerRemark) {
        this.posMerRemark = posMerRemark == null ? null : posMerRemark.trim();
    }
}