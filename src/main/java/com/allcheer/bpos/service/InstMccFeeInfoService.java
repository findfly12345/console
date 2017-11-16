package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDO;
import com.allcheer.bpos.form.InstMccFeeInfoForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface InstMccFeeInfoService {
	
	int countByExample(InstMccFeeInfoForm form);

	List<TblBtsInstMccFeeInfoDO> selectByExample(InstMccFeeInfoForm form);

    int deleteByExample(InstMccFeeInfoForm form);

    int insert(TblBtsInstMccFeeInfoDO record)  throws BposException;

    int updateByExample(TblBtsInstMccFeeInfoDO record)throws BposException;

}
