package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblGateRoute;
import com.allcheer.bpos.entity.TblGateRouteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblGateRouteMapper {
    int countByExample(TblGateRouteExample example);

    int deleteByExample(TblGateRouteExample example);

    int deleteByPrimaryKey(String gateId);

    int insert(TblGateRoute record);

    int insertSelective(TblGateRoute record);

    List<TblGateRoute> selectByExample(TblGateRouteExample example);

    TblGateRoute selectByPrimaryKey(String gateId);

    int updateByExampleSelective(@Param("record") TblGateRoute record, @Param("example") TblGateRouteExample example);

    int updateByExample(@Param("record") TblGateRoute record, @Param("example") TblGateRouteExample example);

    int updateByPrimaryKeySelective(TblGateRoute record);

    int updateByPrimaryKey(TblGateRoute record);
}