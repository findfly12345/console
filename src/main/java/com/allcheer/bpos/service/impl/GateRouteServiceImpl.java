package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.TblGateRouteExample.Criteria;
import com.allcheer.bpos.form.GateRouteForm;
import com.allcheer.bpos.mapper.*;
import com.allcheer.bpos.service.GateRouteService;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GateRouteServiceImpl implements GateRouteService {

	private Logger logger = LoggerFactory.getLogger(GateRouteServiceImpl.class);

	@Autowired
	TblGateRouteMapper tblGateRouteMapper;
	@Autowired
	TblBtsInstRouteDOMapper tblBtsInstRouteDOMapper;
	@Autowired
	TblBankPosInfoDOMapper tblBankPosInfoDOMapper;
	@Autowired
	TblAgentMerTermDoMapper tblAgentMerTermDoMapper;
	@Autowired
	SeqMapper seqMapper;
	@Autowired
	TblMerInfoDOMapper tblMerInfoDOMapper;
	@Autowired
    TblMerAuditRecordDOMapper tblMerAuditRecordDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	private TblGateRouteExample getGateRouteSearchField(GateRouteForm form) {
		String gateId = form.getGateId();
		String gateType = form.getGateType();
		String gateDesp = form.getGateDesp();
		String gateFee = form.getGateFee();
		String gateStat = form.getGateStat();
		String remark = form.getRemark();

		TblGateRouteExample example = new TblGateRouteExample();
		Criteria criteria = example.createCriteria();

		if (filedNotNull(gateId)) {
			criteria.andGateIdEqualTo(gateId);
		}

		if (filedNotNull(gateType)) {
			criteria.andGateTypeEqualTo(gateType);
		}

		if (filedNotNull(gateDesp)) {
			criteria.andGateDespLike("%"+gateDesp+"%");
		}

		if (filedNotNull(gateFee)) {
			criteria.andGateFeeEqualTo(gateFee);
		}

		if (filedNotNull(gateStat)) {
			criteria.andGateStatEqualTo(gateStat);
		}

		if (filedNotNull(remark)) {
			criteria.andRemarkEqualTo(remark);
		}

		return example;
	}

	@Override
	public int countByExample(GateRouteForm form) {
		TblGateRouteExample example = getGateRouteSearchField(form);
		int i = tblGateRouteMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblGateRoute> selectByExample(GateRouteForm form) {
		TblGateRouteExample example = getGateRouteSearchField(form);
		List<TblGateRoute> list = tblGateRouteMapper.selectByExample(example);
		return list;
	}

	@Override
	public TblGateRoute selectByPrimaryKey(String gateId) {
		return tblGateRouteMapper.selectByPrimaryKey(gateId);
	}

	@Override
	public int deleteByPrimaryKey(String instId) {
		return tblGateRouteMapper.deleteByPrimaryKey(instId);
	}

	@Override
	public int insert(TblGateRoute record) throws BposException {
		int i = tblGateRouteMapper.insert(record);
		
		return i;
	}

	@Override
	public int updateByPrimaryKey(TblGateRoute record) {
		logger.debug("网关号: " + record.getGateId() + "网关类型: " + record.getGateType());

		TblGateRouteExample example = new TblGateRouteExample();
		example.createCriteria().andGateIdEqualTo(record.getGateId());

		int j = tblGateRouteMapper.updateByPrimaryKeySelective(record);
		logger.debug("更新成功");

		return j;
	}

	@Override
	public int getPlatFormGateRouteId() {
		long platFormInstRouteSeq = tblBtsInstRouteDOMapper.getId();
		return Integer.parseInt(platFormInstRouteSeq+"");
	}
	
	@Override
//	@Transactional
	public Map unbindMerGateRoute(String instMerId, String instMerTermId) {
		Map resultMap = new HashMap();
		try {
			TblBtsInstRouteDOExample tblBtsInstRouteDOExample = new TblBtsInstRouteDOExample();
			tblBtsInstRouteDOExample.createCriteria().andInstMerIdEqualTo(instMerId).andInstMerTermIdEqualTo(instMerTermId);
			List<TblBtsInstRouteDO> tblBtsInstRouteDOList = tblBtsInstRouteDOMapper.selectByExample(tblBtsInstRouteDOExample);
			if(tblBtsInstRouteDOList != null && tblBtsInstRouteDOList.size() > 0) {
				TblBtsInstRouteDO tblBtsInstRouteDO = tblBtsInstRouteDOList.get(0);
				TblBankPosInfoDOExample tblBankPosInfoDOExample = new TblBankPosInfoDOExample();
				tblBankPosInfoDOExample.createCriteria().andPosMerIdEqualTo(tblBtsInstRouteDO.getBankMerId())
						.andPosTermIdEqualTo(tblBtsInstRouteDO.getBankTermId());
				List<TblBankPosInfoDO> tblBankPosInfoDOList = tblBankPosInfoDOMapper.selectByExample(tblBankPosInfoDOExample);
				if(tblBankPosInfoDOList != null && tblBankPosInfoDOList.size() > 0) {
//					TblBankPosInfoDO tblBankPosInfoDO = tblBankPosInfoDOList.get(0);
					tblBankPosInfoDOMapper.deleteByExample(tblBankPosInfoDOExample);
				}
				tblBtsInstRouteDOMapper.deleteByPrimaryKey(tblBtsInstRouteDO.getRouteSeq());
				resultMap.put("statusCode", 200);
				resultMap.put("message", "商户路由解绑成功");
//				return resultMap;
			}else {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "未找到匹配数据");
//				return resultMap;
			}
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("statusCode", 300);
			resultMap.put("message", "绑定时出现异常");
			throw new BposException("绑定时出现异常");
		}
		return resultMap;
	}

	@Override
//	@Transactional
	public int insertInstRoute(TblBtsInstRouteDO tblBtsInstRouteDO) {
		int i = tblBtsInstRouteDOMapper.insertSelective(tblBtsInstRouteDO);
		return i;
	}

	@Override
//	@Transactional
	public int insertBankPosInfo(TblBankPosInfoDO tblBankPosInfoDO) {
		int i = 0;
		TblBankPosInfoDOExample tblBankPosInfoDOExample = new TblBankPosInfoDOExample();
		tblBankPosInfoDOExample.createCriteria().andGateIdEqualTo(tblBankPosInfoDO.getGateId());
		
		List<TblBankPosInfoDO> tblBankPosInfoDOList = tblBankPosInfoDOMapper.selectByExample(tblBankPosInfoDOExample);
		if (tblBankPosInfoDOList != null && tblBankPosInfoDOList.size() > 0) {
			TblBankPosInfoDO tblBankPosInfoDO1 = tblBankPosInfoDOList.get(0);
			tblBankPosInfoDO.setBatchId(tblBankPosInfoDO1.getBatchId());
			tblBankPosInfoDO.setPinKey(tblBankPosInfoDO1.getPinKey());
			tblBankPosInfoDO.setInstId(tblBankPosInfoDO1.getInstId());
			tblBankPosInfoDO.setInstName(tblBankPosInfoDO1.getInstName());
			tblBankPosInfoDO.setMacKey(tblBankPosInfoDO1.getMacKey());
			tblBankPosInfoDO.setMainKey(tblBankPosInfoDO1.getMainKey());
			tblBankPosInfoDO.setTdKey(tblBankPosInfoDO1.getTdKey());
			i = tblBankPosInfoDOMapper.insertSelective(tblBankPosInfoDO);
		}
		return i;
	}

	@Override
//	@Transactional
	public int updateAgentMerTermById(String instMerId,String instMerTermId,String stat) {
		int i = 0;
		TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
		tblAgentMerTermDoExample.createCriteria().andMerIdEqualTo(instMerId).andTermIdEqualTo(instMerTermId);
		List<TblAgentMerTermDo> tblAgentMerTermDoList = tblAgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
		if(tblAgentMerTermDoList != null && tblAgentMerTermDoList.size() > 0) {
			TblAgentMerTermDo tblAgentMerTermDo = tblAgentMerTermDoList.get(0);
			tblAgentMerTermDo.setGateBindStat(stat);
			tblAgentMerTermDoMapper.updateByExampleSelective(tblAgentMerTermDo,tblAgentMerTermDoExample);
			i = 1;
		}
		return  i;
	}

	@Override
	public Map<String, Object> getBindRouteDetail(String instMerId, String instMerTermId) {
        Map map = new HashMap();        
		TblBtsInstRouteDOExample tblBtsInstRouteDOExample = new TblBtsInstRouteDOExample();
		tblBtsInstRouteDOExample.createCriteria().andInstMerIdEqualTo(instMerId).andInstMerTermIdEqualTo(instMerTermId);
		List<TblBtsInstRouteDO> tblBtsInstRouteDOList = tblBtsInstRouteDOMapper.selectByExample(tblBtsInstRouteDOExample);		
		if(tblBtsInstRouteDOList != null && tblBtsInstRouteDOList.size() > 0) {
			TblBtsInstRouteDO tblBtsInstRouteDO = tblBtsInstRouteDOList.get(0);
			TblBankPosInfoDOExample tblBankPosInfoDOExample = new TblBankPosInfoDOExample();
			tblBankPosInfoDOExample.createCriteria().andPosMerIdEqualTo(tblBtsInstRouteDO.getBankMerId())
					.andPosTermIdEqualTo(tblBtsInstRouteDO.getBankTermId());
			List<TblBankPosInfoDO> tblBankPosInfoDOList = tblBankPosInfoDOMapper.selectByExample(tblBankPosInfoDOExample);
			if(tblBankPosInfoDOList != null && tblBankPosInfoDOList.size() > 0) {
			   map.put("gateId", tblBankPosInfoDOList.get(0).getGateId());
			   map.put("posMerId", tblBankPosInfoDOList.get(0).getPosMerId());
			   map.put("posTermId", tblBankPosInfoDOList.get(0).getPosTermId());
			}
		}else{
			map.put("statusCode", 300);
			map.put("message", "未找到匹配数据");
		}
		return map;
	}
	
	/**
	 * 绑定路由结束之后,更新商户状态为 - 审核通过
	 * @param merId
	 * @param userName
	 * @return
	 */
	
	public Map<String, String> auditPass(String merId, String userName) {
		
		Map<String, String> returnMap = new HashMap<String, String>();
		
		TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
		if (tblMerInfoDO == null) {
			returnMap.put("result", "MER NOT FOUND");
			return returnMap;
		}
		TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
		tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId).andMerStatEqualTo(Constant.AUDIT_IN);
		tblMerInfoDO.setMerStat(Constant.AUDIT_OK);
		int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);
		if (res != 0) {
			TblMerAuditRecordDO tblMerAuditRecordDO = new TblMerAuditRecordDO();
			tblMerAuditRecordDO.setAuditId(String.valueOf(seqMapper.getSequenceNextVal("MER_AGENT_AUDIT_SEQ")));
			tblMerAuditRecordDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerAuditRecordDO.setAuditResult(Constant.AUDIT_OK);
			tblMerAuditRecordDO.setRemark("绑定路由");
			tblMerAuditRecordDO.setUserName(userName);
			tblMerAuditRecordDO.setMerId(merId);
			tblMerAuditRecordDOMapper.insert(tblMerAuditRecordDO);
		    returnMap.put("result", "SUCCESS");
		}
		return returnMap;
	}

	/**
	 * 接触路由绑定结束之后,更新商户状态为 - 审核中
	 * @param merId
	 * @param userName
	 * @return
	 */
	public Map<String, String> auditRevert(String merId, String username) {
		
		Map<String, String> returnMap = new HashMap<String, String>();
		
		TblMerInfoDO tblMerInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
		if (tblMerInfoDO == null) {
			returnMap.put("result", "MER NOT FOUND");
			return returnMap;
		}
		TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
		tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId).andMerStatEqualTo(Constant.AUDIT_OK);
		tblMerInfoDO.setMerStat(Constant.AUDIT_IN);
		int res = tblMerInfoDOMapper.updateByExample(tblMerInfoDO, tblMerInfoDOExample);
		if (res != 0) {
			TblMerAuditRecordDO tblMerAuditRecordDO = new TblMerAuditRecordDO();
			tblMerAuditRecordDO.setAuditId(String.valueOf(seqMapper.getSequenceNextVal("MER_AGENT_AUDIT_SEQ")));
			tblMerAuditRecordDO.setCreateTime(DateUtil.getCurrentDate() + DateUtil.getCurrentTime());
			tblMerAuditRecordDO.setRemark("解绑路由");
			tblMerAuditRecordDO.setUserName(username);
			tblMerAuditRecordDO.setMerId(merId);
			tblMerAuditRecordDO.setAuditResult(Constant.AUDIT_IN);
			tblMerAuditRecordDOMapper.insert(tblMerAuditRecordDO);
			returnMap.put("result", "SUCCESS");
		}
		return returnMap;
	}	
	
	
	
	
	
}
