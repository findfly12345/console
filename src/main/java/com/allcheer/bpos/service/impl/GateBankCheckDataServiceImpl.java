package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.check.entity.constant.CheckStatus;
import com.allcheer.bpos.check.entity.constant.TransStatus;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.GateBankCheckDataDOExample.Criteria;
import com.allcheer.bpos.form.GateBankCheckDataForm;
import com.allcheer.bpos.mapper.GateBankCheckDataDOMapper;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.service.GateBankCheckDataService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GateBankCheckDataServiceImpl implements GateBankCheckDataService {
	private Logger logger = LoggerFactory.getLogger(GateBankCheckDataServiceImpl.class);

	@Autowired
	GateBankCheckDataDOMapper gateBankCheckDataDOMapper;
	@Autowired
	TblBtsTransLogDOMapper tblBtsTransLogDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	private GateBankCheckDataDOExample getSearchFiled(GateBankCheckDataForm form) {
		String gateid = form.getGateId();
		String accId = form.getAcctId();
		String acctName = form.getAcctName();
		String checkFlag = form.getCheckFlag();
		String phone = form.getPhone();
		String seqId = form.getSeqId();
		String transDate = form.getTransDate();
		String transStatus = form.getTransStatus();

		GateBankCheckDataDOExample example = new GateBankCheckDataDOExample();
		Criteria criteria = example.createCriteria();

		if (filedNotNull(gateid)) {
			criteria.andGateIdEqualTo(gateid);
		}
		if (filedNotNull(accId)) {
			criteria.andAcctIdEqualTo(accId);
		}
		if (filedNotNull(acctName)) {
			criteria.andAcctNameEqualTo(acctName);
		}
		if (filedNotNull(checkFlag)) {
			criteria.andCheckFlagEqualTo(checkFlag);
		}
		if (filedNotNull(phone)) {
			criteria.andPhoneEqualTo(phone);
		}
		if (filedNotNull(seqId)) {
			criteria.andSeqIdEqualTo(seqId);
		}
		if (filedNotNull(transDate)) {
			criteria.andTransDateEqualTo(transDate);
		}
		if (filedNotNull(transStatus)) {
			criteria.andTransStatusEqualTo(transStatus);
		}
		return example;
	}

	@Override
	public int countByExample(GateBankCheckDataForm form) {
		GateBankCheckDataDOExample example = getSearchFiled(form);
		int i = gateBankCheckDataDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<GateBankCheckDataDO> selectByExample(GateBankCheckDataForm form) {
		GateBankCheckDataDOExample example = getSearchFiled(form);
		List<GateBankCheckDataDO> list = gateBankCheckDataDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(GateBankCheckDataForm form) {
		GateBankCheckDataDOKey key = new GateBankCheckDataDOKey();
		key.setGateId(form.getGateId());
		key.setSeqId(form.getSeqId());
		key.setTransDate(form.getTransDate());
		int i = gateBankCheckDataDOMapper.deleteByPrimaryKey(key);
		return i;
	}

	@Override
	public int insert(GateBankCheckDataDO record) {
		int i = gateBankCheckDataDOMapper.insertSelective(record);
		return i;
	}

	@Override
	@Transactional
	public int update(GateBankCheckDataDO record) throws BposException {
		int i = gateBankCheckDataDOMapper.updateByPrimaryKeySelective(record);
		
		String gateId = record.getGateId();
		String seqId = record.getSeqId();
		String transDate = record.getTransDate();
		TblBtsTransLogDO transLog = new TblBtsTransLogDO();
		transLog.setTransStat(TransStatus.S.toString());
		transLog.setChkFlag(CheckStatus.S.toString());
		TblBtsTransLogDOExample example = new TblBtsTransLogDOExample();
		example.createCriteria().andGateIdEqualTo(gateId).andPosSeqIdEqualTo(seqId).andTransDateTimeEqualTo(transDate);
		int j = tblBtsTransLogDOMapper.updateByExampleSelective(transLog, example);
		if(j>0){
			logger.warn("网关【{}】，流水号【{}】，交易时间【{}】的记录被手动更改为交易成功",gateId,seqId,transDate);
		}else{
			throw new BposException("找不到交易记录，更改失败");
		}
		return i;
	}

}
