package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsGateRoutDO;
import com.allcheer.bpos.entity.TblBtsGateRoutDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsGateRoutDOMapper {
    int countByExample(TblBtsGateRoutDOExample example);

    int deleteByExample(TblBtsGateRoutDOExample example);

    int deleteByPrimaryKey(String gateId);

    int insert(TblBtsGateRoutDO record);

    int insertSelective(TblBtsGateRoutDO record);

    List<TblBtsGateRoutDO> selectByExample(TblBtsGateRoutDOExample example);

    TblBtsGateRoutDO selectByPrimaryKey(String gateId);

    int updateByExampleSelective(@Param("record") TblBtsGateRoutDO record, @Param("example") TblBtsGateRoutDOExample example);

    int updateByExample(@Param("record") TblBtsGateRoutDO record, @Param("example") TblBtsGateRoutDOExample example);

    int updateByPrimaryKeySelective(TblBtsGateRoutDO record);

    int updateByPrimaryKey(TblBtsGateRoutDO record);
}