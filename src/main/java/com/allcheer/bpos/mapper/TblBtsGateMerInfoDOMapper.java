package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsGateMerInfoDO;
import com.allcheer.bpos.entity.TblBtsGateMerInfoDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsGateMerInfoDOMapper {
    int countByExample(TblBtsGateMerInfoDOExample example);

    int deleteByExample(TblBtsGateMerInfoDOExample example);

    int insert(TblBtsGateMerInfoDO record);

    int insertSelective(TblBtsGateMerInfoDO record);

    List<TblBtsGateMerInfoDO> selectByExample(TblBtsGateMerInfoDOExample example);

    int updateByExampleSelective(@Param("record") TblBtsGateMerInfoDO record, @Param("example") TblBtsGateMerInfoDOExample example);

    int updateByExample(@Param("record") TblBtsGateMerInfoDO record, @Param("example") TblBtsGateMerInfoDOExample example);
}