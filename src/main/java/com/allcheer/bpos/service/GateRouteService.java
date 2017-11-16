package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBankPosInfoDO;
import com.allcheer.bpos.entity.TblBtsInstRouteDO;
import com.allcheer.bpos.entity.TblGateRoute;
import com.allcheer.bpos.form.GateRouteForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;
import java.util.Map;

public interface GateRouteService {

	int countByExample(GateRouteForm example);

	List<TblGateRoute> selectByExample(GateRouteForm example);

	TblGateRoute selectByPrimaryKey(String gateId);

	int deleteByPrimaryKey(String instId);

	int insert(TblGateRoute record) throws BposException;

	int updateByPrimaryKey(TblGateRoute record);

	public int getPlatFormGateRouteId();

	public Map unbindMerGateRoute(String instMerId,String instMerTermId);

	public int insertInstRoute(TblBtsInstRouteDO tblBtsInstRouteDO);

	public int updateAgentMerTermById(String instMerId,String instMerTermId,String stat);

	public int insertBankPosInfo(TblBankPosInfoDO tblBankPosInfoDO);

	public Map<String, Object> getBindRouteDetail(String instMerId, String instMerTermId);

	public Map<String, String> auditPass(String instMerId, String username);

	public Map<String, String> auditRevert(String instMerId, String username);
	

}
