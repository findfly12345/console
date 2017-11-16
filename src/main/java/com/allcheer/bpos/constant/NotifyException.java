/**
 * 
 */
package com.allcheer.bpos.constant;

import com.allcheer.bpos.entity.Enum.ErrorRespEnum;

/**
 * @author Administrator
 *
 */
public class NotifyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -930457678038938439L;
	ErrorRespEnum errorResp;
	String errorMesg;

	public NotifyException(ErrorRespEnum errorResp, String errorMesg) {
		this.errorResp = errorResp;
		this.errorMesg = errorMesg;
	}

	public ErrorRespEnum getErrorResp() {
		return errorResp;
	}

	public String getErrorMesg() {
		return errorMesg;
	}
}
