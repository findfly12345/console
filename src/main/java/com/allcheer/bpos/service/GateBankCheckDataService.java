package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.GateBankCheckDataDO;
import com.allcheer.bpos.form.GateBankCheckDataForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface GateBankCheckDataService {

	int countByExample(GateBankCheckDataForm form);

	List<GateBankCheckDataDO> selectByExample(GateBankCheckDataForm form);

	int deleteByExample(GateBankCheckDataForm form);

	int insert(GateBankCheckDataDO record);

	int update(GateBankCheckDataDO record) throws BposException;
}
