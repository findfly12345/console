package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDO;
import com.allcheer.bpos.form.GateMerInfoForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface GateMerInfoService {

	int countByExample(GateMerInfoForm form);

	List<TblBtsGateMerInfoDO> selectByExample(GateMerInfoForm form);

	int deleteByExample(GateMerInfoForm form)  throws BposException ;

	int insert(TblBtsGateMerInfoDO record)  throws BposException;

	int updateByExample(TblBtsGateMerInfoDO record) throws BposException ;

}
