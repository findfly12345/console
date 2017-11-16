package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblFeeInfoDO;
import com.allcheer.bpos.form.GateFeeInfoForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface GateFeeInfoService {

	int countByExample(GateFeeInfoForm form);

	List<TblFeeInfoDO> selectByExample(GateFeeInfoForm example);

	int deleteByExample(GateFeeInfoForm form)  throws BposException;

	int insert(TblFeeInfoDO record)  throws BposException;

	int updateByExample(TblFeeInfoDO record)  throws BposException;

}
