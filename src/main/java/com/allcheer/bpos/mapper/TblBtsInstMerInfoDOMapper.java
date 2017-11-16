package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsInstMerInfoDO;
import com.allcheer.bpos.entity.TblBtsInstMerInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsInstMerInfoDOMapper {
    int countByExample(TblBtsInstMerInfoDOExample example);

    int deleteByExample(TblBtsInstMerInfoDOExample example);

    int insert(TblBtsInstMerInfoDO record);

    int insertSelective(TblBtsInstMerInfoDO record);

    List<TblBtsInstMerInfoDO> selectByExample(TblBtsInstMerInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsInstMerInfoDO record, @Param("example") TblBtsInstMerInfoDOExample example);

    int updateByExample(@Param("record") TblBtsInstMerInfoDO record, @Param("example") TblBtsInstMerInfoDOExample example);
}