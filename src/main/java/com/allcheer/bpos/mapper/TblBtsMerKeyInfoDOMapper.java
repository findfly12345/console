package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsMerKeyInfoDO;
import com.allcheer.bpos.entity.TblBtsMerKeyInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsMerKeyInfoDOMapper {
    int countByExample(TblBtsMerKeyInfoDOExample example);

    int deleteByExample(TblBtsMerKeyInfoDOExample example);

    int insert(TblBtsMerKeyInfoDO record);

    int insertSelective(TblBtsMerKeyInfoDO record);

    List<TblBtsMerKeyInfoDO> selectByExample(TblBtsMerKeyInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsMerKeyInfoDO record, @Param("example") TblBtsMerKeyInfoDOExample example);

    int updateByExample(@Param("record") TblBtsMerKeyInfoDO record, @Param("example") TblBtsMerKeyInfoDOExample example);
}