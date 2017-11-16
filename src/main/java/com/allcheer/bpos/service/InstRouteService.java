package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblBtsInstRouteDO;
import com.allcheer.bpos.form.InstRouteForm;
import com.allcheer.bpos.util.BposException;

import java.util.List;

public interface InstRouteService {

	int countByExample(InstRouteForm form);

	List<TblBtsInstRouteDO> selectByExample(InstRouteForm form);
	
	int deleteByPrimaryKey(Integer routeSeq);

	int insert(TblBtsInstRouteDO record) throws BposException;

	TblBtsInstRouteDO selectByPrimaryKey(Integer routeSeq);

	int updateByPrimaryKey(TblBtsInstRouteDO record) throws BposException;

	int updateStat(TblBtsInstRouteDO record);
}
