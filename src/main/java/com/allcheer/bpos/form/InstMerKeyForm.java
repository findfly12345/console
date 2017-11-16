package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/3/3.
 */
public class InstMerKeyForm extends BaseForm {

    private String instId;

    private String instMerId;

    private String instMerTermId;

    private String instType;

    private String primaryKey;

    private String statusFlag;


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

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType == null ? null : instType.trim();
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey == null ? null : primaryKey.trim();
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag == null ? null : statusFlag.trim();
    }


}
