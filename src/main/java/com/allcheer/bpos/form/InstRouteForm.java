package com.allcheer.bpos.form;

public class InstRouteForm extends BaseForm<InstRouteForm>{

    private Integer routeSeq;

    private String instCode;

    private String instMerId;

    private String instMerTermId;

    private String gateId;
    
    private String bankTermId;

    private String bankMerId;

	public String getBankTermId() {
		return bankTermId;
	}

	public void setBankTermId(String bankTermId) {
		this.bankTermId = bankTermId;
	}

	public String getBankMerId() {
		return bankMerId;
	}

	public void setBankMerId(String bankMerId) {
		this.bankMerId = bankMerId;
	}

	public Integer getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(Integer routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public String getInstMerId() {
		return instMerId;
	}

	public void setInstMerId(String instMerId) {
		this.instMerId = instMerId;
	}

	public String getInstMerTermId() {
		return instMerTermId;
	}

	public void setInstMerTermId(String instMerTermId) {
		this.instMerTermId = instMerTermId;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

}
