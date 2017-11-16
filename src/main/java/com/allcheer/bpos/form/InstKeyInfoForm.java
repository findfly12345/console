package com.allcheer.bpos.form;

public class InstKeyInfoForm extends BaseForm<InstKeyInfoForm> {

	public String getInstCode() {
		return instCode;
	}
	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}
	public String getInstMainKey() {
		return instMainKey;
	}
	public void setInstMainKey(String instMainKey) {
		this.instMainKey = instMainKey;
	}
	public String getInstPinKey() {
		return instPinKey;
	}
	public void setInstPinKey(String instPinKey) {
		this.instPinKey = instPinKey;
	}
	public String getInstMacKey() {
		return instMacKey;
	}
	public void setInstMacKey(String instMacKey) {
		this.instMacKey = instMacKey;
	}
	public String getInstTdKey() {
		return instTdKey;
	}
	public void setInstTdKey(String instTdKey) {
		this.instTdKey = instTdKey;
	}
	public String getLoginStat() {
		return loginStat;
	}
	public void setLoginStat(String loginStat) {
		this.loginStat = loginStat;
	}
	private String instCode;
	private String instMainKey;
	private String instPinKey;
	private String instMacKey;
	private String instTdKey;
	private String loginStat;

}
