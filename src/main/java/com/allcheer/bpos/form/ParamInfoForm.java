package com.allcheer.bpos.form;

public class ParamInfoForm extends BaseForm<ParamInfoForm> {
	public String getParamNum() {
		return paramNum;
	}
	public void setParamNum(String paramNum) {
		this.paramNum = paramNum;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamStat() {
		return paramStat;
	}
	public void setParamStat(String paramStat) {
		this.paramStat = paramStat;
	}
	private String paramNum;
	private String paramName;
	private String paramValue;
	private String paramStat;
}
