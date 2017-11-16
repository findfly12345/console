package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.GateBankCheckLog;
import com.allcheer.bpos.form.CheckLogForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface CheckLogService {
	
	int countByExample(CheckLogForm form);

	List<GateBankCheckLog> selectByExample(CheckLogForm form);
	
	int deleteByExample(CheckLogForm form)  throws BposException;

	int insert(GateBankCheckLog record);

	int updateByExample(GateBankCheckLog record);

}
