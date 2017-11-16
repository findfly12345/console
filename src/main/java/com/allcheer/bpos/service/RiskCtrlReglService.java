package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblRiskCtrlRegl;
import com.allcheer.bpos.form.RiskCtrlReglListForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface RiskCtrlReglService {

	int countByExample(RiskCtrlReglListForm form);

	List<TblRiskCtrlRegl> selectByExample(RiskCtrlReglListForm example);

	int deleteByExample(RiskCtrlReglListForm form)  throws BposException;

	int insert(TblRiskCtrlRegl record)  throws BposException;

	int updateByExample(TblRiskCtrlRegl record)  throws BposException;

}
