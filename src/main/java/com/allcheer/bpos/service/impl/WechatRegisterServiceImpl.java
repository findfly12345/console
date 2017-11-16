package com.allcheer.bpos.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.allcheer.bpos.domain.hanyin.HanYinRegisterRep;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.AgentMerFeeForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.MerChannelPreService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.util.*;
import com.allcheer.bpos.util.constant.CommonConstants;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.controller.MerMaintenanceController;
import com.allcheer.bpos.domain.Minsheng.MinshengDoRegister;
import com.allcheer.bpos.domain.Minsheng.MinshengHeader;
import com.allcheer.bpos.service.WechatRegisterService;
import com.allcheer.bpos.util.Minsheng.CryptoUtil;
import com.allcheer.bpos.util.Minsheng.HttpClient4Util;
import com.allcheer.bpos.util.Minsheng.MsgFormatMS;

/**
 * Created by fireWorks on 2016/11/29.
 */
@Service("wechatRegisterService")
public class WechatRegisterServiceImpl implements WechatRegisterService {

	private final static Logger logger = LoggerFactory.getLogger(MerMaintenanceController.class);

	@Autowired
	TblRouteControlMapper routeControlMapper;

	@Autowired
	TblBtsInstRouteDOMapper tblBtsInstRouteDOMapper;

	@Autowired
	TblInstRouteControlMapper tblInstRouteControlMapper;

	@Autowired
	private MerchInfoService merchInfoService;

	@Autowired
	TblMerInfoDOMapper merInfoDOMapper;

	@Autowired
	TblBtsInstMerInfoDOMapper instMerInfoDOMapper;

	@Autowired
	TblMerChannelPreInfoDOMapper merChannelPreInfoDOMapper;

	@Autowired
	TblBankWxInfoDOMapper bankWxInfoDOMapper;

	@Autowired
	TblMerBankInfoDOMapper tblMerBankInfoDOMapper;

	@Autowired
	TblMerFeeInfoDOMapper tblMerFeeInfoDOMapper;

	@Autowired
	TblInstMerTermRelDOMapper tblInstMerTermRelDOMapper;

	@Autowired
	TblAreaCodeMapper tblAreaCodeMapper;

	@Autowired
	MerAgentService merAgentService;

	@Autowired
	private TblBankInfoMapper tblBankInfoMapper;

	@Autowired
	TblCustAddressDoMapper tblCustAddressDoMapper;

	@Autowired
	MerChannelPreService merChannelPreService;

	@Autowired
	SeqMapper seqMapper;

	public static String weiXinChannel = "UB";
	public static String zhiFuBaoChannel = "UC";
	public static String HAN_YIN_CHANNEL = "U4";
	public static String SHBANK_CHANNEL_WECHAT = "US";
	public static String SHBANK_CHANNEL_ALIPAY = "UT";
	public static String SHBANK_CHANNEL = "SHBANK";
	static Map<String, String> categoryMap = null;
	static Map<String, String> PayWayMap = null;
	static Map<String, String> merIdSuffixMap = null;

	static {
		categoryMap = new ConcurrentHashMap<>();
		categoryMap.put(zhiFuBaoChannel, "2015062600002758");
		categoryMap.put(weiXinChannel, "70");

		PayWayMap = new ConcurrentHashMap<>();
		PayWayMap.put(zhiFuBaoChannel, "ZFBZF");
		PayWayMap.put(weiXinChannel, "WXZF");

		merIdSuffixMap = new ConcurrentHashMap<>();
		merIdSuffixMap.put(zhiFuBaoChannel, "04");
		merIdSuffixMap.put(weiXinChannel, "03");
		merIdSuffixMap.put(SHBANK_CHANNEL_WECHAT, "US");
		merIdSuffixMap.put(SHBANK_CHANNEL_ALIPAY, "UT");

	}

