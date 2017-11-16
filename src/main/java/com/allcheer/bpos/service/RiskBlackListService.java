package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblRiskBlackList;
import com.allcheer.bpos.form.RiskBlackListForm;
import com.allcheer.bpos.util.BposException;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface RiskBlackListService {

	int countByExample(RiskBlackListForm form);

	List<TblRiskBlackList> selectByExample(RiskBlackListForm example);

	int deleteByExample(RiskBlackListForm form)  throws BposException;

	int insert(TblRiskBlackList record)  throws BposException;

	int updateByExample(TblRiskBlackList record)  throws BposException;

	void exportRiskTriggerMer(List<TblRiskBlackList> userList, ServletOutputStream outputStream) throws Exception;

}
