package com.allcheer.bpos.service;

import java.util.Map;

public interface QrcodeService {
	
	/**
	 * 为新增商户创建二维码
	 *  
	 * @param merId
	 * @param termId
	 * @return
	 */
	public Map QrcodeCreate(String instId, String agentId, String merId, String termId);

	
	/**
	 * 保存生产的二维码信息
	 * @param merId
	 * @param instId
	 * @param agentId
	 * @param qrcode
	 * @param qrcodePath
	 * @return
	 */
	public Boolean QrcodeSaving(String merId, String instId, String agentId, String qrcode, String qrcodePath);

    
    
}
