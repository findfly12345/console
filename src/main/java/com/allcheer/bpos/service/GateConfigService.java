package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.GateParamConfigDO;

import java.util.List;

public interface GateConfigService {
	int countByExample(String gateId, String key);

	List<GateParamConfigDO> selectByExample(String gateId, String key);

	int deleteByExample(String gateId, String key);

	int insert(GateParamConfigDO record);

	int updateByExample(GateParamConfigDO record);
	
	int updateByExample(String gateId, String key,String value);
}