	/**
	 * 调用注册翰银接口
	 *
	 * @param memberId
	 * @param channel
	 * @return
	 */
	public Map<String, String> registerForHanyin(String memberId, String channel, String openFlag) {

		Map<String, String> resultMap = new HashMap<>();
		String url = SystemConstant.HAN_YIN_URL;
		String charset = "UTF-8";
		String errorCode = "";
		String errorMsg = "";

		// 检查是否注册过
		if (openFlag.equals(CommonConstants.OPER_FLAG_A)) {
			Boolean isRegisted = isHanYinRegisted(memberId, channel);
			if (isRegisted) {
				resultMap.put("errorCode", "-1");
				resultMap.put("errorMsg", "该商户已经注册翰银");
				return resultMap;
			}
		}
		HanYinRegisterRep hanYinRegisterRep = new HanYinRegisterRep();
		boolean enriched = enrichMerchInfoHanYin(hanYinRegisterRep, memberId, channel, openFlag);
		if (!enriched) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "没有找到对应商户");
			return resultMap;
		}
		try {
			List<NameValuePair> nvps = new LinkedList<NameValuePair>();
			nvps.add(new BasicNameValuePair("insCode", hanYinRegisterRep.getInsCode()));
			nvps.add(new BasicNameValuePair("insMerchantCode", hanYinRegisterRep.getInsMerchantCode()));
			nvps.add(new BasicNameValuePair("merCode", hanYinRegisterRep.getMerCode()));
			nvps.add(new BasicNameValuePair("merName", hanYinRegisterRep.getMerName()));
			nvps.add(new BasicNameValuePair("realName", hanYinRegisterRep.getRealName()));
			nvps.add(new BasicNameValuePair("merState", hanYinRegisterRep.getMerState()));
			nvps.add(new BasicNameValuePair("merCity", hanYinRegisterRep.getMerCity()));
			nvps.add(new BasicNameValuePair("merAddress", hanYinRegisterRep.getMerAddress()));
			nvps.add(new BasicNameValuePair("certType", hanYinRegisterRep.getCertType()));
			nvps.add(new BasicNameValuePair("certId", hanYinRegisterRep.getCertId()));
			nvps.add(new BasicNameValuePair("mobile", hanYinRegisterRep.getMobile()));
			nvps.add(new BasicNameValuePair("accountId", hanYinRegisterRep.getAccountId()));
			nvps.add(new BasicNameValuePair("accountName", hanYinRegisterRep.getAccountName()));
			nvps.add(new BasicNameValuePair("bankName", hanYinRegisterRep.getBankName()));
			nvps.add(new BasicNameValuePair("bankCode", hanYinRegisterRep.getBankCode()));
			nvps.add(new BasicNameValuePair("operFlag", hanYinRegisterRep.getOperFlag()));
			nvps.add(new BasicNameValuePair("t0drawFee", hanYinRegisterRep.getT0drawFee()));
			nvps.add(new BasicNameValuePair("t0drawRate", hanYinRegisterRep.getT0drawRate()));
			nvps.add(new BasicNameValuePair("t1consFee", hanYinRegisterRep.getT1consFee()));
			nvps.add(new BasicNameValuePair("t1consRate", hanYinRegisterRep.getT1consRate()));
			nvps.add(new BasicNameValuePair("signature", hanYinRegisterRep.getSignature()));

			byte[] respByte = HttpClient4Util.getInstance().doPost(url, null, nvps);
			String respStr = new String(respByte, charset);

			logger.info("返回报文[{}]", new Object[] { respStr });

			if (respStr != null) {
				Gson gson = new Gson();
				resultMap = gson.fromJson(respStr, new TypeToken<Map<String, String>>() {
				}.getType());
				errorCode = resultMap.get("statusCode");
				errorMsg = resultMap.get("statusMsg");
				if (!"00".equals(errorCode)) {
					resultMap.put("errorCode", errorCode);
					resultMap.put("errorMsg", errorMsg);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "注册发生异常");
		}

		return resultMap;

	}

	/**
	 * 翰银 验证该商户是否存在并根据商户号查询商户信息
	 *
	 * @param merId
	 * @param channel
	 * @return
	 */

	private boolean enrichMerchInfoHanYin(HanYinRegisterRep hanYinRegisterRep, String merId, String channel,
			String openFlag) {
		boolean result = false;
		String instMerNumber = SystemConstant.HAN_YIN_INST_MER_NUMBER;
		String instCode = SystemConstant.HAN_YIN_INST_CODE;
		String hanYinKey = SystemConstant.HAN_YIN_KEY;

		TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(merId);
		if (merInfoDO == null) {
			logger.error("没有找到对应商户：{}", merId);
			result = false;
			return result;
		}

		List<TblMerFeeInfoDO> tblMerFeeInfoDOS = merAgentService.queryMerFeeById(merId);
		if (tblMerFeeInfoDOS != null && tblMerFeeInfoDOS.size() > 0) {
			AgentMerFeeForm agentMerFeeForm = new AgentMerFeeForm();
			String feeType = "";
			String[] calc;
			int num = 0;
			for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
				feeType = tblMerFeeInfoDO.getFeeType();
				feeType = feeType.replace("0", "");
				num = Integer.parseInt(feeType);
				calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
				switch (num) {
				case 1:
					agentMerFeeForm.setFee01(calc[0]);
					agentMerFeeForm.setFee01L(calc[1]);
					break;
				case 2:
					agentMerFeeForm.setFee02(calc[0]);
					break;
				case 3:
					agentMerFeeForm.setFee03(calc[0]);
					break;
				case 4:
					agentMerFeeForm.setFee04(calc[0]);
					break;
				case 5:
					agentMerFeeForm.setFee05(calc[0]);
					break;
				case 6:
					agentMerFeeForm.setFee06(calc[0]);
					break;
				case 7:
					agentMerFeeForm.setFee07(calc[0]);
					break;
				case 8:
					agentMerFeeForm.setFee08(calc[0]);
					break;
				default:
					break;
				}
			}

			/*
			 * String code08 =
			 * agentMerFeeForm.getFee08()==null?"0":agentMerFeeForm.getFee08();/
			 * / 提现费率 String code07 =
			 * agentMerFeeForm.getFee07()==null?"0":agentMerFeeForm.getFee07();/
			 * /提现手续费
			 */

			String code08 = agentMerFeeForm.getFee08();// 提现费率
			String code07 = agentMerFeeForm.getFee07();// 提现手续费

			if (StringUtils.isNotBlank(code08)) {
				code08 = AmtUtil.divideWithoutFormat(code08, "100");
				hanYinRegisterRep.setT0drawRate(code08);
			} else {
				hanYinRegisterRep.setT0drawRate("");
			}

			if (StringUtils.isNotBlank(code07)) {
				hanYinRegisterRep.setT0drawFee(code07);
			} else {
				hanYinRegisterRep.setT0drawFee("");
			}

			logger.info("提现费率=========" + code08);
			logger.info("提现手续费=========" + code07);

			/*
			 * if(Double.valueOf(code08)>0){ code08 =
			 * AmtUtil.divideWithoutFormat(code08, "100");
			 * hanYinRegisterRep.setT0drawRate(code08);
			 * hanYinRegisterRep.setT0drawFee("0"); }else{ if
			 * (!StringUtils.isBlank(code07)) {
			 * hanYinRegisterRep.setT0drawFee(code07);
			 * hanYinRegisterRep.setT0drawRate("0"); } }
			 */

			String code02 = agentMerFeeForm.getFee02();

			logger.info("消费费率======" + code02);
			if (!StringUtils.isBlank(code02)) {
				code02 = AmtUtil.divideWithoutFormat(code02, "100");
				hanYinRegisterRep.setT1consRate(code02);
				hanYinRegisterRep.setT1consFee("0");
			}

		}

		hanYinRegisterRep.setInsCode(instCode);// 机构号
		hanYinRegisterRep.setInsMerchantCode(instMerNumber);// 机构商户编号
		hanYinRegisterRep.setMerCode(merId);// 商户号
		hanYinRegisterRep.setMerName(merInfoDO.getMerName());// 商户名称
		hanYinRegisterRep.setRealName(merInfoDO.getLegalPerson());// 姓名

		hanYinRegisterRep.setMerAddress(merInfoDO.getMerAddress());// 商户所在详细地址

		if ("0".equals(merInfoDO.getLegalPersonCertType())) {
			hanYinRegisterRep.setCertType("01");// 证件类型
		}

		hanYinRegisterRep.setCertId(merInfoDO.getLegalPersonCertNm());// 证件号
		hanYinRegisterRep.setMobile(merInfoDO.getContactMobile());// 手机号
		hanYinRegisterRep.setOperFlag(openFlag);
		TblMerBankInfoDO tblMerBankInfoDO = tblMerBankInfoDOMapper.selectByPrimaryKey(merInfoDO.getMerId());
		if (tblMerBankInfoDO != null) {
			TblBankInfoExample tblBankInfoExample = new TblBankInfoExample();
			tblBankInfoExample.createCriteria().andBankNameLike("%" + tblMerBankInfoDO.getBankName() + "%");
			List<TblBankInfo> tblBankInfos = tblBankInfoMapper.selectByExample(tblBankInfoExample);
			if (tblBankInfos != null && tblBankInfos.size() > 0) {
				hanYinRegisterRep.setBankCode(tblBankInfos.get(0).getBranchCode());// 总行联行号
			}
		}

		hanYinRegisterRep.setBankName(tblMerBankInfoDO.getBankName());// 总行名称

		hanYinRegisterRep.setMerState(tblMerBankInfoDO.getProvName());// 商户所在省份
		hanYinRegisterRep.setMerCity(tblMerBankInfoDO.getCityName());// 商户所在城市
		hanYinRegisterRep.setAccountId(tblMerBankInfoDO.getAcctNo());// 结算账号
		hanYinRegisterRep.setAccountName(tblMerBankInfoDO.getAcctName());// 结算户名
		hanYinRegisterRep.setSignKey(hanYinKey);
		String signature = hanYinRegisterRep.getSign();// 签名信息
		String sign = MD5ZX.signHy(signature, "UTF-8");
		hanYinRegisterRep.setSignature(sign);
		logger.info("注册信息: " + signature);

		result = true;
		return result;
	}

	public Map<String, String> registerForMinSheng(String memberId, String channel) {

		Map<String, String> resultMap = new HashMap<>();
		String date = DateUtil.getCurrentDate();
		String time = DateUtil.getCurrentTime();
		String url = SystemConstant.MSBANK_BASE_URL;
		String requestMsg = "";
		String cooperator = SystemConstant.MSBANK_INST_ID;
		String tranCode = "SMZF001";
		String charset = "utf-8";
		String reqMsgId = memberId + date + time;
		String callBack = SystemConstant.MSBANK_NOTIFY_URL;
		String errorCode = "";
		String errorMsg = "";

		Boolean isRegisted = isRegisted(memberId, channel);
		if (isRegisted) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "该商户已经注册民生");
			return resultMap;
		}

		logger.info("检查商户是否注册上海银行");
		Map<String, String> registedMerMap = new HashMap<String, String>();
		registedMerMap = isMerRegisted(memberId);
		if (registedMerMap != null && registedMerMap.containsKey("SHBANK")) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "该商户已经注册上海银行");
			return resultMap;
		}

		Map<String, String> headerMap = new HashMap<>();
		Map<String, String> bodyMap = new HashMap<>();
		logger.info("原始报文：【{}】", requestMsg);
		MinshengHeader minshengHeader = new MinshengHeader();
		MinshengDoRegister minshengDoRegister = new MinshengDoRegister();
		minshengDoRegister.setMerchantId(memberId);
		boolean enriched = enrichMerchInfoMs(minshengHeader, minshengDoRegister, channel);
		if (!enriched) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "没有找到对应商户");
			return resultMap;
		}

		logger.debug("minshengHeader值: " + minshengHeader.toString());
		logger.debug("minshengDoRegister值: " + minshengDoRegister.toString());

		try {
			PrivateKey hzfPriKey = CryptoUtil.getRSAPrivateKeyByFileSuffix(SystemConstant.SELF_RSA_PRV, "pem", null,
					"RSA");

			PublicKey yhPubKey = CryptoUtil.getRSAPublicKeyByFileSuffix(SystemConstant.MSBANK_RSA_PUB, "pem", "RSA");

			headerMap = Bean2Map.beanToMapNoFill(minshengHeader);
			bodyMap = Bean2Map.beanToMapNoFill(minshengDoRegister);
			requestMsg = MsgFormatMS.msgFormat(headerMap, bodyMap);

			byte[] reqBytes = requestMsg.getBytes(charset);
			String keyStr = SystemConstant.SELF_AES_KEY;
			byte[] keyBytes = keyStr.getBytes(charset);
			String encryptData = new String(Base64.encodeBase64(
					(CryptoUtil.AESEncrypt(reqBytes, keyBytes, "AES", "AES/ECB/PKCS5Padding", null))), charset);
			String signData = new String(
					Base64.encodeBase64(CryptoUtil.digitalSign(reqBytes, hzfPriKey, "SHA1WithRSA")), charset);
			String encrtptKey = new String(
					Base64.encodeBase64(CryptoUtil.RSAEncrypt(keyBytes, yhPubKey, 2048, 11, "RSA/ECB/PKCS1Padding")),
					charset);

			logger.info("向SMZF服务器发出请求encryptData:[{}]", new Object[] { encryptData });
			logger.info("向SMZF服务器发出请求encrtptKey:[{}]", new Object[] { encrtptKey });
			logger.info("向SMZF服务器发出请求cooperator:[{}]", new Object[] { cooperator });
			logger.info("向SMZF服务器发出请求signData:[{}]", new Object[] { signData });
			logger.info("向SMZF服务器发出请求tranCode:[{}]", new Object[] { tranCode });
			logger.info("向SMZF服务器发出请求callBack:[{}]", new Object[] { callBack });
			logger.info("向SMZF服务器发出请求reqMsgId:[{}]", new Object[] { reqMsgId });

			List<NameValuePair> nvps = new LinkedList<NameValuePair>();
			nvps.add(new BasicNameValuePair("encryptData", encryptData));
			nvps.add(new BasicNameValuePair("encryptKey", encrtptKey));
			nvps.add(new BasicNameValuePair("cooperator", cooperator));
			nvps.add(new BasicNameValuePair("signData", signData));
			nvps.add(new BasicNameValuePair("tranCode", tranCode));
			nvps.add(new BasicNameValuePair("callBack", callBack));
			nvps.add(new BasicNameValuePair("reqMsgId", reqMsgId));
			byte[] respByte = HttpClient4Util.getInstance().doPost(url, null, nvps);
			String respStr = new String(respByte, charset);

			logger.info("返回报文[{}]", new Object[] { respStr });
			JSONObject jsonObject = JSONObject.parseObject(respStr);
			String resEncryptData = jsonObject.getString("encryptData");
			String resEncryptKey = jsonObject.getString("encryptKey");
			byte[] decodeBase64KeyBytes = Base64.decodeBase64(resEncryptKey.getBytes(charset));
			// 解密encryptKey得到merchantAESKey
			byte[] merchantAESKeyBytes = CryptoUtil.RSADecrypt(decodeBase64KeyBytes, hzfPriKey, 2048, 11,
					"RSA/ECB/PKCS1Padding");
			// 使用base64解码商户请求报文
			byte[] decodeBase64DataBytes = Base64.decodeBase64(resEncryptData.getBytes(charset));
			// 用解密得到的merchantAESKey解密encryptData
			byte[] merchantXmlDataBytes = CryptoUtil.AESDecrypt(decodeBase64DataBytes, merchantAESKeyBytes, "AES",
					"AES/ECB/PKCS5Padding", null);
			String respXml = new String(merchantXmlDataBytes, charset);
			logger.info("respXml[{}]", new Object[] { respXml });

			if (respXml != null) {
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(respXml);
				// Map<String,String> respHead =
				// XmlUtil.toMap(respMap.get("head").getBytes(),charset);
				// Map<String,String> respBody =
				// XmlUtil.toMap(respMap.get("body").getBytes(),charset);

				errorCode = respMap.get("respCode");
				errorMsg = respMap.get("respMsg");
				if ("000000".equals(errorCode)) {
					if (respMap.get("merchantCode") != null) {
						resultMap.put("merchantId", respMap.get("merchantCode"));
						resultMap.put("errorCode", "0");
						resultMap.put("errorMsg", errorMsg);
					}
				} else {
					resultMap.put("errorCode", errorCode);
					resultMap.put("errorMsg", errorMsg);
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "注册发生异常");
		}

		return resultMap;

	}

	/**
	 * 注册到民生银行
	 * 
	 * @param memberId
	 * @param channel
	 * @return
	 */
	@Override
	public Map<String, String> registerForMSBank(String memberId, String channel) {

		Map<String, String> resultMap = new HashMap<>();

		Boolean isRegisted = isRegisted(memberId, channel);
		if (isRegisted) {
			resultMap.put("status", "FAIL");
			resultMap.put("message", "该商户已经注册民生");
			return resultMap;
		}

		logger.info("检查商户是否注册上海银行");
		Map<String, String> registedMerMap = new HashMap<String, String>();
		registedMerMap = isMerRegisted(memberId);
		if (registedMerMap != null && registedMerMap.containsKey("SHBANK")) {
			resultMap.put("status", "FAIL");
			resultMap.put("message", "该商户已经注册上海银行");
			return resultMap;
		}

		String regType = "";
		if (channel.equals("UB")) {
			regType = "WECHAT_MER";
		}
		if (channel.equals("UC")) {
			regType = "ALIPAY_MER";
		}
		String param = "MERID=" + memberId + "&" + "REGTYPE=" + regType;
		logger.info("民生银行注册参数:" + param);

		HttpURLConnection httpUrlConnection = null;

		URL registerurl;
		try {
			registerurl = new URL(SystemConstant.MER_CENTER_URL + "/mer_register/msbank_mer_register");
			logger.info("跳转商户注册接口地址:" + registerurl);

			URLConnection urlConnection = registerurl.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();
			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			int resultCode = httpUrlConnection.getResponseCode();

			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb1.toString());
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(sb1.toString());

				logger.info("调用接口返回:" + respMap.toString());
				if (respMap == null || respMap.isEmpty()) {
					resultMap.put("status", "FAIL");
					resultMap.put("message", "民生银行报备失败, 请查看!");
					return resultMap;
				}

				if (respMap.containsKey("RSPCOD")) {
					resultMap.put("status", "FAIL");
					resultMap.put("message", "民生银行报备失败:" + respMap.get("RSPMSG"));
				} else {
					resultMap.put("status", "SUCCESS");
					resultMap.put("message", respMap.get("MESSAGE"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "民生银行报备失败: 系统异常");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "民生银行报备失败: 系统异常");
		}

		return resultMap;
	}

	/**
	 * 注册到上海银行
	 * 
	 * @param memberId
	 * @param channel
	 * @return
	 */
	@Override
	public Map<String, String> registerForSHBank(String memberId, String action) {

		Map<String, String> resultMap = new HashMap<>();

		logger.info("检查商户是否注册过民生银行");
		Map<String, String> registedMerMap = new HashMap<String, String>();
		registedMerMap = isMerRegisted(memberId);
		if (registedMerMap != null && registedMerMap.containsKey("MINSHENG")) {
			resultMap.put("errorCode", "-1");
			resultMap.put("errorMsg", "该商户已经注册民生银行");
			return resultMap;
		}

		Boolean isEverRegisted = false;
		isEverRegisted = merChannelPreService.SHBankRegisted(memberId);
		if (isEverRegisted) {
			action = "UPD";
		}

		String param = "USRID=" + memberId + "&" + "ACTION=" + action;
		logger.info("上海银行注册地址参数:" + param);
		HttpURLConnection httpUrlConnection = null;

		URL registerurl;
		try {
			registerurl = new URL(SystemConstant.MER_CENTER_URL + "/mer_register/shbank_mer_register");
			logger.info("上海银行注册地址:" + registerurl);
			URLConnection urlConnection = registerurl.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();
			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			int resultCode = httpUrlConnection.getResponseCode();

			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream()));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb1.toString());
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(sb1.toString());
				logger.info("上海银行调用接口返回:" + respMap);

				if (respMap == null || respMap.isEmpty()) {
					resultMap.put("status", "FAIL");
					resultMap.put("message", "上海银行报备失败, 请查看!");
					return resultMap;
				}

				if (respMap.containsKey("RSPCOD")) {
					resultMap.put("status", "FAIL");
					resultMap.put("message", "上海银行报备失败:" + resultMap.get("RSPMSG"));
				} else if (respMap.containsKey("STATUS") && respMap.get("STATUS").equals("SUCCESS"))
					;
				{
					resultMap.put("status", resultMap.get("STATUS"));
					resultMap.put("message", resultMap.get("MESSAGE"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "上海银行报备失败 -系统错误!");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "上海银行报备失败 -系统错误!");
		}

		return resultMap;

	}

	private boolean enrichMerchInfoMs(MinshengHeader minshengHeader, MinshengDoRegister minshengDoRegister,
			String channel) {

		boolean result = false;
		String date = DateUtil.getCurrentDate();
		String time = DateUtil.getCurrentTime();

		TblMerInfoDO merInfoDO = merInfoDOMapper.selectByPrimaryKey(minshengDoRegister.getMerchantId());
		if (merInfoDO == null) {
			logger.error("没有找到对应商户：{}", minshengDoRegister.getMerchantId());
			result = false;
			return result;
		}

		minshengHeader.setVersion("1.0.0");
		minshengHeader.setMsgType("01");
		minshengHeader.setReqDate(date + time);

		minshengDoRegister.setPayWay(PayWayMap.get(channel));
		minshengDoRegister.setMerchantId(minshengDoRegister.getMerchantId() + merIdSuffixMap.get(channel));
		minshengDoRegister.setMerchantName(merInfoDO.getMerName());
		minshengDoRegister.setShortName(merInfoDO.getRegShortName());
		minshengDoRegister.setMerchantAddress(merInfoDO.getMerAddress());

		// 民生银行地区编码获取
		TblCustAddressDoExample tblCustAddressDoExample = new TblCustAddressDoExample();
		String merId = merInfoDO.getMerId();
		logger.info("商户号: " + merId);
		logger.info("商户地址: " + merInfoDO.getMerAddress());

		tblCustAddressDoExample.createCriteria().andMerIdEqualTo(merId);
		List<TblCustAddressDo> tblCustAddressDoList = tblCustAddressDoMapper.selectByExample(tblCustAddressDoExample);
		if (tblCustAddressDoList.size() > 0) {
			minshengDoRegister.setProvinceCode(tblCustAddressDoList.get(0).getProvinceCode());
			minshengDoRegister.setCityCode(tblCustAddressDoList.get(0).getCityCode());
			minshengDoRegister.setDistrictCode(tblCustAddressDoList.get(0).getAreaCode());
		} else {
			// 默认填值
			minshengDoRegister.setProvinceCode("310000");
			minshengDoRegister.setCityCode("310100");
			minshengDoRegister.setDistrictCode("310104");
		}

		minshengDoRegister.setContactType("02");
		minshengDoRegister.setContactName(merInfoDO.getContactPerson());

		minshengDoRegister.setServicePhone(merInfoDO.getContactMobile());
		minshengDoRegister.setIdCard(merInfoDO.getLegalPersonCertNm());
		minshengDoRegister.setMerchantLicense(merInfoDO.getBusLicNm());
		minshengDoRegister.setCategory(categoryMap.get(channel));

		TblMerBankInfoDO tblMerBankInfoDO = tblMerBankInfoDOMapper.selectByPrimaryKey(merInfoDO.getMerId());
		minshengDoRegister.setAccNo(tblMerBankInfoDO.getAcctNo());
		minshengDoRegister.setAccName(tblMerBankInfoDO.getAcctName());
		minshengDoRegister.setBankType(tblMerBankInfoDO.getCnaps());
		minshengDoRegister.setBankName(tblMerBankInfoDO.getBankName());

		TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
		tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merInfoDO.getMerId());

		String fee03 = "";
		String fee04 = "";
		String fee05 = "";
		String fee06 = "";
		String fee07 = "";
		String fee08 = "";

		List<TblMerFeeInfoDO> tblMerFeeInfoDOList = tblMerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
		String tempFee = "";
		for (int i = 0; i < tblMerFeeInfoDOList.size(); i++) {
			if ("03".equals(tblMerFeeInfoDOList.get(i).getFeeType()))
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee03 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee03 = tempFee.split(",")[0];
				}
			if ("04".equals(tblMerFeeInfoDOList.get(i).getFeeType()))
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee04 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee04 = tempFee.split(",")[0];
				}
			if ("05".equals(tblMerFeeInfoDOList.get(i).getFeeType()))
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee05 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee05 = tempFee.split(",")[0];
				}
			if ("06".equals(tblMerFeeInfoDOList.get(i).getFeeType()))
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee06 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee06 = tempFee.split(",")[0];
				}
			if ("07".equals(tblMerFeeInfoDOList.get(i).getFeeType())) {
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee07 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee07 = tempFee.split(",")[0];
				}
				if (Double.valueOf(fee07) < 0.2)
					fee07 = "0.2";
			}
			if ("08".equals(tblMerFeeInfoDOList.get(i).getFeeType())) {
				if (tblMerFeeInfoDOList.get(i).getCalcMode().indexOf("AMT") == -1) {
					fee08 = tblMerFeeInfoDOList.get(i).getCalcMode();
				} else {
					tempFee = CalcModeUtil.splitCalcMode(tblMerFeeInfoDOList.get(i).getCalcMode(), false);
					fee08 = tempFee.split(",")[0];
				}
				if (Double.valueOf(fee08) < 0.2)
					fee08 = "0.2";
			}
		}

		BigDecimal new03 = new BigDecimal(fee03).divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP);
		fee03 = String.valueOf(new03.doubleValue());
		BigDecimal new04 = new BigDecimal(fee04).divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP);
		fee04 = String.valueOf(new04.doubleValue());
		BigDecimal new05 = new BigDecimal(fee05).divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP);
		fee05 = String.valueOf(new05.doubleValue());
		BigDecimal new06 = new BigDecimal(fee06).divide(new BigDecimal("100"), 6, BigDecimal.ROUND_HALF_UP);
		fee06 = String.valueOf(new06.doubleValue());

		if ("UB".equals(channel)) {
			minshengDoRegister.setT0drawFee(fee07);
			minshengDoRegister.setT0tradeRate(fee03);
			minshengDoRegister.setT1drawFee(fee08);
			minshengDoRegister.setT1tradeRate(fee05);
		}

		if ("UC".equals(channel)) {
			minshengDoRegister.setT0drawFee(fee07);
			minshengDoRegister.setT0tradeRate(fee04);
			minshengDoRegister.setT1drawFee(fee08);
			minshengDoRegister.setT1tradeRate(fee06);
		}

		logger.info("fee03: " + fee03 + ": " + "fee04: " + fee04 + ":" + "fee05: " + fee05 + "fee06: " + fee06
				+ "fee07: " + fee07 + "fee08: " + fee08);
		logger.info("注册信息: " + minshengDoRegister.toString());

		result = true;
		return result;
	}

	@Override
	@Transactional
	public Map<String, String> addMerchMapGen(Map<String, String> paramMap, String channel) {
		Map resultMap = new HashMap();
		String priority = "Y";
		String errorCode = paramMap.get("errorCode");
		String errorMsg = paramMap.get("errorMsg");
		String memberId = paramMap.get("memberId");
		String termId = merIdSuffixMap.get(channel);
		if ("0".equals(errorCode)) {
			String merchantId = paramMap.get("merchantId");
			TblRouteControlExample routeControlExample = new TblRouteControlExample();
			routeControlExample.createCriteria().andMerIdEqualTo(memberId).andStatEqualTo("N")
					.andDestGateIdEqualTo(channel);
			List<TblRouteControl> routeControlList = routeControlMapper.selectByExample(routeControlExample);
			if (routeControlList != null && routeControlList.size() != 0) {
				for (TblRouteControl routeControlExist : routeControlList) {
					routeControlExist.setStat("Y");
					TblRouteControlExample routeControlExample1 = new TblRouteControlExample();
					routeControlExample1.createCriteria().andPosRouteSeqEqualTo(routeControlExist.getPosRouteSeq());
					routeControlMapper.updateByExampleSelective(routeControlExist, routeControlExample1);
				}
			} else {
				TblInstMerTermRelDOExample example = new TblInstMerTermRelDOExample();
				example.createCriteria().andMerIdEqualTo(memberId);
				List<TblInstMerTermRelDO> merList = tblInstMerTermRelDOMapper.selectByExample(example);
				if (merList.size() == 0) {
					resultMap.put("statusCode", "300");
					resultMap.put("message", "渠道信息丢失");
					return resultMap;
				}
				TblInstMerTermRelDO merChannelPreInfoDO = merList.get(0);

				TblRouteControl routeControl = new TblRouteControl();
				routeControl.setInstId(merChannelPreInfoDO.getInstId());
				routeControl.setMerId(memberId);
				routeControl.setTermId(termId);
				routeControl.setDestGateId(channel);
				routeControl.setDestMerId(merchantId);
				routeControl.setDestTermId("00000000");
				routeControl.setRemark("");
				routeControl.setPosRouteSeq(String.valueOf(seqMapper.getSequenceNextVal("POS_ROUTE_SEQ")));
				routeControl.setStat(priority);
				routeControlMapper.insertSelective(routeControl);

				TblBankWxInfoDO bankWxInfoDO = new TblBankWxInfoDO();
				bankWxInfoDO.setGateId(channel);
				bankWxInfoDO.setMerId(merchantId);
				bankWxInfoDO.setAvailAmt("200000.00");
				bankWxInfoDO.setUseAmt("0.00");
				bankWxInfoDO.setMainKey("0");
				bankWxInfoDO.setStatus("Y");
				bankWxInfoDOMapper.insertSelective(bankWxInfoDO);

			}
			resultMap.put("statusCode", "200");
			resultMap.put("message", "添加注册信息成功");
		} else if ("-1".equals(errorCode)) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", errorMsg);
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", errorMsg);
		}
		return resultMap;
	}

	/**
	 * 添加翰银 通道入库
	 *
	 * @param paramMap
	 * @param channel
	 * @return
	 */
	@Override
	@Transactional
	public Map<String, String> addHanYinMapGen(Map<String, String> paramMap, String channel) {
		Map resultMap = new HashMap();
		String errorCode = paramMap.get("statusCode");
		String errorMsg = paramMap.get("statusMsg");
		String insMerchantCode = paramMap.get("insMerchantCode");
		String hpMerCode = paramMap.get("hpMerCode");
		String merCode = paramMap.get("merCode");
		if ("00".equals(errorCode)) {
			TblInstRouteControlExample tblInstRouteControlExample = new TblInstRouteControlExample();
			tblInstRouteControlExample.createCriteria().andInstMerIdEqualTo(merCode).andStatEqualTo("N")
					.andGateIdEqualTo(channel);
			List<TblInstRouteControl> tblInstRouteControls = tblInstRouteControlMapper
					.selectByExample(tblInstRouteControlExample);
			if (tblInstRouteControls != null && tblInstRouteControls.size() > 0) {
				TblInstRouteControlExample route = null;
				for (TblInstRouteControl t : tblInstRouteControls) {
					route = new TblInstRouteControlExample();
					t.setStat("Y");
					route.createCriteria().andRouteSeqEqualTo(t.getRouteSeq());
					tblInstRouteControlMapper.updateByExampleSelective(t, route);
				}
			} else {

				Map<String, String> map = merchInfoService.InstOrAgent(merCode);

				String id = "";
				String termid = "";
				if ("YES".equals(map.get("INSTMER"))) {
					id = map.get("INSTID");
					termid = map.get("TERMID");
				} else {
					id = map.get("AGENTID");
					termid = map.get("TERMID");
				}

				logger.info("===========id" + id);
				logger.info("===========termid" + termid);

				TblInstRouteControl tblInstRouteControl = new TblInstRouteControl();

				tblInstRouteControl
						.setRouteSeq(String.valueOf(seqMapper.getSequenceNextVal("TBL_INST_ROUTE_CONTROL_SEQ")));
				tblInstRouteControl.setInstCode(id);
				tblInstRouteControl.setInstMerId(merCode);
				tblInstRouteControl.setInstMerTermId(termid);
				tblInstRouteControl.setGateId(channel);
				tblInstRouteControl.setInstBankMerId(insMerchantCode);
				tblInstRouteControl.setBankMerId(hpMerCode);
				tblInstRouteControl.setStat("Y");

				tblInstRouteControlMapper.insertSelective(tblInstRouteControl);

			}
			resultMap.put("statusCode", "200");
			resultMap.put("message", "添加注册信息成功");
		} else if ("-1".equals(errorCode)) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", errorMsg);
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", errorMsg);
		}
		return resultMap;
	}

	/**
	 * 检查商户是否注册过
	 * 
	 * @return
	 */
	public Map<String, String> isMerRegisted(String merId) {

		Map<String, String> channelMap = new HashMap<>();

		logger.info("检查是否注册过民生");
		Boolean msRegisted = false;
		List<String> MStypes = Arrays.asList("UB", "UC", "UD", "UE");
		for (String mStypes : MStypes) {
			msRegisted = isRegisted(merId, mStypes);
			if (msRegisted) {
				channelMap.put("MINSHENG", "OK");
				return channelMap;
			}
		}

		logger.info("检查是否注册过翰银");
		Boolean hyRegisted = false;
		hyRegisted = isHanYinRegisted(merId, "U4");
		if (hyRegisted) {
			channelMap.put("HANYIN", "OK");
			return channelMap;
		}

		logger.info("检查是否注册过翰银");
		Boolean shbRegistedWX = false;
		Boolean shbRegistedZFB = false;
		shbRegistedWX = isSHBankRegisted(merId, "US");
		shbRegistedZFB = isSHBankRegisted(merId, "UT");
		if (shbRegistedWX || shbRegistedZFB) {
			channelMap.put("SHBANK", "OK");
			return channelMap;
		}

		return channelMap;

	}

	@Override
	public boolean isRegisted(String memberId, String gate) {
		Boolean result = false;

		logger.info("检测商户是否已经注册过该渠道");
		TblRouteControlExample routeControlExample = new TblRouteControlExample();
		routeControlExample.createCriteria().andMerIdEqualTo(memberId).andDestGateIdEqualTo(gate).andStatEqualTo("Y");
		List<TblRouteControl> routeControlList = routeControlMapper.selectByExample(routeControlExample);
		if (routeControlList.size() > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * 测商户是否已经注册过翰银渠道
	 *
	 * @param memberId
	 * @param gate
	 * @return
	 */
	@Override
	public boolean isHanYinRegisted(String memberId, String gate) {
		Boolean result = false;
		logger.info("检测商户是否已经注册过翰银渠道");
		TblInstRouteControlExample tblInstRouteControlExample = new TblInstRouteControlExample();
		tblInstRouteControlExample.createCriteria().andInstMerIdEqualTo(memberId).andGateIdEqualTo(gate)
				.andStatEqualTo("Y");
		List<TblInstRouteControl> tblInstRouteControls = tblInstRouteControlMapper
				.selectByExample(tblInstRouteControlExample);
		if (tblInstRouteControls != null && tblInstRouteControls.size() > 0) {
			result = true;
		}
		return result;

	}

	/**
	 * 测商户是否已经注册过上海银行
	 *
	 * @param memberId
	 * @param gate
	 * @return
	 */
	@Override
	public boolean isSHBankRegisted(String memberId, String gateId) {
		Boolean result = false;
		logger.info("检测商户是否已经注册过上海银行");
		TblRouteControlExample routeControlExample = new TblRouteControlExample();
		routeControlExample.createCriteria().andMerIdEqualTo(memberId).andDestGateIdEqualTo(gateId).andStatEqualTo("Y");
		List<TblRouteControl> routeControlList = routeControlMapper.selectByExample(routeControlExample);
		if (routeControlList != null && routeControlList.size() > 0) {
			result = true;
		}
		return result;
	}

	private static Map<String, String> toMap(byte[] xmlBytes, String charset) throws Exception {
		Map<String, String> rest = new HashMap<String, String>();
		SAXReader reader = new SAXReader(false);
		InputSource source = new InputSource(new ByteArrayInputStream(xmlBytes));
		source.setEncoding(charset);
		try {
			Document doc = reader.read(source);
			Element root = doc.getRootElement();
			Iterator it = root.elementIterator();
			while (it.hasNext()) {
				Element element = (Element) it.next();
				rest.put(element.getName(), element.getText());
			}

		} catch (DocumentException e) {
			throw new RuntimeException("解析返回富友报文异常" + e);
		}
		return rest;
	}

	/**
	 * 商户是否已经注册过恒丰银行
	 *
	 * @param memberId
	 * @param gate
	 * @return
	 */
	@Override
	public boolean isEGBankRegisted(String memberId, String gateId) {
		Boolean result = false;
		logger.info("检测商户是否已经注册过恒丰银行");
		TblRouteControlExample routeControlExample = new TblRouteControlExample();
		routeControlExample.createCriteria().andMerIdEqualTo(memberId).andDestGateIdEqualTo(gateId).andStatEqualTo("Y");
		List<TblRouteControl> routeControlList = routeControlMapper.selectByExample(routeControlExample);
		if (routeControlList != null && routeControlList.size() > 0) {
			result = true;
		}
		return result;
	}

	public Map<String, String> registerForEGBank(String memberId, String action) {
		Map<String, String> resultMap = new HashMap<>();

		String param = "USRID=" + memberId + "&" + "TOCHANNEL=" + action;
		logger.info("恒丰银行注册地址参数:" + param);
		HttpURLConnection httpUrlConnection = null;

		URL registerurl;
		try {
			registerurl = new URL(SystemConstant.MER_CENTER_URL + "/mer_register/egbank_mer_register");
			logger.info("恒丰银行注册地址:" + registerurl);
			URLConnection urlConnection = registerurl.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();
			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			int resultCode = httpUrlConnection.getResponseCode();

			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream()));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb1.toString());
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(sb1.toString());
				logger.info("恒丰银行调用接口返回:" + respMap);

				if (respMap == null || respMap.isEmpty()) {
					resultMap.put("status", "FAIL");
					resultMap.put("message", "恒丰银行报备失败, 请查看!");
					return resultMap;
				}

				resultMap.put("status", respMap.get("STATUS"));
				resultMap.put("message", respMap.get("MESSAGE"));

			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "恒丰银行报备失败 -系统错误!");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("status", "FAIL");
			resultMap.put("message", "恒丰银行报备失败 -系统错误!");
		}

		return resultMap;

	}

}
