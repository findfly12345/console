package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblInstKeyInfo;
import com.allcheer.bpos.entity.TblInstKeyInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblInstKeyInfoMapper {
    int countByExample(TblInstKeyInfoExample example);

    int deleteByExample(TblInstKeyInfoExample example);

    int insert(TblInstKeyInfo record);

    int insertSelective(TblInstKeyInfo record);

    List<TblInstKeyInfo> selectByExample(TblInstKeyInfoExample example);

    int updateByExampleSelective(@Param("record") TblInstKeyInfo record, @Param("example") TblInstKeyInfoExample example);

    int updateByExample(@Param("record") TblInstKeyInfo record, @Param("example") TblInstKeyInfoExample example);
}