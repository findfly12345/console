package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMccFeeInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsInstMccFeeInfoDOMapper {
    int countByExample(TblBtsInstMccFeeInfoDOExample example);

    int deleteByExample(TblBtsInstMccFeeInfoDOExample example);

    int deleteByPrimaryKey(String instId);

    int insert(TblBtsInstMccFeeInfoDO record);

    int insertSelective(TblBtsInstMccFeeInfoDO record);

    List<TblBtsInstMccFeeInfoDO> selectByExample(TblBtsInstMccFeeInfoDOExample example);

    TblBtsInstMccFeeInfoDO selectByPrimaryKey(String instId);

    int updateByExampleSelective(@Param("record") TblBtsInstMccFeeInfoDO record, @Param("example") TblBtsInstMccFeeInfoDOExample example);

    int updateByExample(@Param("record") TblBtsInstMccFeeInfoDO record, @Param("example") TblBtsInstMccFeeInfoDOExample example);
}