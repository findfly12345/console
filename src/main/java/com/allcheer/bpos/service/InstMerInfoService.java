package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsInstMerInfoDO;
import com.allcheer.bpos.form.InstMerInfoForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface InstMerInfoService {

	int countByExample(InstMerInfoForm form);

	List<TblBtsInstMerInfoDO> selectByExample(InstMerInfoForm form);

	int deleteByExample(InstMerInfoForm form);

	int insert(TblBtsInstMerInfoDO record) throws BposException;

	int updateByExample(TblBtsInstMerInfoDO form);
}
