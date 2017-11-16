package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.InstAccountingSummaryDO;
import com.allcheer.bpos.entity.InstAccountingSummaryDOExample;
import com.allcheer.bpos.entity.InstAccountingSummaryDOExample.Criteria;
import com.allcheer.bpos.entity.InstAccountingSummaryDOKey;
import com.allcheer.bpos.form.InstAccountSumForm;
import com.allcheer.bpos.mapper.InstAccountingSummaryDOMapper;
import com.allcheer.bpos.service.InstAccountSumService;
import com.allcheer.bpos.util.task.InstSumTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstAccountSumServiceImpl implements InstAccountSumService{

	@Autowired
	InstAccountingSummaryDOMapper instAccountingSummaryDOMapper;
	@Autowired
	InstSumTask instSumTask;
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private InstAccountingSummaryDOExample getSearchFiled(InstAccountSumForm form){
		String instId = form.getInstId();
		String transDate = form.getAccountingDate();

		InstAccountingSummaryDOExample example = new InstAccountingSummaryDOExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(instId)) {
			criteria.andInstIdEqualTo(instId);
		}
		if (filedNotNull(transDate)) {
			criteria.andAccountingDateEqualTo(transDate);
		}
		return example;
	} 
	
	@Override
	public int countByExample(InstAccountSumForm form) {
		InstAccountingSummaryDOExample example = getSearchFiled(form);
		int i = instAccountingSummaryDOMapper.countByExample(example);
		return i;
	}

	@Override
	public List<InstAccountingSummaryDO> selectByExample(InstAccountSumForm form) {
		InstAccountingSummaryDOExample example = getSearchFiled(form);
		List<InstAccountingSummaryDO> list = instAccountingSummaryDOMapper.selectByExample(example);
		return list;
	}

	@Override
	@Transactional
	public int deleteByExample(InstAccountSumForm form) {
		String accountingDate = form.getAccountingDate();
		String instId = form.getInstId();
		
		InstAccountingSummaryDOKey key = new InstAccountingSummaryDOKey();
		key.setInstId(instId);
		key.setAccountingDate(accountingDate);
		int i = instAccountingSummaryDOMapper.deleteByPrimaryKey(key);
		
		instSumTask.sumTask(accountingDate,instId);
		return i;
	}

	@Override
	public int insert(InstAccountingSummaryDO record) {
		int i = instAccountingSummaryDOMapper.insertSelective(record);
		return i;
	}

	@Override
	public int updateByExample(InstAccountingSummaryDO record) {
		InstAccountingSummaryDOExample example = new InstAccountingSummaryDOExample();
		example.createCriteria().andInstIdEqualTo(record.getInstId()).andAccountingDateEqualTo(record.getAccountingDate());
		int i = instAccountingSummaryDOMapper.updateByExampleSelective(record, example);
		return i;
	}
}
