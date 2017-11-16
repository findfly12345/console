package com.allcheer.bpos.service;

import java.util.Map;

/**
 * Lambert
 */
public interface AddressService {
	
	
	
	/**
	 * 获取正确的地址编码
	 * @param province
	 * @param city
	 * @param area
	 * @return
	 */
	public Map<String, String> getCorrectAddressCodes(String province, String city, String area);

	
	/**
	 * 匹配地址编码
	 * 
	 * @param addressStr
	 * @return
	 */
	public String mapAddressToCode(String addressStr, String addressLevel, String upAreaCode);

	/**
	 * 判断地址代码是否正确
	 * 
	 * @return
	 */
	public Boolean isCodeCorrect(String codeStr);

	
	/**
	 * 判断地址是编码, 还是真实地址名
	 * 
	 * @param argStr
	 * @return
	 */
	public String isCodeOrAddress(String argStr);


	
	
	
	
	
	
	

}
