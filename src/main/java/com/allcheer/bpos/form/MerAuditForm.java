package com.allcheer.bpos.form;

import com.allcheer.bpos.entity.TblMerAuditRecordDO;

import java.util.List;

/**
 * @author husf
 */
public class MerAuditForm extends BaseForm {


    private String phone;

    private String merName;

    private String merId;

    private String applyStartAt;

    private String applyEndAt;
    
    private String auditResult;

    private List<TblMerAuditRecordDO> tblMerAuditRecordDOList;

    public List<TblMerAuditRecordDO> getTblMerAuditRecordDOList() {
        return tblMerAuditRecordDOList;
    }

    public void setTblMerAuditRecordDOList(List<TblMerAuditRecordDO> tblMerAuditRecordDOList) {
        this.tblMerAuditRecordDOList = tblMerAuditRecordDOList;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getApplyStartAt() {
        return applyStartAt;
    }

    public void setApplyStartAt(String applyStartAt) {
        this.applyStartAt = applyStartAt;
    }

    public String getApplyEndAt() {
        return applyEndAt;
    }

    public void setApplyEndAt(String applyEndAt) {
        this.applyEndAt = applyEndAt;
    }
    
    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

	/**
	 * @return the auditResult
	 */
	public String getAuditResult() {
		return auditResult;
	}

	/**
	 * @param auditResult the auditResult to set
	 */
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}    
}
