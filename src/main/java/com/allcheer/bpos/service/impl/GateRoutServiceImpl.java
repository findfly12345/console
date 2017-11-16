package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.entity.TblBtsGateRoutDOExample;
import com.allcheer.bpos.entity.TblBtsGateRoutDOExample.Criteria;
import com.allcheer.bpos.form.GateRoutForm;
import com.allcheer.bpos.mapper.TblBtsGateRoutDOMapper;
import com.allcheer.bpos.service.GateRoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateRoutServiceImpl implements GateRoutService {
	
	@Autowired
	TblBtsGateRoutDOMapper tblBtsGateRoutDOMapper;
	
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblBtsGateRoutDOExample getSearchFiled(GateRoutForm form){
		String gateid = form.getGateId();
		String ip = form.getLineIp();
		String name = form.getLineName();
		String port = form.getLinePort();

		TblBtsGateRoutDOExample example = new TblBtsGateRoutDOExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(gateid)) {
			criteria.andGateIdEqualTo(gateid);
		}
		if (filedNotNull(ip)) {
			criteria.andLineIpEqualTo(ip);
		}
		if(filedNotNull(name)){
			criteria.andLineNameEqualTo(name);
		}
		return example;
	} 

	@Override
	public int countByExample(GateRoutForm form) {
		TblBtsGateRoutDOExample example = getSearchFiled(form);
		int count = tblBtsGateRoutDOMapper.countByExample(example);
		return count;
	}

	@Override
	public int deleteByExample(GateRoutForm form) {
		TblBtsGateRoutDOExample example = getSearchFiled(form);
		int count = tblBtsGateRoutDOMapper.deleteByExample(example);
		return count;
	}

	@Override
	public int insert(TblBtsGateRoutDO record) {
		int insert = tblBtsGateRoutDOMapper.insert(record);
		return insert;
	}

	@Override
	public List<TblBtsGateRoutDO> selectByExample(GateRoutForm form) {
		TblBtsGateRoutDOExample example = getSearchFiled(form);
		List<TblBtsGateRoutDO> list = tblBtsGateRoutDOMapper.selectByExample(example);
		return list;
	}

	@Override
	public int updateByExample(TblBtsGateRoutDO record) {
		GateRoutForm form = new GateRoutForm();
		form.setGateId(record.getGateId());
		TblBtsGateRoutDOExample example = getSearchFiled(form);
		int count = tblBtsGateRoutDOMapper.updateByExample(record, example);
		return count;
	}

	
	@Override
	public TblBtsGateRoutDO selectByGateId(String id) {
		GateRoutForm from = new GateRoutForm();
		from.setGateId(id);
		TblBtsGateRoutDOExample example = getSearchFiled(from);
		List<TblBtsGateRoutDO> list = tblBtsGateRoutDOMapper.selectByExample(example);
		return list.get(0);
	}

}
