package com.allcheer.bpos.form;

public class RiskCtrlReglListForm extends BaseForm<RiskCtrlReglListForm> {

	private String riskNum;
	private String paramNum;
	private String paramVal;
	private String riskFlag;
	private String modOper;
	private String modDate;
	private String remark;

	public String getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(String riskNum) {
		this.riskNum = riskNum;
	}

	public String getParamNum() {
		return paramNum;
	}

	public void setParamNum(String paramNum) {
		this.paramNum = paramNum;
	}

	public String getParamVal() {
		return paramVal;
	}

	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	public String getModOper() {
		return modOper;
	}

	public void setModOper(String modOper) {
		this.modOper = modOper;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
