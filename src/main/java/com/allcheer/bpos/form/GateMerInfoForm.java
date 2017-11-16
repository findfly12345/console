package com.allcheer.bpos.form;

public class GateMerInfoForm extends BaseForm<GateMerInfoForm>{

	private String gateId;

	private String posMerId;

	private String posTermId;

	private String posMerType;

	private String posMerStat;

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

	public String getPosMerType() {
		return posMerType;
	}

	public void setPosMerType(String posMerType) {
		this.posMerType = posMerType;
	}

	public String getPosMerStat() {
		return posMerStat;
	}

	public void setPosMerStat(String posMerStat) {
		this.posMerStat = posMerStat;
	}

}
