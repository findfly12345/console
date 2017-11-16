package com.allcheer.bpos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstFeeInfoDO;
import com.allcheer.bpos.entity.TblBtsInstFeeInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDOExample.Criteria;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample;
import com.allcheer.bpos.form.InstMccFeeInfoForm;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMccFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMerInfoDOMapper;
import com.allcheer.bpos.service.InstMccFeeInfoService;
import com.allcheer.bpos.util.BposException;

@Service
public class InstMccFeeInfoServiceImpl implements InstMccFeeInfoService {
	private Logger logger = LoggerFactory.getLogger(InstMccFeeInfoServiceImpl.class);
	
	@Autowired
	TblBtsInstMccFeeInfoDOMapper tblBtsInstMccFeeInfoDOMapper;
	@Autowired
	TblBtsInstFeeInfoDOMapper tblBtsInstFeeInfoDOMapper;
	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;
	@Autowired
	TblBtsInstMerInfoDOMapper tblBtsInstMerInfoDOMapper;
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsInstMccFeeInfoDOExample getInstMccFeeSearchFiled(InstMccFeeInfoForm form){
		String code = form.getInstId();
		String mode = form.getCalcMode();
		String mcc = form.getMccType();

		TblBtsInstMccFeeInfoDOExample example = new TblBtsInstMccFeeInfoDOExample();
		Criteria criteria = example.createCriteria();
		
		if(filedNotNull(code)){
			criteria.andInstIdEqualTo(code);
		}
		if(filedNotNull(mode)){
			criteria.andCalcModeEqualTo(mode);
		}
		if(filedNotNull(mcc)){
			criteria.andMccTypeEqualTo(mcc);
		}
		return example;
	} 
	
	@Override
	public int countByExample(InstMccFeeInfoForm form) {
		TblBtsInstMccFeeInfoDOExample example = getInstMccFeeSearchFiled(form);
		int i = tblBtsInstMccFeeInfoDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsInstMccFeeInfoDO> selectByExample(InstMccFeeInfoForm form) {
		TblBtsInstMccFeeInfoDOExample example = getInstMccFeeSearchFiled(form);
		List<TblBtsInstMccFeeInfoDO> list = tblBtsInstMccFeeInfoDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(InstMccFeeInfoForm form) {
		//TODO 不实现
		return 0;
	}

	@Override
	@Transactional
	public int insert(TblBtsInstMccFeeInfoDO record) throws BposException {
		String instId = record.getInstId();
		String mccType = record.getMccType();
		String calcMode = record.getCalcMode();
		logger.debug("机构【{}】,mcc【{}】插入费率开始",instId,mccType);
		TblBtsInstDO inst = tblBtsInstDOMapper.selectByPrimaryKey(instId);
		if(inst == null){
			logger.error("机构【{}】,mcc【{}】插入费率失败，机构不存在",instId,mccType);
			throw new BposException("机构号不存在");
		}
		
		int instMccAddSize = tblBtsInstMccFeeInfoDOMapper.insertSelective(record);
		
		TblBtsInstMerInfoDOExample example = new TblBtsInstMerInfoDOExample();
		example.createCriteria().andInstIdEqualTo(instId).andInstMerTypeEqualTo(mccType);
		List<TblBtsInstMerInfoDO> instMerInfoList = tblBtsInstMerInfoDOMapper.selectByExample(example);
		logger.debug("机构【{}】,mcc【{}】插入费率,找到{}条机构",instId,mccType,instMerInfoList.size());
		
		int upInstFeeSize = 0;
		TblBtsInstFeeInfoDO instFeeInfo = new TblBtsInstFeeInfoDO();
		instFeeInfo.setCalcMode(calcMode);
		TblBtsInstFeeInfoDOExample example1 = new TblBtsInstFeeInfoDOExample();
		for(TblBtsInstMerInfoDO instMerInfo:instMerInfoList){
			String instMerId = instMerInfo.getInstMerId();
			String instTermId = instMerInfo.getInstTermId();
			example1.createCriteria().andInstIdEqualTo(instId).andInstMerIdEqualTo(instMerId).andInstTermIdEqualTo(instTermId);
			int upInstFeeInfoSize = tblBtsInstFeeInfoDOMapper.updateByExampleSelective(instFeeInfo, example1);
			if(upInstFeeInfoSize >0){
				upInstFeeSize = upInstFeeSize + upInstFeeInfoSize;
			}
			example1.clear();
		}
		logger.debug("机构【{}】,mcc【{}】插入费率,更新商户费率记录【{}】条",instId,mccType,upInstFeeSize);
		return instMccAddSize;
	}

	@Override
	public int updateByExample(TblBtsInstMccFeeInfoDO record) throws BposException {
		String instId = record.getInstId();
		String mccType = record.getMccType();
		String calcMode = record.getCalcMode();
		logger.debug("机构【{}】,mcc【{}】更新费率开始",instId,mccType);
		TblBtsInstDO inst = tblBtsInstDOMapper.selectByPrimaryKey(instId);
		if(inst == null){
			logger.error("机构【{}】,mcc【{}】更新费率失败，机构不存在",instId,mccType);
			throw new BposException("机构号不存在");
		}
		TblBtsInstMccFeeInfoDOExample example3 = new TblBtsInstMccFeeInfoDOExample();
		example3.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo(mccType);
		int instMccAddSize = tblBtsInstMccFeeInfoDOMapper.updateByExampleSelective(record, example3);
		
		TblBtsInstMerInfoDOExample example = new TblBtsInstMerInfoDOExample();
		example.createCriteria().andInstIdEqualTo(instId).andInstMerTypeEqualTo(mccType);
		List<TblBtsInstMerInfoDO> instMerInfoList = tblBtsInstMerInfoDOMapper.selectByExample(example);
		logger.debug("机构【{}】,mcc【{}】更新费率,找到{}条机构",instId,mccType,instMerInfoList.size());
		
		int upInstFeeSize = 0;
		TblBtsInstFeeInfoDO instFeeInfo = new TblBtsInstFeeInfoDO();
		instFeeInfo.setCalcMode(calcMode);
		TblBtsInstFeeInfoDOExample example1 = new TblBtsInstFeeInfoDOExample();
		for(TblBtsInstMerInfoDO instMerInfo:instMerInfoList){
			String instMerId = instMerInfo.getInstMerId();
			String instTermId = instMerInfo.getInstTermId();
			example1.createCriteria().andInstIdEqualTo(instId).andInstMerIdEqualTo(instMerId).andInstTermIdEqualTo(instTermId);
			int upInstFeeInfoSize = tblBtsInstFeeInfoDOMapper.updateByExampleSelective(instFeeInfo, example1);
			if(upInstFeeInfoSize >0){
				upInstFeeSize = upInstFeeSize + upInstFeeInfoSize;
			}
			example1.clear();
		}
		logger.debug("机构【{}】,mcc【{}】更新费率{}条,更新商户费率记录【{}】条",instId,mccType,instMccAddSize,upInstFeeSize);
		return instMccAddSize;
	}

}
