package com.allcheer.bpos.form;

/**
 * Created by fireWorks on 2016/2/27.
 */
public class InstitutionForm extends BaseForm {
    private String instId;

    private String instCode;

    private String instType;

    private String instName;

    private String instEmail;

    private String instTelphome;
    
    private String btsMerId;

    private String createTime;

    private String updateTime;

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId == null ? null : instId.trim();
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

    public String getBtsMerId() {
        return btsMerId;
    }

    public void setBtsMerId(String btsMerId) {
        this.btsMerId = btsMerId == null ? null : btsMerId.trim();
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

	public String getInstEmail() {
		return instEmail;
	}

	public void setInstEmail(String instEmail) {
		this.instEmail = instEmail;
	}

	public String getInstTelphome() {
		return instTelphome;
	}

	public void setInstTelphome(String instTelphome) {
		this.instTelphome = instTelphome;
	}

}
