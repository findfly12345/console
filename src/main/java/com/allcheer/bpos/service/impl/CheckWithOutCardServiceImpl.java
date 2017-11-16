package com.allcheer.bpos.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.Minsheng.MinshengDoGetCheckFile;
import com.allcheer.bpos.domain.Minsheng.MinshengHeader;
import com.allcheer.bpos.domain.Minsheng.MsBaseDomain;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.CheckFileForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.CheckWithOutCardService;
import com.allcheer.bpos.service.TransLogService;
import com.allcheer.bpos.util.*;
import com.allcheer.bpos.util.Minsheng.CryptoUtil;
import com.allcheer.bpos.util.Minsheng.HttpClient4Util;
import com.allcheer.bpos.util.Minsheng.MsgFormatMS;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.*;

import javax.servlet.ServletOutputStream;

/**
 * Created by LiuBin on 2017/1/5.
 */
@Service("checkWithOutCardService")
public class CheckWithOutCardServiceImpl implements CheckWithOutCardService {

	private final static Logger logger = LoggerFactory.getLogger(CheckWithOutCardServiceImpl.class);

	@Autowired
	WCINCOMEDOMapper _WCINCOMEDOMapper;

	@Autowired
	TblT1CheckFileRespondeDoMapper tblT1CheckFileRespondeDoMapper;

	@Autowired
	TblWechatIncomeCheckFileDoMapper tblWechatIncomeCheckFileDoMapper;

	@Autowired
	TblMsCheckFileDoMapper tblMsCheckFileDoMapper;

	@Autowired
	TblCapitalClearingDoMapper tblCapitalClearingDoMapper;

	@Autowired
	TblT0CheckFileResponseDoMapper tblT0CheckFileResponseDoMapper;

	@Autowired
	TblRouteControlMapper tblRouteControlMapper;

	/**
	 * 从民生接口获得流水信息
	 * 
	 * @param settleDate
	 * @return
	 */
	@Override
	@Transactional
	public Map<String, String> getMs020(String settleDate) {

		Map<String, String> resultMap = new HashMap<>();
		MinshengDoGetCheckFile minshengDoGetCheckFile = new MinshengDoGetCheckFile();
		minshengDoGetCheckFile.setSettleDate(settleDate);
		minshengDoGetCheckFile.setFileType("1");
		try {
			String respXml = minShengCommonPorts(minshengDoGetCheckFile, settleDate, "SMZF020");
			logger.info("respXml[{}]", new Object[] { respXml });
			String[] rows = parseXmlToStringArray(respXml);
			if (rows.length == 0) {
				return resultMap;
			}
			for (String row : rows) {
				if ("".equals(row.trim())) {
					continue;
				}
				String[] content = row.split("\\|");
				TblMsCheckFileDo tblMsCheckFileDo = new TblMsCheckFileDo();
				tblMsCheckFileDo.setCooperator(content[0]);
				tblMsCheckFileDo.setMerchantcode(content[1]);
				tblMsCheckFileDo.setSmzfmsgid(content[2]);
				tblMsCheckFileDo.setReqmsgid(content[3]);
				tblMsCheckFileDo.setAmount(new BigDecimal(content[4] == "" ? "0" : content[4]));
				tblMsCheckFileDo.setSettledate(content[5]);
				tblMsCheckFileDo.setResptype(content[6]);
				tblMsCheckFileDo.setRespcode(content[7]);
				tblMsCheckFileDo.setRespmsg(content[8]);
				tblMsCheckFileDo.setTransactiontype(content[9]);
				tblMsCheckFileDo.setOrireqmsgid(content[10]);
				tblMsCheckFileDo.setFee(new BigDecimal(content[11] == "" ? "0" : content[11]));
				tblMsCheckFileDo.setPayway(content[12]);
				tblMsCheckFileDo.setPaytype(content[13]);
				tblMsCheckFileDo.setDrawbatchno(content[14]);
				tblMsCheckFileDo.setShfee(new BigDecimal(content[15] == "" ? "0" : content[15]));
				tblMsCheckFileDo.setUpdateTime(DateUtil.getCurrentDate());
				tblMsCheckFileDo.setCreateTime(DateUtil.getCurrentDate());

				tblMsCheckFileDoMapper.insert(tblMsCheckFileDo);
			}
			resultMap.put("success", "true");
		} catch (Exception e) {
			logger.debug("请求T1对账文件失败");
			logger.error(e.getMessage());
			throw new BposException("请求T1对账文件失败");
		}

		return resultMap;
	}

