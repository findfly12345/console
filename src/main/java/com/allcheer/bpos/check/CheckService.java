package com.allcheer.bpos.check;

import com.allcheer.bpos.check.entity.constant.CheckFlagStatus;
import com.allcheer.bpos.check.entity.constant.TransStatus;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.GateBankCheckDataDOMapper;
import com.allcheer.bpos.mapper.GateBankCheckLogMapper;
import com.allcheer.bpos.mapper.GateParamConfigDOMapper;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.util.JsonUtil;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class CheckService {
	private Logger logger = LoggerFactory.getLogger(CheckService.class);

	private static final String BANK_CHECK_KEY = "bankfile";
	private static final String TRANS_LOG_KEY = "translog";
	
	private static final String STATUS_KEY_CHECKFLAG = "1";
	private static final String STATUS_KEY_TRANSSTAT = "2";
	private static final String STATUS_KEY_BANKSTAT = "3";
	
	private static final String SEPARATOR = ",";
	private static final String CHECK_PATTERN = "yyyyMMdd";

	@Autowired
	GateBankCheckLogMapper gateBankCheckLogMapper;
	@Autowired
	GateParamConfigDOMapper gateParamConfigDOMapper;
	@Autowired
	GateBankCheckDataDOMapper gateBankCheckDataDOMapper;
	@Autowired
	TblBtsTransLogDOMapper tblBtsTransLogDOMapper;
	@Autowired
	CacheSuper cache;
	/**
	 * 问题：对帐文件中跨天交易将会有问题
	 * 		translog只对一个日期的记录
	 * 解决：默认查询前三天的交易，在找不到，呵呵（单边帐）
	 * @param gateId
	 * @param time
	 * @param config
	 */
	public void run(String gateId,Date time,Map<String, String> config){
		cache.dorp();
		logger.debug("开始【{}】对帐。。。", gateId);
		String checkDate = DateFormatUtils.format(time, CHECK_PATTERN);
		logger.debug("【{}】对帐日期【{}】", gateId, checkDate);
		InputStream file = getBankFile(config, checkDate);
		List<GateBankCheckDataDO> checkBean = getBankFileData(gateId,file,checkDate);
		logger.debug("解析到【{}】条数据", checkBean==null?"0null":checkBean.size());
		try {
			file.close();
		} catch (IOException e) {
		}
		if(checkBean==null ||checkBean.size()==0){
			return ;
		}
		saveCache(BANK_CHECK_KEY, checkBean);
		Set<String> transLogIds = getCheckDataIds(checkBean);
		checkBean = getTransLogByGateId(gateId, time);
		logger.debug("translog获取到【{}】条数据", checkBean.size());
		saveCache(TRANS_LOG_KEY, checkBean);
		transLogIds.addAll(getCheckDataIds(checkBean)) ;
		checkBean = null;
		
		GateBankCheckDataDO transLogDataTmp = null;
		GateBankCheckDataDO bankDataTmp = null;
		logger.debug("开始比对数据。。。");
		for (String id : transLogIds) {
			transLogDataTmp = getDataFormCache(TRANS_LOG_KEY, id);
			bankDataTmp = getDataFormCache(BANK_CHECK_KEY, id);
			Map<String,String> checkFlag = checkStatus(transLogDataTmp, bankDataTmp);
			if(transLogDataTmp == null && bankDataTmp != null){
				transLogDataTmp = bankDataTmp;
			}
			if (transLogDataTmp != null && checkFlag !=null) {
				// 性能问题，使用批处理
				transLogDataTmp.setCheckFlag(checkFlag.get(STATUS_KEY_CHECKFLAG));
				gateBankCheckDataDOMapper.insertSelective(transLogDataTmp);
				upTransLog(transLogDataTmp,checkFlag);
			}
		}
		logger.debug("对帐完成");
	}
	
	private void upTransLog(GateBankCheckDataDO transLogDataTmp, Map<String, String> checkFlag) {
		String transStat = checkFlag.get(STATUS_KEY_TRANSSTAT);
		String bankStat = checkFlag.get(STATUS_KEY_BANKSTAT);
		if(transStat != null && bankStat!=null){
			TblBtsTransLogDO record = new TblBtsTransLogDO();
			record.setTransStat(transStat);
			record.setBankStat(bankStat);
			TblBtsTransLogDOExample example = new TblBtsTransLogDOExample();
			example.createCriteria().andGateIdEqualTo(transLogDataTmp.getGateId()).andPosSeqIdEqualTo(transLogDataTmp.getSeqId()).andAcctDateEqualTo(transLogDataTmp.getTransDate());
			int updateByExample = tblBtsTransLogDOMapper.updateByExample(record, example);
			logger.debug("translog 更新了{}条数据",updateByExample);
		}
	}

	private List<GateBankCheckDataDO> getBankFileData(String gateId, InputStream file,String checkDate) {
		List<GateBankCheckDataDO> parseBankFile = parseBankFile(file);
		for(GateBankCheckDataDO data:parseBankFile){
			data.setGateId(gateId);
			data.setCheckDate(checkDate);
		}
		return parseBankFile;
	}

	public void run(String gateId,Date time) {
		Map<String, String> config = getCheckConfig(gateId);
		run(gateId,time,config);
	}

	public abstract List<GateBankCheckDataDO> parseBankFile(InputStream file);

	public abstract InputStream getBankFile(Map<String, String> config, String checkDate);

	public void setCache(CacheSuper cache){
		this.cache = cache;
	}
	
	private Map<String,String> checkStatus(GateBankCheckDataDO transLogDataTmp, GateBankCheckDataDO bankDataTmp) {
		Map<String,String> statusMap = new HashMap<String,String>();
		if (transLogDataTmp == null) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.Q.toString());
			return statusMap;
		}
		if (bankDataTmp == null) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.M.toString());
			return statusMap;
		}
		String transStatus = transLogDataTmp.getTransStatus();
		String bankTransStatus = bankDataTmp.getTransStatus();
		if (TransStatus.S.toString().equals(transStatus) && TransStatus.F.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.Q.toString());
			return statusMap;
		}
		if (TransStatus.F.toString().equals(transStatus) && TransStatus.S.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.R.toString());
			return statusMap;
		}
		if (TransStatus.F.toString().equals(transStatus) && TransStatus.F.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.S.toString());
			return statusMap;
		}
		if (TransStatus.S.toString().equals(transStatus) && TransStatus.S.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.S.toString());
			return statusMap;
		}
		if (TransStatus.I.toString().equals(transStatus) && TransStatus.S.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.S.toString());
			statusMap.put(STATUS_KEY_TRANSSTAT, TransStatus.S.toString());
			statusMap.put(STATUS_KEY_BANKSTAT, TransStatus.S.toString());
			return statusMap;
		}
		if (TransStatus.I.toString().equals(transStatus) && TransStatus.F.toString().equals(bankTransStatus)) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.S.toString());
			statusMap.put(STATUS_KEY_TRANSSTAT, TransStatus.F.toString());
			statusMap.put(STATUS_KEY_BANKSTAT, TransStatus.F.toString());
			return statusMap;
		}
		String transAmt = transLogDataTmp.getTransAmt();
		String bankTransAmt = bankDataTmp.getTransAmt();
		if (transAmt != null && transAmt != bankTransAmt) {
			statusMap.put(STATUS_KEY_CHECKFLAG, CheckFlagStatus.A.toString());
			return statusMap;
		}
		logger.error("对帐错误,bankLog:【{}】,transLog:【{}】",JsonUtil.toJson(bankDataTmp),JsonUtil.toJson(transLogDataTmp));
		return null;
	}

	private GateBankCheckDataDO getDataFormCache(String transLogKey, String id) {
		String mapValue = cache.getMapValue(transLogKey, id);
		if (mapValue == null) {
			return null;
		}
		String[] identity = id.split(SEPARATOR);
		String[] transStat = mapValue.split(SEPARATOR);
		GateBankCheckDataDO checkData = new GateBankCheckDataDO();
		checkData.setGateId(identity[0]);
		checkData.setSeqId(identity[1]);
		checkData.setTransDate(identity[2]);

		checkData.setAcctId(transStat[0]);
		checkData.setTransAmt(transStat[1]);
		checkData.setTransStatus(transStat[2]);
		checkData.setCheckDate(transStat[3]);

		return checkData;
	}

	private Set<String> getCheckDataIds(List<GateBankCheckDataDO> checkBean) {
		Set<String> keySet = new HashSet<String>();
		for (GateBankCheckDataDO checkData : checkBean) {
			keySet.add(MapKey(checkData));
		}
		return keySet;
	}

	private List<GateBankCheckDataDO> getTransLogByGateId(String gateId, Date endCheckDate) {
		Date startTrans = DateUtils.addDays(endCheckDate, -3);
		String startTransDate = DateFormatUtils.format(startTrans, CHECK_PATTERN);
		String endTransDate = DateFormatUtils.format(endCheckDate, CHECK_PATTERN);
		
		List<GateBankCheckDataDO> checkDataList = new ArrayList<GateBankCheckDataDO>();
		GateBankCheckDataDO checkData = null;
		TblBtsTransLogDOExample example = new TblBtsTransLogDOExample();
		example.createCriteria().andGateIdEqualTo(gateId).andTransDateTimeGreaterThanOrEqualTo(startTransDate).andTransDateTimeLessThanOrEqualTo(endTransDate);
		List<TblBtsTransLogDO> list = tblBtsTransLogDOMapper.selectByExample(example);
		for (TblBtsTransLogDO translog : list) {
			checkData = new GateBankCheckDataDO();
			checkData.setGateId(gateId);
			checkData.setSeqId(translog.getPosSeqId());
			checkData.setTransDate(translog.getAcctDate());

			checkData.setTransAmt(translog.getOrdAmt());
			checkData.setAcctId(translog.getCardNo());
			checkData.setTransStatus(translog.getTransStat());
			checkData.setCheckDate(endTransDate);
			checkDataList.add(checkData);
		}
		return checkDataList;
	}

	private void saveCache(String string, List<GateBankCheckDataDO> checkBean) {
		if(string == null || checkBean == null || checkBean.size() == 0){
			logger.debug("没有数据插入cache");
			return;
		}
		String id = null;
		String value = null;
		Map<String, String> map = new HashMap<String, String>();
		for (GateBankCheckDataDO data : checkBean) {
			id = MapKey(data);
			value = data.getAcctId() + SEPARATOR + data.getTransAmt() + SEPARATOR + data.getTransStatus() + SEPARATOR + data.getCheckDate();
			map.put(id, value);
		}
		cache.batchPut(string, map);
	}

	public String MapKey(GateBankCheckDataDO data) {
		return data.getGateId() + SEPARATOR + data.getSeqId() + SEPARATOR + data.getTransDate();
	}

	private Map<String, String> getCheckConfig(String gateId) {
		Map<String, String> configMap = new HashMap<String, String>();
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		example.createCriteria().andGateIdEqualTo(gateId);
		List<GateParamConfigDO> configList = gateParamConfigDOMapper.selectByExample(example);
		String key = null;
		String value = null;
		for (GateParamConfigDO config : configList) {
			key = config.getParamKey();
			value = config.getParamValue();
			configMap.put(key, value);
		}
		logger.debug("config setting:{}", JsonUtil.toJson(configMap));
		return configMap;
	}
}
