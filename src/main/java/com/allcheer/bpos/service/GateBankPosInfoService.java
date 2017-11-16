package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsPosInfoDO;
import com.allcheer.bpos.form.GateBankPosInfoForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface GateBankPosInfoService {

	int countByExample(GateBankPosInfoForm form);

	List<TblBtsPosInfoDO> selectByExample(GateBankPosInfoForm form);

	int deleteByExample(GateBankPosInfoForm form)  throws BposException;

	int insert(TblBtsPosInfoDO record)  throws BposException;

	int updateByExample(TblBtsPosInfoDO record)  throws BposException;
	
}
