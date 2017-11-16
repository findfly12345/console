package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.GateParamConfigDO;
import com.allcheer.bpos.entity.GateParamConfigDOExample;
import com.allcheer.bpos.mapper.GateParamConfigDOMapper;
import com.allcheer.bpos.service.GateConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateConfigServiceImpl implements GateConfigService {
	private Logger logger = LoggerFactory.getLogger(GateConfigServiceImpl.class);
	@Autowired
	GateParamConfigDOMapper gateParamConfigDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	@Override
	public int countByExample(String gateId, String key) {
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		com.allcheer.bpos.entity.GateParamConfigDOExample.Criteria criteria = example.createCriteria();
		if(filedNotNull(gateId)){
			criteria.andGateIdEqualTo(gateId);
		}
		if(filedNotNull(key)){
			criteria.andParamKeyEqualTo(key);
		}
		int i = gateParamConfigDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<GateParamConfigDO> selectByExample(String gateId, String key) {
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		com.allcheer.bpos.entity.GateParamConfigDOExample.Criteria criteria = example.createCriteria();
		if(filedNotNull(gateId)){
			criteria.andGateIdEqualTo(gateId);
		}
		if(filedNotNull(key)){
			criteria.andParamKeyEqualTo(key);
		}
		List<GateParamConfigDO> list = gateParamConfigDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(String gateId, String key) {
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		example.createCriteria().andGateIdEqualTo(gateId).andParamKeyEqualTo(key);
		int i = gateParamConfigDOMapper.deleteByExample(example);
		return i;
	}

	@Override
	public int insert(GateParamConfigDO record) {
		return gateParamConfigDOMapper.insertSelective(record);
	}

	@Override
	public int updateByExample(GateParamConfigDO record) {
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		example.createCriteria().andGateIdEqualTo(record.getGateId()).andParamKeyEqualTo(record.getParamKey());
		int i = gateParamConfigDOMapper.updateByExampleSelective(record, example);
		return i;
	}

	@Override
	public int updateByExample(String gateId, String key, String value) {
		GateParamConfigDOExample example = new GateParamConfigDOExample();
		example.createCriteria().andGateIdEqualTo(gateId).andParamKeyEqualTo(key);
		GateParamConfigDO record = new GateParamConfigDO();
		record.setParamValue(value);
		int i = gateParamConfigDOMapper.updateByExampleSelective(record, example);
		return i;
	}
}
