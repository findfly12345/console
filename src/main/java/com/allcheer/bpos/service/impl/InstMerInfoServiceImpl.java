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
import com.allcheer.bpos.entity.TblBtsInstKeyDO;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample.Criteria;
import com.allcheer.bpos.entity.TblBtsMerKeyInfoDO;
import com.allcheer.bpos.entity.TblBtsMerKeyInfoDOExample;
import com.allcheer.bpos.form.InstMerInfoForm;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstKeyDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMccFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsMerKeyInfoDOMapper;
import com.allcheer.bpos.service.InstMerInfoService;
import com.allcheer.bpos.util.BposException;

@Service
public class InstMerInfoServiceImpl implements InstMerInfoService {

	private Logger logger = LoggerFactory.getLogger(InstMerInfoServiceImpl.class);

	@Autowired
	TblBtsInstMerInfoDOMapper tblBtsInstMerInfoDOMapper;
	@Autowired
	TblBtsMerKeyInfoDOMapper tblBtsMerKeyInfoDOMapper;
	@Autowired
	TblBtsInstFeeInfoDOMapper tblBtsInstFeeInfoDOMapper;
	@Autowired
	TblBtsInstKeyDOMapper tblBtsInstKeyDOMapper;
	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;
	@Autowired
	TblBtsInstMccFeeInfoDOMapper tblBtsInstMccFeeInfoDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	private TblBtsInstMerInfoDOExample getInstkeySearchFiled(InstMerInfoForm form) {
		String code = form.getInstId();
		String merid = form.getInstMerId();
		String termid = form.getInstTermId();
		String type = form.getInstMerType();

		TblBtsInstMerInfoDOExample example = new TblBtsInstMerInfoDOExample();
		Criteria criteria = example.createCriteria();

		if (filedNotNull(code)) {
			criteria.andInstIdEqualTo(code);
		}
		if (filedNotNull(merid)) {
			criteria.andInstMerIdEqualTo(merid);
		}
		if (filedNotNull(termid)) {
			criteria.andInstTermIdEqualTo(termid);
		}
		if (filedNotNull(type)) {
			criteria.andInstMerTypeEqualTo(type);
		}
		return example;
	}

