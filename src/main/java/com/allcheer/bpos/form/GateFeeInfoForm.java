package com.allcheer.bpos.form;

public class GateFeeInfoForm extends BaseForm<GateFeeInfoForm>{

	private String gateId;

	private String posMerId;

	private String posTermId;

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getPosMerId() {
		return posMerId;
	}

	public void setPosMerId(String posMerId) {
		this.posMerId = posMerId;
	}

	public String getPosTermId() {
		return posTermId;
	}

	public void setPosTermId(String posTermId) {
		this.posTermId = posTermId;
	}

}
