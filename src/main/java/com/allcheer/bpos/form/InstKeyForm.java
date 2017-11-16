package com.allcheer.bpos.form;

public class InstKeyForm extends BaseForm<InstKeyForm>{
	
	private String instId;

    private String instPrimaryKey;

    private String instSignStatus;

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstPrimaryKey() {
		return instPrimaryKey;
	}

	public void setInstPrimaryKey(String instPrimaryKey) {
		this.instPrimaryKey = instPrimaryKey;
	}

	public String getInstSignStatus() {
		return instSignStatus;
	}

	public void setInstSignStatus(String instSignStatus) {
		this.instSignStatus = instSignStatus;
	}
	
}
