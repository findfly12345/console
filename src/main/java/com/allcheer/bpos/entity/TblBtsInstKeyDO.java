package com.allcheer.bpos.entity;

public class TblBtsInstKeyDO {
	
    private String instId;

    private String instPrimaryKeyIndex;

    private String instPrimaryKey;

    private String instPinKey;

    private String instMacKey;

    private String instTdKey;

    private String instSignStatus;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
    }

    public String getInstPrimaryKeyIndex() {
        return instPrimaryKeyIndex;
    }

    public void setInstPrimaryKeyIndex(String instPrimaryKeyIndex) {
        this.instPrimaryKeyIndex = instPrimaryKeyIndex == null ? null : instPrimaryKeyIndex.trim();
    }

    public String getInstPrimaryKey() {
        return instPrimaryKey;
    }

    public void setInstPrimaryKey(String instPrimaryKey) {
        this.instPrimaryKey = instPrimaryKey == null ? null : instPrimaryKey.trim();
    }

    public String getInstPinKey() {
        return instPinKey;
    }

    public void setInstPinKey(String instPinKey) {
        this.instPinKey = instPinKey == null ? null : instPinKey.trim();
    }

    public String getInstMacKey() {
        return instMacKey;
    }

    public void setInstMacKey(String instMacKey) {
        this.instMacKey = instMacKey == null ? null : instMacKey.trim();
    }

    public String getInstTdKey() {
        return instTdKey;
    }

    public void setInstTdKey(String instTdKey) {
        this.instTdKey = instTdKey == null ? null : instTdKey.trim();
    }

    public String getInstSignStatus() {
        return instSignStatus;
    }

    public void setInstSignStatus(String instSignStatus) {
        this.instSignStatus = instSignStatus == null ? null : instSignStatus.trim();
    }
}