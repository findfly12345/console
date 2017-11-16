package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.InstAccountingSummaryDO;
import com.allcheer.bpos.form.InstAccountSumForm;

import java.util.List;

public interface InstAccountSumService {

	int countByExample(InstAccountSumForm form);

	List<InstAccountingSummaryDO> selectByExample(InstAccountSumForm form);
	
	int deleteByExample(InstAccountSumForm form);

	int insert(InstAccountingSummaryDO record);

	int updateByExample(InstAccountingSummaryDO record);

}
