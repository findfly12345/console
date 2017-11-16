package com.allcheer.bpos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstKeyDO;
import com.allcheer.bpos.entity.TblBtsInstKeyDOExample;
import com.allcheer.bpos.entity.TblBtsMerKeyInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstKeyDOExample.Criteria;
import com.allcheer.bpos.entity.TblBtsMerKeyInfoDO;
import com.allcheer.bpos.form.InstKeyForm;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstKeyDOMapper;
import com.allcheer.bpos.mapper.TblBtsMerKeyInfoDOMapper;
import com.allcheer.bpos.service.InstKeyService;
import com.allcheer.bpos.util.BposException;

@Service
public class InstKeyServiceImpl implements InstKeyService {

	private Logger logger = LoggerFactory.getLogger(InstKeyServiceImpl.class);
	
	@Autowired
	TblBtsInstKeyDOMapper tblBtsInstKeyDOMapper;
	@Autowired
	TblBtsMerKeyInfoDOMapper tblBtsMerKeyInfoDOMapper;
	@Autowired
	TblBtsInstDOMapper tblBtsInstDOMapper;
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsInstKeyDOExample getInstkeySearchFiled(InstKeyForm form){
		String code = form.getInstId();
		String key = form.getInstPrimaryKey();
		String stat = form.getInstSignStatus();

		TblBtsInstKeyDOExample example = new TblBtsInstKeyDOExample();
		Criteria criteria = example.createCriteria();
		
		if(filedNotNull(code)){
			criteria.andInstIdEqualTo(code);
		}
		if(filedNotNull(key)){
			criteria.andInstPinKeyEqualTo(key);
		}
		if(filedNotNull(stat)){
			criteria.andInstSignStatusEqualTo(stat);
		}
		return example;
	} 
	
	
	@Override
	public int countByExample(InstKeyForm form) {
		TblBtsInstKeyDOExample example = getInstkeySearchFiled(form);
		int i = tblBtsInstKeyDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsInstKeyDO> selectByExample(InstKeyForm form) {
		TblBtsInstKeyDOExample example = getInstkeySearchFiled(form);
		List<TblBtsInstKeyDO> list = tblBtsInstKeyDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public TblBtsInstKeyDO selectByPrimaryKey(String instId) {
		return tblBtsInstKeyDOMapper.selectByPrimaryKey(instId);
	}

	@Override
	public int deleteByPrimaryKey(String instId) {
		return tblBtsInstKeyDOMapper.deleteByPrimaryKey(instId);
	}

	@Override
	public int insert(TblBtsInstKeyDO record) throws BposException {
		String code = record.getInstId();
		logger.debug("机构【{}】插入密钥【{}】",code,record.getInstPrimaryKey());
		TblBtsInstDO inst = tblBtsInstDOMapper.selectByPrimaryKey(code);
		if(inst == null){
			logger.error("机构【{}】密钥插入失败，机构不存在",code);
			throw new BposException("机构号不存在");
		}
		
		TblBtsMerKeyInfoDOExample example = new TblBtsMerKeyInfoDOExample();
		example.createCriteria().andInstIdEqualTo(code);
		TblBtsMerKeyInfoDO merkey = new TblBtsMerKeyInfoDO();
		merkey.setPrimaryKey(record.getInstPrimaryKey());
		int i = tblBtsMerKeyInfoDOMapper.updateByExampleSelective(merkey, example);
		logger.info("机构【{}】更新商户密钥{}条",code,i);
		
		int j = tblBtsInstKeyDOMapper.insertSelective(record);
		logger.debug("机构【{}】插入密钥成功",code);
		return j;
	}

	@Override
	public int updateByPrimaryKey(TblBtsInstKeyDO record) {
		logger.debug("机构【{}】更新密钥【{}】",record.getInstId(),record.getInstPrimaryKey());
		TblBtsMerKeyInfoDOExample example = new TblBtsMerKeyInfoDOExample();
		example.createCriteria().andInstIdEqualTo(record.getInstId());
		TblBtsMerKeyInfoDO merkey = new TblBtsMerKeyInfoDO();
		merkey.setPrimaryKey(record.getInstPrimaryKey());
		int i = tblBtsMerKeyInfoDOMapper.updateByExampleSelective(merkey, example);
		logger.info("机构【{}】更新商户密钥{}条",record.getInstId(),i);
		
		int j = tblBtsInstKeyDOMapper.updateByPrimaryKeySelective(record);
		logger.debug("机构【{}】更新密钥成功",record.getInstId());
		return j;
	}
}
