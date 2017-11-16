package com.allcheer.bpos.form;

public class CheckLogForm extends BaseForm<CheckLogForm>{
	private String gateId;
	private String bankCheckDate;

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getBankCheckDate() {
		return bankCheckDate;
	}

	public void setBankCheckDate(String bankCheckDate) {
		this.bankCheckDate = bankCheckDate;
	}
}
