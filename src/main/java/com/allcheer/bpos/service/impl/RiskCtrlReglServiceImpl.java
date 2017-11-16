package com.allcheer.bpos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblRiskCtrlRegl;
import com.allcheer.bpos.entity.TblRiskCtrlReglExample;
import com.allcheer.bpos.entity.TblRiskCtrlReglExample.Criteria;
import com.allcheer.bpos.form.RiskCtrlReglListForm;
import com.allcheer.bpos.mapper.TblRiskCtrlReglMapper;
import com.allcheer.bpos.service.RiskCtrlReglService;
import com.allcheer.bpos.util.BposException;

@Service
public class RiskCtrlReglServiceImpl implements RiskCtrlReglService{
	private Logger logger = LoggerFactory.getLogger(RiskCtrlReglServiceImpl.class);
	
	@Autowired
	TblRiskCtrlReglMapper tblRiskCtrlReglMapper;	
	
	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}
	
	private TblRiskCtrlReglExample getSearchFiled(RiskCtrlReglListForm form){
		String riskNum = form.getRiskNum();
		String paramNum = form.getParamNum();
		String paramVal = form.getParamVal();
		String riskFlag = form.getRiskFlag();

		TblRiskCtrlReglExample example = new TblRiskCtrlReglExample();
		Criteria criteria = example.createCriteria();
		
		if (filedNotNull(riskNum)) {
			criteria.andRiskNumEqualTo(riskNum);
		}
		if (filedNotNull(paramNum)) {
			criteria.andParamNumEqualTo(paramNum);
		}
		if(filedNotNull(paramVal)){
			criteria.andParamValEqualTo(paramVal);
		}
		if(filedNotNull(riskFlag)){
			criteria.andRiskFlagEqualTo(riskFlag);
		}
		
		return example;
	} 
	
	
	@Override
	public int countByExample(RiskCtrlReglListForm form) {
		TblRiskCtrlReglExample example = getSearchFiled(form);
		int i = tblRiskCtrlReglMapper.countByExample(example);
		return i;
	}

	@Override
	public List<TblRiskCtrlRegl> selectByExample(RiskCtrlReglListForm form) {
		TblRiskCtrlReglExample example = getSearchFiled(form);
		List<TblRiskCtrlRegl> list = tblRiskCtrlReglMapper.selectByExample(example);
		return list;
	}

	@Override
	public int deleteByExample(RiskCtrlReglListForm form) throws BposException {
		String riskNum = form.getRiskNum();
		String paramNum = form.getParamNum();
		
		
		if(filedNotNull(riskNum) && filedNotNull(paramNum)){
			TblRiskCtrlReglExample example = new TblRiskCtrlReglExample();
			example.createCriteria().andRiskNumEqualTo(riskNum).andParamNumEqualTo(paramNum);
			int i = tblRiskCtrlReglMapper.deleteByExample(example);
			return i;
		}
		throw new BposException("删除风控规则失败，原因：风控编码、参数编码为空");
	}

	@Override
	public int insert(TblRiskCtrlRegl record) throws BposException {
		return tblRiskCtrlReglMapper.insert(record);
	}

	@Override
	public int updateByExample(TblRiskCtrlRegl record) throws BposException {
		String riskNum = record.getRiskNum();
		String paramNum = record.getParamNum();
		
		
		if(filedNotNull(riskNum) && filedNotNull(paramNum)){
			gateRequired(record);
			TblRiskCtrlReglExample example = new TblRiskCtrlReglExample();
			example.createCriteria().andRiskNumEqualTo(riskNum).andParamNumEqualTo(paramNum);
			int i = tblRiskCtrlReglMapper.updateByExampleSelective(record, example);
			return i;
		}
		throw new BposException("更新风控规则失败，原因：风控编码、参数编码为空");
	}
	private void gateRequired(TblRiskCtrlRegl record) throws BposException {
		String riskNum = record.getRiskNum();
		String paramNum = record.getParamNum();
		
		TblRiskCtrlReglExample tblRiskCtrlReglExample = new TblRiskCtrlReglExample();
		tblRiskCtrlReglExample.createCriteria().andRiskNumEqualTo(riskNum).andParamNumEqualTo(paramNum);
		
		List<TblRiskCtrlRegl> gateRoutList = tblRiskCtrlReglMapper.selectByExample(tblRiskCtrlReglExample);
		if(gateRoutList.size() == 0){
			throw new BposException("插入风控规则失败，原因：风控编码、参数编码不存在");
		}
	}
}
