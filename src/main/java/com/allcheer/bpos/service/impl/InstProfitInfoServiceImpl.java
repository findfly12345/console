package com.allcheer.bpos.service.impl;

import java.util.List;

import com.allcheer.bpos.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allcheer.bpos.entity.TblInstProfitInfo;
import com.allcheer.bpos.entity.TblInstProfitInfoExample;
import com.allcheer.bpos.entity.TblInstProfitInfoExample.Criteria;
import com.allcheer.bpos.form.InstProfitInfoForm;
import com.allcheer.bpos.mapper.TblInstProfitInfoMapper;
import com.allcheer.bpos.service.InstProfitInfoService;

@Service
public class InstProfitInfoServiceImpl implements InstProfitInfoService {
	@Autowired
	TblInstProfitInfoMapper tblInstProfitInfoMapper;

	private boolean filedNotNull(String filed) {
		return filed != null && (!"".equals(filed.trim()));
	}

	/**
	 * @see com.allcheer.bpos.service.WCTransLogService#getTransList(com.allcheer.bpos.form.WCTransLogForm)
	 */
	@Override
	public List<TblInstProfitInfo> getInstProfitList(InstProfitInfoForm form) {
		TblInstProfitInfoExample example = getSearchFiled(form);
		List<TblInstProfitInfo> selectByExample = tblInstProfitInfoMapper.selectByExample(example);
		return selectByExample;
	}

	/**
	 * @see com.allcheer.bpos.service.WCTransLogService#getWCIncomeListCount(com.allcheer.bpos.form.WCTransLogForm)
	 */
	@Override
	public int getInstProfitListCount(InstProfitInfoForm form) {
		TblInstProfitInfoExample example = getSearchFiled(form);
		int countByExample = tblInstProfitInfoMapper.countByExample(example);
		return countByExample;
	}

	private TblInstProfitInfoExample getSearchFiled(InstProfitInfoForm form) {

		String startTransDateTime = form.getStartTransDateTime();
		String endTransDateTime = form.getEndTransDateTime();
		String instId = form.getInstId();

		TblInstProfitInfoExample example = new TblInstProfitInfoExample();
		Criteria criteria = example.createCriteria();
		if (filedNotNull(instId)) {
			criteria.andInstIdEqualTo(instId);
		}

		if (filedNotNull(startTransDateTime)) {
			criteria.andTransDateGreaterThanOrEqualTo(DateUtil.removeLineDateString(form.getStartTransDateTime()));
		}
		if (filedNotNull(endTransDateTime)) {
			criteria.andTransDateLessThanOrEqualTo(DateUtil.removeLineDateString(form.getEndTransDateTime()));
		}

		return example;
	}


	@Override
	public int insert(TblInstProfitInfo tblInstProfitInfo) {

		int i = tblInstProfitInfoMapper.insert(tblInstProfitInfo);

		return i;
	}

	@Override
	public int deleteTblInstProfitInfo(String date) {
		TblInstProfitInfoExample tblInstProfitInfoExample = new TblInstProfitInfoExample();
		tblInstProfitInfoExample.createCriteria().andTransDateEqualTo(date);
		return tblInstProfitInfoMapper.deleteByExample(tblInstProfitInfoExample);
	}

}
