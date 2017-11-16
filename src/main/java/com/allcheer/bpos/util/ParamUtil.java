package com.allcheer.bpos.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public class ParamUtil {
	public static Map<String, String> getParamMap(String sText) {
		Map<String, String> resMap = new HashMap<String, String>();
		if ((sText == null) || ("".equals(sText))) {
			return resMap;
		}
		String sParamsText = getParamText(sText);
		String[] arrParam = sParamsText.split("&");
		for (String sTemp : arrParam) {
			String sKey = null;
			String sVal = null;
			if (sTemp.indexOf("=") != -1) {
				sKey = sTemp.split("=")[0];
				if (sTemp.split("=").length == 2)
					sVal = sTemp.split("=")[1];
				else {
					sVal = "";
				}
				resMap.put(sKey, sVal);
			}
		}
		return resMap;
	}

	public static String getParamText(String sText) {
		String sRes = null;
		if ((sText == null) || ("".equals(sText))) {
			return sRes;
		}
		String[] arr = sText.split("&sign=");
		sRes = arr[0];
		return sRes;
	}

	public static String getSign(String sText) {
		String sRes = null;
		if ((sText == null) || ("".equals(sText)) || (sText.toUpperCase().indexOf("&SIGN") == -1)) {
			return sRes;
		}
		String[] arr = sText.split("&");
		sRes = arr[(arr.length - 1)];
		if (sRes.indexOf("=") != -1) {
			sRes = sRes.replace(sRes.split("=")[0] + "=", "");
		}
		return sRes;
	}

	/**
	 * 为所有属性设置初值， 进行数据库插入之前调用 ，将当前对象所有为null或空字符串的属性赋默认值
	 */
	@SuppressWarnings("rawtypes")
	public static void fillDefaultValues(Object obj) {
		try {
			for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(obj)) {
				Class clazz = pd.getPropertyType();
				Method rm = pd.getReadMethod();
				Method wm = pd.getWriteMethod();
				if (rm.invoke(obj) == null || "".equals(rm.invoke(obj))) {
					if (clazz.equals(String.class)) {
						wm.invoke(obj, " ");
					} else if (clazz.equals(Short.class)) {
						wm.invoke(obj, Short.valueOf((short) 0));
					} else if (clazz.equals(Integer.class)) {
						wm.invoke(obj, Integer.valueOf(0));
					} else if (clazz.equals(BigDecimal.class)) {
						wm.invoke(obj, BigDecimal.valueOf(0));
					} else if (clazz.equals(Long.class)) {
						wm.invoke(obj, Long.valueOf(0));
					} else if (clazz.equals(List.class)) {
						wm.invoke(obj, new ArrayList());
					} else if (clazz.equals(Map.class)) {
						wm.invoke(obj, new HashMap());
					}
				}else{
					if (clazz.equals(String.class)) {
						if(!"".equals(rm.invoke(obj).toString().trim())){
							wm.invoke(obj, rm.invoke(obj).toString().trim());
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("属性设置出错", e);
		}
	}
}
