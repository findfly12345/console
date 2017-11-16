package com.allcheer.bpos.form;

public class InstMerInfoForm extends BaseForm<InstMerInfoForm>{

	private String instId;

	private String instMerId;

	private String instTermId;

	private String instMerType;


	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstMerId() {
		return instMerId;
	}

	public void setInstMerId(String instMerId) {
		this.instMerId = instMerId;
	}

	public String getInstTermId() {
		return instTermId;
	}

	public void setInstTermId(String instTermId) {
		this.instTermId = instTermId;
	}

	public String getInstMerType() {
		return instMerType;
	}

	public void setInstMerType(String instMerType) {
		this.instMerType = instMerType;
	}
}
