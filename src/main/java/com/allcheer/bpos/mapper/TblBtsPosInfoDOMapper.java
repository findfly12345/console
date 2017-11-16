package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsPosInfoDO;
import com.allcheer.bpos.entity.TblBtsPosInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsPosInfoDOMapper {
    int countByExample(TblBtsPosInfoDOExample example);

    int deleteByExample(TblBtsPosInfoDOExample example);

    int insert(TblBtsPosInfoDO record);

    int insertSelective(TblBtsPosInfoDO record);

    List<TblBtsPosInfoDO> selectByExample(TblBtsPosInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsPosInfoDO record, @Param("example") TblBtsPosInfoDOExample example);

    int updateByExample(@Param("record") TblBtsPosInfoDO record, @Param("example") TblBtsPosInfoDOExample example);
}