package com.allcheer.bpos.domain;

/**
 * Created by fireWorks on 2016/3/10.
 */
public class ChannelInfoBO extends BaseBO {

    private String gateId;

    private String posMerId;

    private String posTermId;

    private String batchId;

    private String instId;

    private String instName;

    private String mainKey;

    private String macKey;

    private String pinKey;

    private String tdKey;

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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    public String getMainKey() {
        return mainKey;
    }

    public void setMainKey(String mainKey) {
        this.mainKey = mainKey == null ? null : mainKey.trim();
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey == null ? null : macKey.trim();
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey == null ? null : pinKey.trim();
    }

    public String getTdKey() {
        return tdKey;
    }

    public void setTdKey(String tdKey) {
        this.tdKey = tdKey == null ? null : tdKey.trim();
    }
}
