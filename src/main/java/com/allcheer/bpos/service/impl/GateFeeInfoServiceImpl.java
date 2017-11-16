package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.entity.TblFeeInfoDO;
import com.allcheer.bpos.entity.TblFeeInfoDOExample;
import com.allcheer.bpos.entity.TblFeeInfoDOExample.Criteria;
import com.allcheer.bpos.form.GateFeeInfoForm;
import com.allcheer.bpos.mapper.TblBtsGateRoutDOMapper;
import com.allcheer.bpos.mapper.TblFeeInfoDOMapper;
import com.allcheer.bpos.service.GateFeeInfoService;
import com.allcheer.bpos.util.BposException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateFeeInfoServiceImpl implements GateFeeInfoService{
	private Logger logger = LoggerFactory.getLogger(GateFeeInfoServiceImpl.class);
	
	@Autowired
	TblFeeInfoDOMapper tblFeeInfoDOMapper;
	@Autowired
	TblBtsGateRoutDOMapper tblBtsGateRoutDOMapper;	
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblFeeInfoDOExample getSearchFiled(GateFeeInfoForm form){
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();

		TblFeeInfoDOExample example = new TblFeeInfoDOExample();
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
		return example;
	} 
	
	
	@Override
	public int countByExample(GateFeeInfoForm form) {
		TblFeeInfoDOExample example = getSearchFiled(form);
		int i = tblFeeInfoDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblFeeInfoDO> selectByExample(GateFeeInfoForm form) {
		TblFeeInfoDOExample example = getSearchFiled(form);
		List<TblFeeInfoDO> list = tblFeeInfoDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(GateFeeInfoForm form) throws BposException {
		String gateid = form.getGateId();
		String merId = form.getPosMerId();
		String termId = form.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			TblFeeInfoDOExample example = new TblFeeInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblFeeInfoDOMapper.deleteByExample(example);
			return i;
		}
		logger.error("删除网关费率失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("删除网关费率失败，原因：网关，商户号，终端号为空");
	}

	@Override
	public int insert(TblFeeInfoDO record) throws BposException {
		gateRequired(record);
		return tblFeeInfoDOMapper.insertSelective(record);
	}

	@Override
	public int updateByExample(TblFeeInfoDO record) throws BposException {
		String gateid = record.getGateId();
		String merId = record.getPosMerId();
		String termId = record.getPosTermId();
		if(filedNotNull(gateid) && filedNotNull(merId) &&  filedNotNull(termId)){
			gateRequired(record);
			TblFeeInfoDOExample example = new TblFeeInfoDOExample();
			example.createCriteria().andGateIdEqualTo(gateid).andPosMerIdEqualTo(merId).andPosTermIdEqualTo(termId);
			int i = tblFeeInfoDOMapper.updateByExampleSelective(record, example);
			return i;
		}
		logger.error("更新网关费率失败，原因：网关【{}】，商户号【{}】，终端号【{}】为空",gateid,merId,termId);
		throw new BposException("更新网关费率失败，原因：网关，商户号，终端号为空");
	}
	private void gateRequired(TblFeeInfoDO record) throws BposException {
		String gateId = record.getGateId();
		TblBtsGateRoutDO gateRout = tblBtsGateRoutDOMapper.selectByPrimaryKey(gateId);
		if(gateRout == null){
			logger.error("插入网关【{}】费率失败，原因：网关不存在",gateId);
			throw new BposException("插入网关费率失败，原因：网关不存在");
		}
	}
}
