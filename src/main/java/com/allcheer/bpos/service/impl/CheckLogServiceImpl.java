package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.GateBankCheckDataDOExample;
import com.allcheer.bpos.entity.GateBankCheckLog;
import com.allcheer.bpos.entity.GateBankCheckLogExample;
import com.allcheer.bpos.entity.GateBankCheckLogExample.Criteria;
import com.allcheer.bpos.form.CheckLogForm;
import com.allcheer.bpos.mapper.GateBankCheckDataDOMapper;
import com.allcheer.bpos.mapper.GateBankCheckLogMapper;
import com.allcheer.bpos.service.CheckLogService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CheckLogServiceImpl implements CheckLogService {
	private Logger logger = LoggerFactory.getLogger(CheckLogServiceImpl.class);

	@Autowired
	GateBankCheckLogMapper gateBankCheckLogMapper;
	@Autowired
	GateBankCheckDataDOMapper gateBankCheckDataDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	private GateBankCheckLogExample getSearchFiled(CheckLogForm form) {
		String gateid = form.getGateId();
		String checkDate = form.getBankCheckDate();

		GateBankCheckLogExample example = new GateBankCheckLogExample();
		Criteria criteria = example.createCriteria();

		if (filedNotNull(gateid)) {
			criteria.andGateIdEqualTo(gateid);
		}
		if (filedNotNull(checkDate)) {
			criteria.andBankCheckDateEqualTo(checkDate);
		}
		return example;
	}

	@Override
	public int countByExample(CheckLogForm form) {
		GateBankCheckLogExample example = getSearchFiled(form);
		int i = gateBankCheckLogMapper.countByExample(example);
		return i;
	}

	@Override
	public List<GateBankCheckLog> selectByExample(CheckLogForm form) {
		GateBankCheckLogExample example = getSearchFiled(form);
		List<GateBankCheckLog> list = gateBankCheckLogMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional
	public int deleteByExample(CheckLogForm form) throws BposException {
		String gateId = form.getGateId();
		String bankCheckDate = form.getBankCheckDate();
		try {
			GateBankCheckLogExample example = new GateBankCheckLogExample();
			example.createCriteria().andGateIdEqualTo(gateId).andBankCheckDateEqualTo(bankCheckDate);
			int i = gateBankCheckLogMapper.deleteByExample(example);
			GateBankCheckDataDOExample example1 = new GateBankCheckDataDOExample();
			example1.createCriteria().andGateIdEqualTo(gateId).andTransDateEqualTo(bankCheckDate);
			gateBankCheckDataDOMapper.deleteByExample(example1);
			return i;
		} catch (Exception e) {
			logger.error("网关【{}】,交易日期【{}】删除对帐记录失败",gateId,bankCheckDate);
			throw new BposException("删除对帐记录失败");
		}
	}

	@Override
	public int insert(GateBankCheckLog record) {
		return gateBankCheckLogMapper.insertSelective(record);
	}

	@Override
	public int updateByExample(GateBankCheckLog record) {
		GateBankCheckLogExample example = new GateBankCheckLogExample();
		example.createCriteria().andGateIdEqualTo(record.getGateId())
				.andBankCheckDateEqualTo(record.getBankCheckDate());
		int i = gateBankCheckLogMapper.updateByExampleSelective(record, example);
		return i;
	}

}
