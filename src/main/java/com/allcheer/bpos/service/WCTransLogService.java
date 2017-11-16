package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.InstProfitSummaryDO;
import com.allcheer.bpos.entity.WCINCOMEDO;
import com.allcheer.bpos.form.WCTransLogForm;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface WCTransLogService {

	List<WCINCOMEDO> getTransList(WCTransLogForm form);

	List<WCINCOMEDO> getTransListForExport(WCTransLogForm form);

	int getWCIncomeListCount(WCTransLogForm form);

	void exportSettlementInfo(List<WCINCOMEDO> transList, ServletOutputStream outputStream) throws Exception;
	
	List<InstProfitSummaryDO> rumGroupByInstIdAndType(String instId, String transDate,
			String incomeStatus);

	List<InstProfitSummaryDO> rumGroupByAgentIdAndType(String agentId, String transDate,String incomeStatus);


	
}
