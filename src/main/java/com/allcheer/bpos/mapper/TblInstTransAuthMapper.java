package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblInstTransAuth;
import com.allcheer.bpos.entity.TblInstTransAuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblInstTransAuthMapper {
    int countByExample(TblInstTransAuthExample example);

    int deleteByExample(TblInstTransAuthExample example);

    int insert(TblInstTransAuth record);

    int insertSelective(TblInstTransAuth record);

    List<TblInstTransAuth> selectByExample(TblInstTransAuthExample example);

    int updateByExampleSelective(@Param("record") TblInstTransAuth record, @Param("example") TblInstTransAuthExample example);

    int updateByExample(@Param("record") TblInstTransAuth record, @Param("example") TblInstTransAuthExample example);
}