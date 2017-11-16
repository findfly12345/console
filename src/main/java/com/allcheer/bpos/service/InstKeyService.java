package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsInstKeyDO;
import com.allcheer.bpos.form.InstKeyForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface InstKeyService {

	int countByExample(InstKeyForm example);

	List<TblBtsInstKeyDO> selectByExample(InstKeyForm example);

	TblBtsInstKeyDO selectByPrimaryKey(String instId);

	int deleteByPrimaryKey(String instId);

	int insert(TblBtsInstKeyDO record)  throws BposException;

	int updateByPrimaryKey(TblBtsInstKeyDO record);
}
