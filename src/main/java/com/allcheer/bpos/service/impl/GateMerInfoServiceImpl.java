package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDO;
import com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample;
import com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample.Criteria;
import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.form.GateMerInfoForm;
import com.allcheer.bpos.mapper.TblBtsGateMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsGateRoutDOMapper;
import com.allcheer.bpos.service.GateMerInfoService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateMerInfoServiceImpl implements GateMerInfoService {
	private Logger logger = LoggerFactory.getLogger(GateMerInfoServiceImpl.class);
	
	@Autowired
	TblBtsGateMerInfoDOMapper tblBtsGateMerInfoDOMapper;
	@Autowired
	TblBtsGateRoutDOMapper tblBtsGateRoutDOMapper;
	
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsGateMerInfoDOExample getSearchFiled(GateMerInfoForm form){
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();
		String posMerType = form.getPosMerType();
		String posMerStat = form.getPosMerStat();

		TblBtsGateMerInfoDOExample example = new TblBtsGateMerInfoDOExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(gateid)) {
			criteria.andGateIdEqualTo(gateid);
		}
		if (filedNotNull(merId)) {
			criteria.andPosMerIdEqualTo(merId);
		}
		if(filedNotNull(termId)){
			criteria.andPosTermIdEqualTo(termId);
		}
		if(filedNotNull(posMerType)){
			criteria.andPosMerTypeEqualTo(posMerType);
		}
		if(filedNotNull(posMerStat)){
			criteria.andPosMerStatEqualTo(posMerStat);
		}
		return example;
	} 
	
	@Override
	public int countByExample(GateMerInfoForm form) {
		TblBtsGateMerInfoDOExample example = getSearchFiled(form);
		int i = tblBtsGateMerInfoDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsGateMerInfoDO> selectByExample(GateMerInfoForm form) {
		TblBtsGateMerInfoDOExample example = getSearchFiled(form);
		List<TblBtsGateMerInfoDO> list = tblBtsGateMerInfoDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(GateMerInfoForm form) throws BposException {
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			TblBtsGateMerInfoDOExample example = new TblBtsGateMerInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblBtsGateMerInfoDOMapper.deleteByExample(example);
			return i;
		}
		logger.error("删除网关大商户失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("删除网关大商户失败，原因：网关，商户号，终端号为空");
	}

	@Override
	public int insert(TblBtsGateMerInfoDO record) throws BposException {
		gateRequired(record);
		return tblBtsGateMerInfoDOMapper.insertSelective(record);
	}

	@Override
	public int updateByExample(TblBtsGateMerInfoDO record) throws BposException {
		String gateid = record.getGateId();
		String merId = record.getPosMerId();
		String termId = record.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			gateRequired(record);
			TblBtsGateMerInfoDOExample example = new TblBtsGateMerInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblBtsGateMerInfoDOMapper.updateByExampleSelective(record, example);
			return i;
		}
		logger.error("更新网关大商户失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("更新网大商户钥失败，原因：网关，商户号，终端号为空");
	}
	
	private void gateRequired(TblBtsGateMerInfoDO record) throws BposException {
		String gateId = record.getGateId();
		TblBtsGateRoutDO gateRout = tblBtsGateRoutDOMapper.selectByPrimaryKey(gateId);
		if(gateRout == null){
			logger.error("插入网关【{}】大商户失败，原因：网关不存在",gateId);
			throw new BposException("插入网关大商户失败，原因：网关不存在");
		}
	}

}
