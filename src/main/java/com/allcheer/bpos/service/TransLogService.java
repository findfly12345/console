package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.InstProfitDO;
import com.allcheer.bpos.entity.InstProfitSummaryDO;
import com.allcheer.bpos.entity.TblBtsTransLogDO;
import com.allcheer.bpos.form.TransLogForm;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface TransLogService {
	
	List<TblBtsTransLogDO> getTransList(TransLogForm form);

	List<InstProfitDO>  rumGroupCardFlagByInstInstId(TransLogForm form);
	List<InstProfitDO>  rumGroupChannelReport(TransLogForm form);

	int getTransListCount(TransLogForm form);
	
	void exportSettlementInfo(List<TblBtsTransLogDO> transList, ServletOutputStream outputStream) throws Exception;

	List<InstProfitSummaryDO> rumGroupByAgentIdAndType(String agentId, String acctDate, String transStat);

	List<InstProfitSummaryDO> rumGroupByIinstIdAndType(String instId, String acctDate, String transStat);


	int selectInstByFeeAmmtAndAccoDateAndGageIdAndTransStat(String acctDate);




	List<TblBtsTransLogDO> rumByIinstIdAndType( String acctDate);

	int saveTransLog(TblBtsTransLogDO tblBtsTransLogDO);



	//机构分润
	void exporInstProfitInfo(List<InstProfitDO> transList,
							  ServletOutputStream outputStream,TransLogForm trans) throws Exception;

	//机构报表
	void exporchannelReport(List<InstProfitDO> transList,
							 ServletOutputStream outputStream,TransLogForm trans) throws Exception;



}
 