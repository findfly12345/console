package com.allcheer.bpos.form;

public class GateRouteForm extends BaseForm<GateRouteForm> {

	private String gateId;
	private String gateType;
	private String gateDesp;
	private String gateFee;
	private String gateStat;
	private String remark;

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getGateType() {
		return gateType;
	}

	public void setGateType(String gateType) {
		this.gateType = gateType;
	}

	public String getGateDesp() {
		return gateDesp;
	}

	public void setGateDesp(String gateDesp) {
		this.gateDesp = gateDesp;
	}

	public String getGateFee() {
		return gateFee;
	}

	public void setGateFee(String gateFee) {
		this.gateFee = gateFee;
	}

	public String getGateStat() {
		return gateStat;
	}

	public void setGateStat(String gateStat) {
		this.gateStat = gateStat;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
