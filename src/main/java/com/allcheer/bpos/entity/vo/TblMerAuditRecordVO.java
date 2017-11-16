package com.allcheer.bpos.entity.vo;

/**
 * Created by hsf-pc on 2017/2/14.
 */
public class TblMerAuditRecordVO {

    private String auditId;
    private String merId;
    private String memberId;
    private String agentName;
    private String idno;
    private String merName;
    private String phone;
    private String applyAt;
    private String merStat;  
    private String auditResult;
    private String lastAt;
    private String userName;
    private String errorFields;
    private String remark;    
    
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(String applyAt) {
        this.applyAt = applyAt;
    }


    public String getLastAt() {
        return lastAt;
    }

    public void setLastAt(String lastAt) {
        this.lastAt = lastAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorFields() {
        return errorFields;
    }

    public void setErrorFields(String errorFields) {
        this.errorFields = errorFields;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMerStat() {
        return merStat;
    }

    public void setMerStat(String merStat) {
        this.merStat = merStat;
    }
    
    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }    
}
