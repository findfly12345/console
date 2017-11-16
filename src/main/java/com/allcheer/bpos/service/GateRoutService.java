package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.form.GateRoutForm;

import java.util.List;

public interface GateRoutService {

	int countByExample(GateRoutForm form);

	int deleteByExample(GateRoutForm form);

	int insert(TblBtsGateRoutDO record);

	List<TblBtsGateRoutDO> selectByExample(GateRoutForm form);

	int updateByExample(TblBtsGateRoutDO record);

	TblBtsGateRoutDO selectByGateId(String id);
}
