package com.allcheer.bpos.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
	
	private static final String KEY_MD5 = "md5";
	private static final String CHARS = "utf8";

	public static String encryptMD5(String data) {
		return data;
		//TODO 测试期间关闭加密
//		try{
//			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
//			md5.update(data.getBytes(CHARS));
//			byte[] digest = md5.digest();
//			return new String(digest,CHARS);
//		}catch(Exception e ){
//			logger.error("md5加密错误",e);
//		}
//		return null;
	}
}
