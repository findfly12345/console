package com.allcheer.bpos.form;

public class RouteControlForm extends BaseForm<RouteControlForm> {

	public String getPosRouteSeq() {
		return posRouteSeq;
	}

	public void setPosRouteSeq(String posRouteSeq) {
		this.posRouteSeq = posRouteSeq;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getDestGateId() {
		return destGateId;
	}

	public void setDestGateId(String destGateId) {
		this.destGateId = destGateId;
	}

	public String getDestMerId() {
		return destMerId;
	}

	public void setDestMerId(String destMerId) {
		this.destMerId = destMerId;
	}

	public String getDestTermId() {
		return destTermId;
	}

	public void setDestTermId(String destTermId) {
		this.destTermId = destTermId;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String posRouteSeq;
	private String instId;
	private String merId;
	private String termId;
	private String destGateId;
	private String destMerId;
	private String destTermId;
	private String stat;
	private String remark;

}
