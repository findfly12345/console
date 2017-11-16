package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsInstFeeInfoDO;
import com.allcheer.bpos.entity.TblBtsInstFeeInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsInstFeeInfoDOMapper {
    int countByExample(TblBtsInstFeeInfoDOExample example);

    int deleteByExample(TblBtsInstFeeInfoDOExample example);

    int insert(TblBtsInstFeeInfoDO record);

    int insertSelective(TblBtsInstFeeInfoDO record);

    List<TblBtsInstFeeInfoDO> selectByExample(TblBtsInstFeeInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsInstFeeInfoDO record, @Param("example") TblBtsInstFeeInfoDOExample example);

    int updateByExample(@Param("record") TblBtsInstFeeInfoDO record, @Param("example") TblBtsInstFeeInfoDOExample example);
}