package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblOnlineRoute;
import com.allcheer.bpos.entity.TblOnlineRouteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblOnlineRouteMapper {
	int countByExample(TblOnlineRouteExample example);

	int deleteByExample(TblOnlineRouteExample example);

	int insert(TblOnlineRoute record);

	int insertSelective(TblOnlineRoute record);

	List<TblOnlineRoute> selectByExample(TblOnlineRouteExample example);

	int updateByExampleSelective(@Param("record") TblOnlineRoute record,
			@Param("example") TblOnlineRouteExample example);

	int updateByExample(@Param("record") TblOnlineRoute record, @Param("example") TblOnlineRouteExample example);

	int countByCust(@Param("instId") String instId, @Param("merId") String merId);

	public List<TblOnlineRoute> selectByCust(@Param("instId") String instId, @Param("merId") String merId);
}