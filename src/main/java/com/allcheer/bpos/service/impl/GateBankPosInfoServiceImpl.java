package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample;
import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.entity.TblBtsPosInfoDO;
import com.allcheer.bpos.entity.TblBtsPosInfoDOExample;
import com.allcheer.bpos.entity.TblBtsPosInfoDOExample.Criteria;
import com.allcheer.bpos.form.GateBankPosInfoForm;
import com.allcheer.bpos.mapper.TblBtsGateMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsGateRoutDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsPosInfoDOMapper;
import com.allcheer.bpos.service.GateBankPosInfoService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateBankPosInfoServiceImpl implements GateBankPosInfoService {
	
	private Logger logger = LoggerFactory.getLogger(GateBankPosInfoServiceImpl.class);
	
	@Autowired
	TblBtsPosInfoDOMapper tblBtsPosInfoDOMapper;
	@Autowired
	TblBtsGateRoutDOMapper tblBtsGateRoutDOMapper;
	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;
	@Autowired
	TblBtsGateMerInfoDOMapper tblBtsGateMerInfoDOMapper;
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsPosInfoDOExample getSearchFiled(GateBankPosInfoForm form){
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();
		String instId = form.getInstId();
		String batchId = form.getBatchId();
		String instName = form.getInstName();

		TblBtsPosInfoDOExample example = new TblBtsPosInfoDOExample();
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
		if(filedNotNull(instId)){
			criteria.andInstIdEqualTo(instId);
		}
		if(filedNotNull(batchId)){
			criteria.andBatchIdEqualTo(batchId);
		}
		if(filedNotNull(instName)){
			criteria.andInstNameEqualTo(instName);
		}
		return example;
	} 
	
	@Override
	public int countByExample(GateBankPosInfoForm form) {
		TblBtsPosInfoDOExample example = getSearchFiled(form);
		int i = tblBtsPosInfoDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsPosInfoDO> selectByExample(GateBankPosInfoForm form) {
		TblBtsPosInfoDOExample example = getSearchFiled(form);
		List<TblBtsPosInfoDO> list = tblBtsPosInfoDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(GateBankPosInfoForm form) throws BposException {
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			TblBtsPosInfoDOExample example = new TblBtsPosInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblBtsPosInfoDOMapper.deleteByExample(example);
			return i;
		}
		logger.error("删除网关密钥失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("删除网关密钥失败，原因：网关，商户号，终端号为空");
	}

	@Override
	public int insert(TblBtsPosInfoDO record) throws BposException {
		gateRequired(record);
		return tblBtsPosInfoDOMapper.insert(record);
	}

	private void gateRequired(TblBtsPosInfoDO record) throws BposException {
		String gateId = record.getGateId();
		String posMerId = record.getPosMerId();
		String posTermId = record.getPosTermId();
		TblBtsGateRoutDO gateRout = tblBtsGateRoutDOMapper.selectByPrimaryKey(gateId);
		if(gateRout == null){
			logger.error("插入网关【{}】密钥失败，原因：网关不存在",gateId);
			throw new BposException("插入网关密钥失败，原因：网关不存在");
		}
		
		if(filedNotNull(gateId) && filedNotNull(posMerId) && filedNotNull(posTermId)){
			TblBtsGateMerInfoDOExample example = new TblBtsGateMerInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateId).andPosMerIdEqualTo(posMerId).andPosTermIdEqualTo(posTermId);
			int i = tblBtsGateMerInfoDOMapper.countByExample(example);
			if(i > 0){
				return ;
			}
		}
		logger.error("插入网关【{}】密钥失败，原因：网关大商户不存在",gateId);
		throw new BposException("插入网关密钥失败，原因：网关大商户不存在");
	}

	@Override
	public int updateByExample(TblBtsPosInfoDO record) throws BposException {
		String gateid = record.getGateId();
		String merId = record.getPosMerId();
		String termId = record.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			gateRequired(record);
			TblBtsPosInfoDOExample example = new TblBtsPosInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblBtsPosInfoDOMapper.updateByExampleSelective(record, example);
			return i;
		}
		logger.error("更新网关密钥失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("更新网关密钥失败，原因：网关，商户号，终端号为空");
	}

}
