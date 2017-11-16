package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.util.Pagination;

import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/10/22.
 */
public interface MerChannelPreService {
	Pagination<TblMerInfoVO> queryMerInfoList(Map map);

	List<TblMerInfoVO> merInfoList(Map map);

	public Boolean SHBankRegisted(String merId);

	public Boolean EGBankRegisted(String merId);
}
