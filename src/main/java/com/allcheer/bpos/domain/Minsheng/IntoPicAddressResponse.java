package com.allcheer.bpos.domain.Minsheng;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name="ROOT")  
public class IntoPicAddressResponse {
    private String insNum;
    private String merNum;
    private String rspCode;
    private String rspMsg;
	/**
	 * @return the insNum
	 */
	public String getInsNum() {
		return insNum;
	}
	/**
	 * @param insNum the insNum to set
	 */
	public void setInsNum(String insNum) {
		this.insNum = insNum;
	}
	/**
	 * @return the merNum
	 */
	public String getMerNum() {
		return merNum;
	}
	/**
	 * @param merNum the merNum to set
	 */
	public void setMerNum(String merNum) {
		this.merNum = merNum;
	}
	/**
	 * @return the rspCode
	 */
	public String getRspCode() {
		return rspCode;
	}
	/**
	 * @param rspCode the rspCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	/**
	 * @return the rspMsg
	 */
	public String getRspMsg() {
		return rspMsg;
	}
	/**
	 * @param rspMsg the rspMsg to set
	 */
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}


}
