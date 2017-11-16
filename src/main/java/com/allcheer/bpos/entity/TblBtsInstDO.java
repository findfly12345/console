package com.allcheer.bpos.entity;

import com.allcheer.bpos.form.BaseForm;

public class TblBtsInstDO extends BaseForm {
    private String instCode;

    private String instType;

    private String instName;

    private String instEmail;

    private String instTelphome;

    private String createById;

    private String updateById;

    private String createTime;

    private String updateTime;

    private String mccType;
    private String calcMode;

    public String getMccType() {
        return mccType;
    }

    public void setMccType(String mccType) {
        this.mccType = mccType;
    }

    public String getCalcMode() {
        return calcMode;
    }

    public void setCalcMode(String calcMode) {
        this.calcMode = calcMode;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode == null ? null : instCode.trim();
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType == null ? null : instType.trim();
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName == null ? null : instName.trim();
    }

    public String getInstEmail() {
        return instEmail;
    }

    public void setInstEmail(String instEmail) {
        this.instEmail = instEmail == null ? null : instEmail.trim();
    }

    public String getInstTelphome() {
        return instTelphome;
    }

    public void setInstTelphome(String instTelphome) {
        this.instTelphome = instTelphome == null ? null : instTelphome.trim();
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById == null ? null : createById.trim();
    }

    public String getUpdateById() {
        return updateById;
    }

    public void setUpdateById(String updateById) {
        this.updateById = updateById == null ? null : updateById.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}