	/**
	 * 检查数据库中的数据
	 * 
	 * @param settleDate
	 * @return
	 */
	@Override
	public List<TblMsCheckFileDo> getMsCheckFile(String settleDate) {
		TblMsCheckFileDoExample tblMsCheckFileDoExample = new TblMsCheckFileDoExample();
		tblMsCheckFileDoExample.createCriteria().andSettledateEqualTo(settleDate);
		List<TblMsCheckFileDo> tblMsCheckFileDos = new ArrayList<>();
		tblMsCheckFileDos = tblMsCheckFileDoMapper.selectByExample(tblMsCheckFileDoExample);
		return tblMsCheckFileDos;
	}

	@Override
	public List<TblT1CheckFileRespondeDo> getT1CheckFile(CheckFileForm form) {
		TblT1CheckFileRespondeDoExample tblT1CheckFileRespondeDoExample = new TblT1CheckFileRespondeDoExample();
		TblT1CheckFileRespondeDoExample.Criteria criteria = tblT1CheckFileRespondeDoExample.createCriteria();
		criteria.andSettledateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getMerchantCode())) {
			criteria.andMerchantcodeEqualTo(form.getMerchantCode());
		}
		if (StringUtils.isNotBlank(form.getAccNo())) {
			criteria.andAccnoEqualTo(form.getAccNo());
		}
		if (StringUtils.isNotBlank(form.getAccName())) {
			criteria.andAccnameEqualTo(form.getAccName());
		}
		List<TblT1CheckFileRespondeDo> tblT1CheckFileRespondeDos = new ArrayList<>();
		tblT1CheckFileRespondeDos = tblT1CheckFileRespondeDoMapper.selectByExample(tblT1CheckFileRespondeDoExample);
		return tblT1CheckFileRespondeDos;
	}

	@Override
	public List<TblT0CheckFileResponseDo> getT0CheckFile(CheckFileForm form) {
		TblT0CheckFileResponseDoExample tblT0CheckFileResponseDoExample = new TblT0CheckFileResponseDoExample();
		TblT0CheckFileResponseDoExample.Criteria criteria = tblT0CheckFileResponseDoExample.createCriteria();
		criteria.andSettledateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getMerchantCode())) {
			criteria.andMerchantcodeEqualTo(form.getMerchantCode());
		}
		if (StringUtils.isNotBlank(form.getAccNo())) {
			criteria.andAccnoEqualTo(form.getAccNo());
		}
		if (StringUtils.isNotBlank(form.getAccName())) {
			criteria.andAccnameEqualTo(form.getAccName());
		}
		List<TblT0CheckFileResponseDo> tblT0CheckFileResponseDos = new ArrayList<>();
		tblT0CheckFileResponseDos = tblT0CheckFileResponseDoMapper.selectByExample(tblT0CheckFileResponseDoExample);
		return tblT0CheckFileResponseDos;
	}

	/**
	 * 获得对账差异文件
	 * 
	 * @param form
	 * @return
	 */
	@Override
	public List<TblWechatIncomeCheckFileDo> getDifferenceWithCheckFile(CheckFileForm form) {
		TblWechatIncomeCheckFileDoExample tblWechatIncomeCheckFileDoExample = new TblWechatIncomeCheckFileDoExample();
		TblWechatIncomeCheckFileDoExample.Criteria criteria = tblWechatIncomeCheckFileDoExample.createCriteria();
		criteria.andTradeDateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getChekcResp())) {
			criteria.andChekcRespEqualTo(form.getChekcResp());
		}
		List<TblWechatIncomeCheckFileDo> tblWechatIncomeCheckFileDos = new ArrayList<>();
		tblWechatIncomeCheckFileDos = tblWechatIncomeCheckFileDoMapper
				.selectByExample(tblWechatIncomeCheckFileDoExample);
		return tblWechatIncomeCheckFileDos;
	}

	/**
	 * 获得商户T1清算结账结果
	 * 
	 * @param settleDate
	 * @return
	 */
	@Override
	public List<TblCapitalClearingDo> getMerchantT1Clearing(String settleDate, String merchantCode) {
		TblCapitalClearingDoExample tblCapitalClearingDoExample = new TblCapitalClearingDoExample();
		tblCapitalClearingDoExample.createCriteria().andSettledateEqualTo(settleDate)
				.andMerchantcodeEqualTo(merchantCode);
		List<TblCapitalClearingDo> tblCapitalClearingDos = new ArrayList<>();
		tblCapitalClearingDos = tblCapitalClearingDoMapper.selectByExample(tblCapitalClearingDoExample);
		return tblCapitalClearingDos;
	}

	/**
	 * 开始对账
	 * 
	 * @param settleDate
	 * @return
	 */
	@Override
	@Transactional
	public Map compareT1CheckFile(String settleDate) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String tradeStatus = "";
		try {
			WCINCOMEDOExample wcincomedoExample = new WCINCOMEDOExample();
			wcincomedoExample.createCriteria().andIncomeRecvDateEqualTo(settleDate);
			List<WCINCOMEDO> wcincomedos = _WCINCOMEDOMapper.selectByExample(wcincomedoExample);
			List<TblMsCheckFileDo> tblMsCheckFileDos = getMsCheckFile(settleDate);
			for (TblMsCheckFileDo tblMsCheckFileDo : tblMsCheckFileDos) {
				map.put(tblMsCheckFileDo.getReqmsgid(), tblMsCheckFileDo);
			}
			for (WCINCOMEDO wcincomedo : wcincomedos) {
				if (map.get(wcincomedo.getIncomeId()) != null) {
					tradeStatus = wcincomedo.getIncomeStatus();
					if (tradeStatus.equals("S"))
						wcincomedo.setCheckFlag("S");
					else
						wcincomedo.setCheckFlag("F");
				} else
					wcincomedo.setCheckFlag("P");
				_WCINCOMEDOMapper.updateByPrimaryKey(wcincomedo);

				TblWechatIncomeCheckFileDo tblWechatIncomeCheckFileDo = new TblWechatIncomeCheckFileDo();
				tblWechatIncomeCheckFileDo.setTradeId(wcincomedo.getIncomeId());
				tblWechatIncomeCheckFileDo.setTradeAmount(wcincomedo.getIncomeAmount());
				tblWechatIncomeCheckFileDo.setTradeDate(wcincomedo.getIncomeRecvDate());
				tblWechatIncomeCheckFileDo.setTradeType(wcincomedo.getIncomeType());
				tblWechatIncomeCheckFileDo.setChekcResp(wcincomedo.getCheckFlag());
				tblWechatIncomeCheckFileDo.setCreateTime(DateUtil.getCurrentDate());
				tblWechatIncomeCheckFileDo.setUpdateTime(DateUtil.getCurrentDate());

				tblWechatIncomeCheckFileDoMapper.insert(tblWechatIncomeCheckFileDo);
			}
		} catch (Exception e) {
			throw new BposException("对账出现异常");
		}
		resultMap.put("success", true);
		resultMap.put("msg", "对账差异比较结束");
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, String> getMs026(String settleDate, String merchantCode) {

		Map<String, String> resultMap = new HashMap<>();
		TblCapitalClearingDo tblCapitalClearingDo = new TblCapitalClearingDo();
		tblCapitalClearingDo.setMerchantCode(merchantCode);
		tblCapitalClearingDo.setSettleDate(settleDate);
		try {
			String respXml = minShengCommonPorts(tblCapitalClearingDo, settleDate, "SMZF026");
			logger.info("respXml[{}]", new Object[] { respXml });
			if (!StringUtils.isNotBlank(respXml)) {
				return resultMap;
			}
			Document doc = DocumentHelper.parseText(respXml);
			Map<String, Object> map = (Map<String, Object>) XmlUtil.xml2map(doc.getRootElement());
			Map<String, Object> headMap = (Map<String, Object>) map.get("head");
			String responseCode = headMap.get("respCode") + "";
			if (!responseCode.equals("000000")) {
				throw new BposException(headMap.get("respMsg") + "");
			}
			Map<String, Object> contentMap = (Map<String, Object>) map.get("body");
			// tblCapitalClearingDo.setSettleDate(contentMap.get("settleDate")
			// == null?"":contentMap.get("settleDate")+"");
			tblCapitalClearingDo.setOriRespType(contentMap.get("oriRespType") + "");
			tblCapitalClearingDo.setOriRespCode(contentMap.get("oriRespCode") + "");
			tblCapitalClearingDo.setOriRespMsg(contentMap.get("oriRespMsg") + "");
			tblCapitalClearingDo.setDrawAmount(contentMap.get("drawAmount") == "" ? new BigDecimal(0.00)
					: new BigDecimal(contentMap.get("drawAmount") + ""));
			tblCapitalClearingDo.setDrawFee(contentMap.get("drawFee") == "" ? new BigDecimal(0.00)
					: new BigDecimal(contentMap.get("drawFee") + ""));
			tblCapitalClearingDo.setExtend1(contentMap.get("extend1") + "");
			tblCapitalClearingDo.setExtend2(contentMap.get("extend2") + "");
			tblCapitalClearingDo.setExtend3(contentMap.get("extend3") + "");
			List<TblCapitalClearingDo> tblCapitalClearingDos = getMerchantT1Clearing(settleDate, merchantCode);
			if (tblCapitalClearingDos != null && tblCapitalClearingDos.size() > 0) {
				TblCapitalClearingDo tblCapitalClearingDo1 = tblCapitalClearingDos.get(0);
				TblCapitalClearingDoExample tblCapitalClearingDoExample = new TblCapitalClearingDoExample();
				tblCapitalClearingDoExample.createCriteria().andSettledateEqualTo(settleDate)
						.andMerchantcodeEqualTo(merchantCode);
				tblCapitalClearingDoMapper.updateByExampleSelective(tblCapitalClearingDo1, tblCapitalClearingDoExample);
			} else {
				tblCapitalClearingDoMapper.insert(tblCapitalClearingDo);
			}
			resultMap.put("success", "true");
			resultMap.put("msg", "查询成功");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BposException("获取接口SMZF026失败");
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, String> getMs025(String settleDate) {

		Map<String, String> resultMap = new HashMap<>();
		MinshengDoGetCheckFile minshengDoGetCheckFile = new MinshengDoGetCheckFile();
		minshengDoGetCheckFile.setSettleDate(settleDate);
		minshengDoGetCheckFile.setFileType("1");
		try {
			String respXml = minShengCommonPorts(minshengDoGetCheckFile, settleDate, "SMZF025");
			logger.info("respXml[{}]", new Object[] { respXml });
			String[] rows = parseXmlToStringArray(respXml);
			if (rows.length == 0) {
				return resultMap;
			}
			for (String row : rows) {
				if ("".equals(row.trim())) {
					continue;
				}
				String[] content = row.split("\\|");
				TblT1CheckFileRespondeDo tblT1CheckFileRespondeDo = new TblT1CheckFileRespondeDo();
				tblT1CheckFileRespondeDo.setCooperator(content[0]);
				tblT1CheckFileRespondeDo.setMerchantcode(content[1]);
				tblT1CheckFileRespondeDo.setSmzfmsgid(content[2]);
				tblT1CheckFileRespondeDo.setAccno(content[3]);
				tblT1CheckFileRespondeDo.setAccname(content[4]);
				tblT1CheckFileRespondeDo.setDrawamount(new BigDecimal(content[5] == "" ? "0" : content[5]));
				tblT1CheckFileRespondeDo.setDrawfee(new BigDecimal(content[6] == "" ? "0" : content[6]));
				tblT1CheckFileRespondeDo.setSettledate(content[7]);
				tblT1CheckFileRespondeDo.setResptype(content[8]);
				tblT1CheckFileRespondeDo.setRespcode(content[9]);
				tblT1CheckFileRespondeDo.setRespmsg(content[10]);
				tblT1CheckFileRespondeDo.setDzlx(content[11]);

				tblT1CheckFileRespondeDoMapper.insert(tblT1CheckFileRespondeDo);
			}
			resultMap.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取接口SMZF025失败");
			throw new BposException("获取接口SMZF025失败");
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, String> getMs024(String settleDate) {

		Map<String, String> resultMap = new HashMap<>();
		MinshengDoGetCheckFile minshengDoGetCheckFile = new MinshengDoGetCheckFile();
		minshengDoGetCheckFile.setSettleDate(settleDate);
		minshengDoGetCheckFile.setFileType("1");
		try {
			String respXml = minShengCommonPorts(minshengDoGetCheckFile, settleDate, "SMZF024");
			logger.info("respXml[{}]", new Object[] { respXml });
			String[] rows = parseXmlToStringArray(respXml);
			if (rows.length == 0) {
				return resultMap;
			}
			for (String row : rows) {
				if ("".equals(row.trim())) {
					continue;
				}
				String[] content = row.split("\\|");
				TblT0CheckFileResponseDo tblT0CheckFileResponseDo = new TblT0CheckFileResponseDo();
				tblT0CheckFileResponseDo.setCooperator(content[0]);
				tblT0CheckFileResponseDo.setMerchantcode(content[1]);
				tblT0CheckFileResponseDo.setSmzfmsgid(content[2]);
				tblT0CheckFileResponseDo.setReqmsgid(content[3]);
				tblT0CheckFileResponseDo.setAccno(content[4]);
				tblT0CheckFileResponseDo.setAccname(content[5]);
				tblT0CheckFileResponseDo.setDrawamount(new BigDecimal(content[6] == "" ? "0" : content[6]));
				tblT0CheckFileResponseDo.setDrawfee(new BigDecimal(content[7] == "" ? "0" : content[7]));
				tblT0CheckFileResponseDo.setTradefee(new BigDecimal(content[8] == "" ? "0" : content[8]));
				tblT0CheckFileResponseDo.setSettledate(content[9]);
				tblT0CheckFileResponseDo.setResptype(content[10]);
				tblT0CheckFileResponseDo.setRespcode(content[11]);
				tblT0CheckFileResponseDo.setRespmsg(content[12]);
				tblT0CheckFileResponseDoMapper.insert(tblT0CheckFileResponseDo);
			}
			resultMap.put("success", "true");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取接口SMZF024失败");
			throw new BposException("获取接口SMZF024失败");
		}
		return resultMap;
	}

	public String[] parseXmlToStringArray(String respXml) {
		String[] rows;
		try {
			if (!StringUtils.isNotBlank(respXml)) {
				return rows = new String[0];
			}
			Document doc = DocumentHelper.parseText(respXml);
			Map<String, Object> map = (Map<String, Object>) XmlUtil.xml2map(doc.getRootElement());
			Map<String, Object> headMap = (Map<String, Object>) map.get("head");
			String responseCode = headMap.get("respCode") + "";
			if (!responseCode.equals("000000")) {
				return rows = new String[0];
			}
			Map<String, Object> contentMap = (Map<String, Object>) map.get("body");
			if (!StringUtils.isNotBlank(contentMap.get("content") + "")) {
				return rows = new String[0];
			}
			String responseContent = contentMap.get("content").toString();
			if (responseContent.indexOf("########") > -1) {
				responseContent = responseContent.replace("########", "");
			}
			rows = responseContent.split("\n");
		} catch (Exception e) {
			logger.error("解析XML出错");
			throw new BposException("解析XML出错");
		}
		return rows;
	}

	/**
	 * 接口公共方法
	 * 
	 * @param domain
	 * @param settleDate
	 * @param tranCode
	 * @return
	 */
	public String minShengCommonPorts(MsBaseDomain domain, String settleDate, String tranCode) {

		String url = SystemConstant.MSBANK_BASE_URL;
		Map<String, String> resultMap = new HashMap<>();
		String requestMsg = "";
		String date = DateUtil.getCurrentDate();
		String time = DateUtil.getCurrentTime();
		String cooperator = SystemConstant.MSBANK_INST_ID;
		String reqMsgId = settleDate + date + time;
		// String tranCode = "SMZF025";
		String charset = "utf-8";
		String callBack = "";
		String errorCode = "";
		String errorMsg = "";
		Map<String, String> headerMap = new HashMap<>();
		Map<String, String> bodyMap = new HashMap<>();
		logger.info("原始报文：【{}】", requestMsg);
		MinshengHeader minshengHeader = new MinshengHeader();
		minshengHeader.setVersion("1.0.0");
		minshengHeader.setMsgType("01");
		minshengHeader.setReqDate(date + time);
		// MinshengDoGetCheckFile minshengDoGetCheckFile = new
		// MinshengDoGetCheckFile();
		// clazz.setSettleDate(settleDate);
		// clazz.setFileType("1");
		String respXml = "";
		try {
			PrivateKey hzfPriKey = CryptoUtil.getRSAPrivateKeyByFileSuffix(SystemConstant.SELF_RSA_PRV, "pem", null,
					"RSA");

			PublicKey yhPubKey = CryptoUtil.getRSAPublicKeyByFileSuffix(SystemConstant.MSBANK_RSA_PUB, "pem", "RSA");

			headerMap = Bean2Map.beanToMapNoFill(minshengHeader);
			bodyMap = Bean2Map.beanToMapNoFill(domain);
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

			logger.info("获取T1对账文件返回报文[{}]", new Object[] { respStr });
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
			respXml = new String(merchantXmlDataBytes, charset);
			logger.info("respXml[{}]", new Object[] { respXml });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	@Override
	public int getCheckFileListCount(CheckFileForm form) {
		TblCapitalClearingDoExample example = getSearchFiled(form);
		int countByExample = tblCapitalClearingDoMapper.countByExample(example);
		return countByExample;
	}

	@Override
	public int getMsCheckFileListCount(CheckFileForm form) {
		TblMsCheckFileDoExample tblMsCheckFileDoExample = new TblMsCheckFileDoExample();
		tblMsCheckFileDoExample.createCriteria().andSettledateEqualTo(form.getFilterDate());
		int countByExample = tblMsCheckFileDoMapper.countByExample(tblMsCheckFileDoExample);
		return countByExample;
	}

	@Override
	public int getWeChatIncomeCheckFileListCount(CheckFileForm form) {
		TblWechatIncomeCheckFileDoExample tblWechatIncomeCheckFileDoExample = new TblWechatIncomeCheckFileDoExample();
		TblWechatIncomeCheckFileDoExample.Criteria criteria = tblWechatIncomeCheckFileDoExample.createCriteria();
		criteria.andTradeDateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getChekcResp())) {
			criteria.andChekcRespEqualTo(form.getChekcResp());
		}
		int countByExample = tblWechatIncomeCheckFileDoMapper.countByExample(tblWechatIncomeCheckFileDoExample);
		return countByExample;
	}

	@Override
	public int geT1CheckFileListCount(CheckFileForm form) {
		TblT1CheckFileRespondeDoExample tblT1CheckFileRespondeDoExample = new TblT1CheckFileRespondeDoExample();
		TblT1CheckFileRespondeDoExample.Criteria criteria = tblT1CheckFileRespondeDoExample.createCriteria();
		criteria.andSettledateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getMerchantCode())) {
			criteria.andMerchantcodeEqualTo(form.getMerchantCode());
		}
		if (StringUtils.isNotBlank(form.getAccNo())) {
			criteria.andAccnoEqualTo(form.getAccNo());
		}
		if (StringUtils.isNotBlank(form.getAccName())) {
			criteria.andAccnameEqualTo(form.getAccName());
		}
		int countByExample = tblT1CheckFileRespondeDoMapper.countByExample(tblT1CheckFileRespondeDoExample);
		return countByExample;
	}

	@Override
	public int geT0CheckFileListCount(CheckFileForm form) {
		TblT0CheckFileResponseDoExample tblT0CheckFileResponseDoExample = new TblT0CheckFileResponseDoExample();
		TblT0CheckFileResponseDoExample.Criteria criteria = tblT0CheckFileResponseDoExample.createCriteria();
		criteria.andSettledateEqualTo(form.getFilterDate());
		if (StringUtils.isNotBlank(form.getMerchantCode())) {
			criteria.andMerchantcodeEqualTo(form.getMerchantCode());
		}
		if (StringUtils.isNotBlank(form.getAccNo())) {
			criteria.andAccnoEqualTo(form.getAccNo());
		}
		if (StringUtils.isNotBlank(form.getAccName())) {
			criteria.andAccnameEqualTo(form.getAccName());
		}
		int countByExample = tblT0CheckFileResponseDoMapper.countByExample(tblT0CheckFileResponseDoExample);
		return countByExample;
	}

	private TblCapitalClearingDoExample getSearchFiled(CheckFileForm form) {
		String filterDate = form.getFilterDate();
		String merchantCode = form.getMerchantCode();

		TblCapitalClearingDoExample example = new TblCapitalClearingDoExample();
		TblCapitalClearingDoExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(filterDate)) {
			criteria.andSettledateEqualTo(filterDate);
		}
		if (StringUtils.isNotBlank(merchantCode)) {
			criteria.andMerchantcodeEqualTo(merchantCode);
		}
		return example;
	}

	/**
	 * @see TransLogService#exportSettlementInfo(List, ServletOutputStream)
	 */
	@Override
	public void exportSettlementInfo(List<TblT1CheckFileRespondeDo> tblT1CheckFileRespondeDoList,
			ServletOutputStream outputStream, String instCode) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("记录列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);

		String[] titles = { "平台商户号", "清算日期", "商户编号", "交易账号", "交易户名", "清算金额", "提现手续费", "清算状态" };

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
		int i = 0;
		for (TblT1CheckFileRespondeDo tblT1CheckFileRespondeDo : tblT1CheckFileRespondeDoList) {

			String merId = tblT1CheckFileRespondeDo.getMerchantcode();
			String xkMerId = "";

			if (StringUtils.isEmpty(merId) && !StringUtils.isEmpty(instCode))
				continue;

			if (!StringUtils.isEmpty(instCode) && !StringUtils.isEmpty(merId)) {
				TblRouteControlExample tblRouteControlExample = new TblRouteControlExample();
				tblRouteControlExample.createCriteria().andDestMerIdEqualTo(merId).andInstIdEqualTo(instCode);
				List<TblRouteControl> tblRouteControlList = tblRouteControlMapper
						.selectByExample(tblRouteControlExample);
				if (tblRouteControlList.size() == 0)
					continue;
				
				xkMerId = tblRouteControlList.get(0).getMerId();
			}

			row = sheet.createRow(i + 1);
			
			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(xkMerId);

			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(tblT1CheckFileRespondeDo.getSettledate());

			HSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(style);
			cell2.setCellValue(tblT1CheckFileRespondeDo.getMerchantcode());

			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(tblT1CheckFileRespondeDo.getAccno());

			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(tblT1CheckFileRespondeDo.getAccname());

			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			String transAmt = tblT1CheckFileRespondeDo.getDrawamount().stripTrailingZeros().toPlainString();
			cell5.setCellValue(transAmt);

			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);
			String transFee = tblT1CheckFileRespondeDo.getDrawfee().stripTrailingZeros().toPlainString();
			cell6.setCellValue(transFee);

			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(tblT1CheckFileRespondeDo.getRespmsg());

			i++;
		}
		try {
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wb.close();
		}
	}

	/**
	 * @see TransLogService#exportSettlementInfo(List, ServletOutputStream)
	 */
	@Override
	public void exportD0SettlementInfo(List<TblT0CheckFileResponseDo> tblT0CheckFileRespondeDoList,
			ServletOutputStream outputStream, String instCode) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("记录列表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setWrapText(true);

		String[] titles = { "平台商户号", "清算日期", "商户编号", "交易账号", "交易户名", "清算金额", "提现手续费", "清算状态" };

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
		}
		int i = 0;
		for (TblT0CheckFileResponseDo tblT0CheckFileResponseDo : tblT0CheckFileRespondeDoList) {
			row = sheet.createRow(i + 1);
			
			String xkMerId = "";

			String merId = tblT0CheckFileResponseDo.getMerchantcode();
			if (StringUtils.isEmpty(merId))
				continue;

			if (!StringUtils.isEmpty(instCode)) {
				TblRouteControlExample tblRouteControlExample = new TblRouteControlExample();
				tblRouteControlExample.createCriteria().andDestMerIdEqualTo(merId).andInstIdEqualTo(instCode);
				List<TblRouteControl> tblRouteControlList = tblRouteControlMapper
						.selectByExample(tblRouteControlExample);
				if (tblRouteControlList.size() == 0)
					continue;
				
				xkMerId = tblRouteControlList.get(0).getMerId();
			}

			String sAmt = tblT0CheckFileResponseDo.getDrawamount().stripTrailingZeros().toPlainString();
			String sTransFee = tblT0CheckFileResponseDo.getDrawfee().stripTrailingZeros().toPlainString();

			if ("0".equals(sAmt) && "0".equals(sTransFee))
				continue;
			
			HSSFCell cell0 = row.createCell((short) 0);
			cell0.setCellStyle(style);
			cell0.setCellValue(xkMerId);

			HSSFCell cell1 = row.createCell((short) 1);
			cell1.setCellStyle(style);
			cell1.setCellValue(tblT0CheckFileResponseDo.getSettledate());

			HSSFCell cell2 = row.createCell((short) 2);
			cell2.setCellStyle(style);
			cell2.setCellValue(tblT0CheckFileResponseDo.getMerchantcode());

			HSSFCell cell3 = row.createCell((short) 3);
			cell3.setCellStyle(style);
			cell3.setCellValue(tblT0CheckFileResponseDo.getAccno());

			HSSFCell cell4 = row.createCell((short) 4);
			cell4.setCellStyle(style);
			cell4.setCellValue(tblT0CheckFileResponseDo.getAccname());

			HSSFCell cell5 = row.createCell((short) 5);
			cell5.setCellStyle(style);
			String transAmt = tblT0CheckFileResponseDo.getDrawamount().stripTrailingZeros().toPlainString();
			cell5.setCellValue(transAmt);

			HSSFCell cell6 = row.createCell((short) 6);
			cell6.setCellStyle(style);
			String transFee = tblT0CheckFileResponseDo.getDrawfee().stripTrailingZeros().toPlainString();
			cell6.setCellValue(transFee);

			HSSFCell cell7 = row.createCell((short) 7);
			cell7.setCellStyle(style);
			cell7.setCellValue(tblT0CheckFileResponseDo.getRespmsg());

			i++;
		}
		try {
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wb.close();
		}
	}

}
