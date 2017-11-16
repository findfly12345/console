package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.controller.MerInfoController;
import com.allcheer.bpos.entity.TblAddressCodeDO;
import com.allcheer.bpos.entity.TblAddressCodeDOExample;
import com.allcheer.bpos.mapper.TblAddressCodeDOMapper;
import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

	private final static Logger logger = LoggerFactory.getLogger(MerInfoController.class);

	@Autowired
	TblAddressCodeDOMapper AddressCodeDOMapper;

	/**
	 * 获取正确的地址编码
	 * @param province
	 * @param city
	 * @param area
	 * @return
	 */
	@Override
	public Map<String, String> getCorrectAddressCodes(String province, String city, String area) {

		Map<String, String> resultMap = new HashMap<String, String>();

		String provinceCode = "";
		String cityCode = "";
		String areaCode = "";

		logger.info("A校验地区");
		String CodeOrString = isCodeOrAddress(area);
		if (CodeOrString.equals("CODE")) {

			TblAddressCodeDOExample areaCodeDOExample = new TblAddressCodeDOExample();
			areaCodeDOExample.createCriteria().andAreaCodeEqualTo(area); 
			List<TblAddressCodeDO> AreaCodeList = AddressCodeDOMapper.selectByExample(areaCodeDOExample);
			if(AreaCodeList == null || AreaCodeList.size() == 0 || AreaCodeList.isEmpty()){
				resultMap.put("ERROR", "A地区编码编码不正确");
				return resultMap;				
			}
			String areaUpCode = AreaCodeList.get(0).getUpAreaCode();
			
			TblAddressCodeDOExample cityCodeDOExample = new TblAddressCodeDOExample();
			cityCodeDOExample.createCriteria().andAreaCodeEqualTo(areaUpCode); 
			List<TblAddressCodeDO> cityCodeList = AddressCodeDOMapper.selectByExample(cityCodeDOExample);			
			if(cityCodeList == null || cityCodeList.size() ==0 || cityCodeList.isEmpty()){
				resultMap.put("ERROR", "A城市编码编码不正确");
				return resultMap;				
			}			
			String cityUpCode = cityCodeList.get(0).getUpAreaCode();
			
			TblAddressCodeDOExample provinceCodeDOExample = new TblAddressCodeDOExample();
			provinceCodeDOExample.createCriteria().andAreaCodeEqualTo(cityUpCode); 
			List<TblAddressCodeDO> provinceCodeList = AddressCodeDOMapper.selectByExample(provinceCodeDOExample);			
			if(provinceCodeList == null || provinceCodeList.size() == 0 || provinceCodeList.isEmpty()){
				resultMap.put("ERROR", "A省市编码编码不正确");
				return resultMap;				
			}					
			
			String sysAreaCode = area;
			String sysCityCode = areaUpCode;
			String sysProvinceCode = cityUpCode;
			
			if(StringUtils.isBlank(province) && StringUtils.isBlank(city)){
				provinceCode = sysProvinceCode;
				cityCode = sysCityCode;
				areaCode = sysAreaCode;	
			} else {
				
				if(!sysProvinceCode.equals(province)){
					resultMap.put("ERROR", "A省市编码编码不匹配");
					return resultMap;					
				}
				if(!sysCityCode.equals(city)){
					resultMap.put("ERROR", "A城市编码编码不匹配");
					return resultMap;					
				}				
				provinceCode = sysProvinceCode;
				cityCode = sysCityCode;
				areaCode = sysAreaCode;							
			}
		} else {

			logger.info("校验省市");
			String CodeOrStringP = isCodeOrAddress(province);
			if (CodeOrStringP.equals("CODE")) {
				Boolean isCorrect = isCodeCorrect(province);
				if (!isCorrect) {
					resultMap.put("ERROR", "省份编码不正确");
					return resultMap;
				}
				provinceCode = province;
			} else {
				provinceCode = mapAddressToCode(province, Constant.ADDRESS_PROVINCE, "1");
				if (StringUtils.isBlank(provinceCode)) {
					resultMap.put("ERROR", "省份地址不正确");
					return resultMap;
				}
			}

			logger.info("校验城市");
			String CodeOrStringC = isCodeOrAddress(city);
			if (CodeOrStringC.equals("CODE")) {
				Boolean isCorrect = isCodeCorrect(city);
				if (!isCorrect) {
					resultMap.put("ERROR", "城市编码不正确");
					return resultMap;
				}
				cityCode = city;
			} else {
				cityCode = mapAddressToCode(city, Constant.ADDRESS_CITY, provinceCode);
				if (StringUtils.isBlank(cityCode)) {
					resultMap.put("ERROR", "城市地址不正确");
					return resultMap;
				}
			}

			logger.info("校验地区");
			String CodeOrStringA = isCodeOrAddress(area);
			if (CodeOrStringA.equals("CODE")) {
				Boolean isCorrect = isCodeCorrect(area);
				if (!isCorrect) {
					resultMap.put("ERROR", "地区编码不正确");
					return resultMap;
				}
				areaCode = area;
			} else {
				areaCode = mapAddressToCode(area, Constant.ADDRESS_AREA, cityCode);
				if (StringUtils.isBlank(areaCode)) {
					resultMap.put("ERROR", "地区地址不正确");
					return resultMap;
				}
			}

		}

		resultMap.put("PROVINCE", provinceCode);
		resultMap.put("CITY", cityCode);
		resultMap.put("AREA", areaCode);
		return resultMap;
	}

	/**
	 * 匹配地址编码
	 * 
	 * @param addressStr
	 * @return
	 */
	@Override
	public String mapAddressToCode(String addressStr, String addressLevel, String upAreaCode) {

		String addressCode = "";

		TblAddressCodeDOExample AddressCodeDOExample = new TblAddressCodeDOExample();
		AddressCodeDOExample.createCriteria().andAreaNameLike("%" + addressStr + "%").andAreaTypeEqualTo(addressLevel)
				.andUpAreaCodeEqualTo(upAreaCode);
		List<TblAddressCodeDO> AddressCodeList = AddressCodeDOMapper.selectByExample(AddressCodeDOExample);

		if (AddressCodeList != null && AddressCodeList.size() > 0) {
			addressCode = AddressCodeList.get(0).getAreaCode();
		}

		return addressCode;
	}

	/**
	 * 判断地址代码是否正确
	 * 
	 * @return
	 */
	@Override
	public Boolean isCodeCorrect(String codeStr) {

		TblAddressCodeDOExample AddressCodeDOExample = new TblAddressCodeDOExample();
		AddressCodeDOExample.createCriteria().andAreaCodeEqualTo(codeStr);
		List<TblAddressCodeDO> AddressCodeList = AddressCodeDOMapper.selectByExample(AddressCodeDOExample);

		if (AddressCodeList != null && AddressCodeList.size() > 0) {
			return true;
		}

		return false;

	}

	/**
	 * 判断地址是编码, 还是真实地址名
	 * 
	 * @param argStr
	 * @return
	 */
	@Override
	public String isCodeOrAddress(String argStr) {

		String typeResult = "";

		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]*)?$");

		Matcher match = pattern.matcher(argStr);

		if (match.matches()) {
			typeResult = "CODE";
		} else {
			typeResult = "ADDRESS";
		}

		return typeResult;
	}

	/**
	 * 方法测试
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		AddressServiceImpl addressServiceImpl = new AddressServiceImpl();

		addressServiceImpl.isCodeCorrect("22发地方");

	}
}
