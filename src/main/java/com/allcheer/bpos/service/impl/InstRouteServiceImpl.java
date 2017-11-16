package com.allcheer.bpos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample;
import com.allcheer.bpos.entity.TblBtsInstRouteDO;
import com.allcheer.bpos.entity.TblBtsInstRouteDOExample;
import com.allcheer.bpos.entity.TblBtsInstRouteDOExample.Criteria;
import com.allcheer.bpos.form.InstRouteForm;
import com.allcheer.bpos.mapper.TblBtsGateMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstRouteDOMapper;
import com.allcheer.bpos.service.InstRouteService;
import com.allcheer.bpos.util.BposException;

@Service
public class InstRouteServiceImpl implements InstRouteService {
	
	private Logger logger = LoggerFactory.getLogger(InstRouteServiceImpl.class);

	@Autowired
	TblBtsInstRouteDOMapper tblBtsInstRouteDOMapper;
	@Autowired
	TblBtsInstMerInfoDOMapper tblBtsInstMerInfoDOMapper;
	@Autowired
	TblBtsGateMerInfoDOMapper tblBtsGateMerInfoDOMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsInstRouteDOExample getInstRouteSearchFiled(InstRouteForm form){
		String gateid = form.getGateId();
		String merId = form.getInstMerId();
		String merTermId = form.getInstMerTermId();
		String code = form.getInstCode();
		String bankMerId = form.getBankMerId();
		String bankTermId = form.getBankTermId();

		TblBtsInstRouteDOExample example = new TblBtsInstRouteDOExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(gateid)) {
			criteria.andGateIdEqualTo(gateid);
		}
		if (filedNotNull(merId)) {
			criteria.andInstMerIdEqualTo(merId);
		}
		if(filedNotNull(merTermId)){
			criteria.andInstMerTermIdEqualTo(merTermId);
		}
		if(filedNotNull(code)){
			criteria.andInstCodeEqualTo(code);
		}
		if(filedNotNull(bankMerId)){
			criteria.andBankMerIdEqualTo(bankMerId);
		}
		if(filedNotNull(bankTermId)){
			criteria.andBankTermIdEqualTo(bankTermId);
		}
		return example;
	} 
	
	private boolean beforeAddOrUp(TblBtsInstRouteDO record) throws BposException{
		String merId = record.getInstMerId();
		String merTermId = record.getInstMerTermId();
		String code = record.getInstCode();
		
		String gateid = record.getGateId();
		String bankMerId = record.getBankMerId();
		String bankTermId = record.getBankTermId();
		
		
		if(filedNotNull(gateid) && filedNotNull(bankMerId) && filedNotNull(bankTermId) && filedNotNull(code) && filedNotNull(merTermId) && filedNotNull(merId)){
			TblBtsInstMerInfoDOExample merEx = new TblBtsInstMerInfoDOExample();
			com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample.Criteria criteria = merEx.createCriteria();
			criteria.andInstIdEqualTo(code).andInstMerIdEqualTo(merId).andInstTermIdEqualTo(merTermId);
			int merKeyInfoSize = tblBtsInstMerInfoDOMapper.countByExample(merEx);
			
			
			TblBtsGateMerInfoDOExample posEx = new TblBtsGateMerInfoDOExample();
			com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample.Criteria criteria2 = posEx.createCriteria();
			criteria2.andGateIdEqualTo(gateid).andPosMerIdEqualTo(bankMerId).andPosTermIdEqualTo(bankTermId);
			int posInfoSize = tblBtsGateMerInfoDOMapper.countByExample(posEx);
			
			if(merKeyInfoSize > 0 &&  posInfoSize > 0){
				return true;
			}
			logger.error("机构数：{}，网关数：{}",merKeyInfoSize,posInfoSize);
			if(merKeyInfoSize == 0){
				throw new BposException("机构不存在");
			}
			if(posInfoSize == 0){
				throw new BposException("网关不存在");
			}
		}
		return false;
	}
	
	
	@Override
	public int countByExample(InstRouteForm form) {
		
		TblBtsInstRouteDOExample example = getInstRouteSearchFiled(form);
		int i = tblBtsInstRouteDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblBtsInstRouteDO> selectByExample(InstRouteForm form) {

		TblBtsInstRouteDOExample example = getInstRouteSearchFiled(form);
		List<TblBtsInstRouteDO> list = tblBtsInstRouteDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByPrimaryKey(Integer routeSeq) {
		
		return tblBtsInstRouteDOMapper.deleteByPrimaryKey(routeSeq);
	}

	@Override
	public int insert(TblBtsInstRouteDO record) throws BposException {
		int id = tblBtsInstRouteDOMapper.getId();
		record.setRouteSeq(id);
		boolean next = beforeAddOrUp(record);
		if(next){
			tblBtsInstRouteDOMapper.insert(record);
		}
		return 0;
	}

	@Override
	public TblBtsInstRouteDO selectByPrimaryKey(Integer routeSeq) {
		
		
		return tblBtsInstRouteDOMapper.selectByPrimaryKey(routeSeq);
	}

	@Override
	public int updateByPrimaryKey(TblBtsInstRouteDO record) throws BposException {
		boolean next = beforeAddOrUp(record);
		if(next){
			tblBtsInstRouteDOMapper.updateByPrimaryKey(record);
		}
		return 0;
	}
	
	@Override
	public int updateStat(TblBtsInstRouteDO record){
		return tblBtsInstRouteDOMapper.updateByPrimaryKeySelective(record);
	}

}
