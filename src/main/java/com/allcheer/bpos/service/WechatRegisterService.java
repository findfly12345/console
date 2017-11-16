package com.allcheer.bpos.service;

import java.util.Map;

/**
 * Created by fireWorks on 2016/11/29.
 */
public interface WechatRegisterService {

	public Map<String, String> registerForMinSheng(String memberId, String channel);

	public boolean isRegisted(String memberId, String gate);

	public Map<String, String> addMerchMapGen(Map<String, String> paramMap, String channel);

	Map<String, String> addHanYinMapGen(Map<String, String> paramMap, String channel);

	Map<String, String> registerForHanyin(String memberId, String channel, String openFlag);

	boolean isHanYinRegisted(String memberId, String gate);

	public Map<String, String> registerForSHBank(String memberId, String channel);

	public boolean isSHBankRegisted(String memberId, String gateId);

	public boolean isEGBankRegisted(String memberId, String gateId);

	/**
	 * 注册到民生银行
	 * 
	 * @param memberId
	 * @param channel
	 * @return
	 */
	public Map<String, String> registerForMSBank(String memberId, String channel);
	
	/**
	 * 注册到恒丰银行
	 * 
	 * @param memberId
	 * @return
	 */
	public Map<String, String> registerForEGBank(String merId, String action);
}
