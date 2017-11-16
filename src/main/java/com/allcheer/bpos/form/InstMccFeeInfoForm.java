package com.allcheer.bpos.form;

public class InstMccFeeInfoForm extends BaseForm<InstMccFeeInfoForm>{
	
	private String instId;

	private String mccType;

	private String calcMode;

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

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

}