	@Override
	public int countByExample(InstMerInfoForm form) {
		TblBtsInstMerInfoDOExample example = getInstkeySearchFiled(form);
		int i = tblBtsInstMerInfoDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsInstMerInfoDO> selectByExample(InstMerInfoForm form) {
		TblBtsInstMerInfoDOExample example = getInstkeySearchFiled(form);
		List<TblBtsInstMerInfoDO> selectByExample = tblBtsInstMerInfoDOMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	@Transactional
	public int deleteByExample(InstMerInfoForm form) {
		String code = form.getInstId();
		String merid = form.getInstMerId();
		String termid = form.getInstTermId();
		logger.debug("机构【{}】商户【{}】终端【{}】删除开始",code,merid,termid);
		TblBtsMerKeyInfoDOExample example = new TblBtsMerKeyInfoDOExample();
		example.createCriteria().andInstIdEqualTo(code).andInstMerIdEqualTo(merid).andInstMerTermIdEqualTo(termid);
		int MerKeyDelSize = tblBtsMerKeyInfoDOMapper.deleteByExample(example);
		
		
		TblBtsInstFeeInfoDOExample example2 = new TblBtsInstFeeInfoDOExample();
		example2.createCriteria().andInstIdEqualTo(code).andInstMerIdEqualTo(merid).andInstTermIdEqualTo(termid);
		int feeDelSize = tblBtsInstFeeInfoDOMapper.deleteByExample(example2);
		
		TblBtsInstMerInfoDOExample example3 = new TblBtsInstMerInfoDOExample();
		example3.createCriteria().andInstIdEqualTo(code).andInstMerIdEqualTo(merid).andInstTermIdEqualTo(termid);
		int instMertDelSize = tblBtsInstMerInfoDOMapper.deleteByExample(example3);
		logger.info("机构【{}】商户【{}】终端【{}】,密钥删除{}条,费率删除{}条,大商户删除{}条",code,merid,termid,MerKeyDelSize,feeDelSize,instMertDelSize);
		return instMertDelSize;
	}

	@Override
	@Transactional
	public int insert(TblBtsInstMerInfoDO record) throws BposException {
		String code = record.getInstId();
		String merid = record.getInstMerId();
		String termid = record.getInstTermId();
		String merType = record.getInstMerType();
		logger.debug("机构【{}】商户【{}】终端【{}】插入开始",code,merid,termid);
		TblBtsInstDO inst = tblBtsInstDOMapper.selectByPrimaryKey(code);
		if(inst == null){
			logger.error("机构【{}】商户【{}】终端【{}】插入失败，机构不存在",code,merid,termid);
			throw new BposException("机构号不存在");
		}
		
		TblBtsInstKeyDO instKey = tblBtsInstKeyDOMapper.selectByPrimaryKey(code);
		TblBtsMerKeyInfoDO merkey = new TblBtsMerKeyInfoDO();
		merkey.setInstId(code);
		merkey.setInstMerId(merid);
		merkey.setInstMerTermId(termid);
		if(instKey !=null){
			merkey.setPinKey(instKey.getInstPinKey());
			merkey.setTdKey(instKey.getInstTdKey());
			merkey.setMacKey(instKey.getInstMacKey());
			merkey.setPrimaryKey(instKey.getInstPrimaryKey());
		}
		int merKeyInfoAddSize = tblBtsMerKeyInfoDOMapper.insertSelective(merkey);
		
		TblBtsInstMccFeeInfoDOExample instMccFeeExample = new TblBtsInstMccFeeInfoDOExample();
		instMccFeeExample.createCriteria().andInstIdEqualTo(code).andMccTypeEqualTo(merType);
		List<TblBtsInstMccFeeInfoDO> instMccFee = tblBtsInstMccFeeInfoDOMapper.selectByExample(instMccFeeExample);
		String calcMode = null;
		if(instMccFee !=null && instMccFee.size()>0){
			if(instMccFee.size() > 1){
				logger.error("机构【{}】商户【{}】终端【{}】插入失败，同机构有多条不同商户类型的费率",code,merid,termid);
				throw new BposException("同机构有多条不同商户类型的费率"); 
			}
			calcMode = instMccFee.get(0).getCalcMode();
			logger.debug("机构【{}】商户【{}】终端【{}】插入，费率为【{}】",code,merid,termid,calcMode);
		}
		
		TblBtsInstFeeInfoDO feeDo = new TblBtsInstFeeInfoDO();
		feeDo.setInstId(code);
		feeDo.setInstMerId(merid);
		feeDo.setInstTermId(termid);
		feeDo.setCalcMode(calcMode);
		int feeAddSize = tblBtsInstFeeInfoDOMapper.insertSelective(feeDo);
		
		int instMerInfoSize = tblBtsInstMerInfoDOMapper.insertSelective(record);
		logger.info("机构【{}】商户【{}】终端【{}】,密钥插入{}条,费率插入{}条,大商户插入{}条",code,merid,termid,merKeyInfoAddSize,feeAddSize,instMerInfoSize);
		return instMerInfoSize;
	}

	@Override
	public int updateByExample(TblBtsInstMerInfoDO record) {
		String code = record.getInstId();
		String merid = record.getInstMerId();
		String termid = record.getInstTermId();
		
		TblBtsInstMerInfoDOExample example = new TblBtsInstMerInfoDOExample();
		Criteria criteria = example.createCriteria();
		criteria.andInstIdEqualTo(code).andInstMerIdEqualTo(merid).andInstTermIdEqualTo(termid);
		
		return tblBtsInstMerInfoDOMapper.updateByExampleSelective(record, example);
	}

}
