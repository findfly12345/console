package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblFeeInfoDO;
import com.allcheer.bpos.entity.TblFeeInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblFeeInfoDOMapper {
    int countByExample(TblFeeInfoDOExample example);

    int deleteByExample(TblFeeInfoDOExample example);

    int insert(TblFeeInfoDO record);

    int insertSelective(TblFeeInfoDO record);

    List<TblFeeInfoDO> selectByExample(TblFeeInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblFeeInfoDO record, @Param("example") TblFeeInfoDOExample example);

    int updateByExample(@Param("record") TblFeeInfoDO record, @Param("example") TblFeeInfoDOExample example);
}