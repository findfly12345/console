package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblInstProfitInfo;
import com.allcheer.bpos.form.InstProfitInfoForm;

import java.util.List;

public interface InstProfitInfoService {

	List<TblInstProfitInfo> getInstProfitList(InstProfitInfoForm form);

	int getInstProfitListCount(InstProfitInfoForm form);

	int insert(TblInstProfitInfo tblInstProfitInfo);

	int deleteTblInstProfitInfo(String date);

}
