package com.allcheer.bpos.domain;

/**
 * Created by fireWorks on 2016/3/3.
 */
public class InstMerKeyBO extends BaseBO {

    private String instId;

    private String instMerId;

    private String instMerTermId;

    private String primaryKey;

    private String instType;

    private String pinKey;

    private String macKey;

    private String tdKey;

    private String checkStatus;

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

    public String getInstMerTermId() {
        return instMerTermId;
    }

    public void setInstMerTermId(String instMerTermId) {
        this.instMerTermId = instMerTermId == null ? null : instMerTermId.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType == null ? null : instType.trim();
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey == null ? null : pinKey.trim();
    }

    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey == null ? null : macKey.trim();
    }

    public String getTdKey() {
        return tdKey;
    }

    public void setTdKey(String tdKey) {
        this.tdKey = tdKey == null ? null : tdKey.trim();
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }
}
