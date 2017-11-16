package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsInstKeyDO;
import com.allcheer.bpos.entity.TblBtsInstKeyDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsInstKeyDOMapper {
    int countByExample(TblBtsInstKeyDOExample example);

    int deleteByExample(TblBtsInstKeyDOExample example);

    int deleteByPrimaryKey(String instId);

    int insert(TblBtsInstKeyDO record);

    int insertSelective(TblBtsInstKeyDO record);

    List<TblBtsInstKeyDO> selectByExample(TblBtsInstKeyDOExample example);

    TblBtsInstKeyDO selectByPrimaryKey(String instId);

    int updateByExampleSelective(@Param("record") TblBtsInstKeyDO record, @Param("example") TblBtsInstKeyDOExample example);

    int updateByExample(@Param("record") TblBtsInstKeyDO record, @Param("example") TblBtsInstKeyDOExample example);

    int updateByPrimaryKeySelective(TblBtsInstKeyDO record);

    int updateByPrimaryKey(TblBtsInstKeyDO record);
}