package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBlackMer;
import com.allcheer.bpos.entity.TblBlackMerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBlackMerMapper {
    int countByExample(TblBlackMerExample example);

    int deleteByExample(TblBlackMerExample example);

    int insert(TblBlackMer record);

    int insertSelective(TblBlackMer record);

    List<TblBlackMer> selectByExample(TblBlackMerExample example);

    int updateByExampleSelective(@Param("record") TblBlackMer record, @Param("example") TblBlackMerExample example);

    int updateByExample(@Param("record") TblBlackMer record, @Param("example") TblBlackMerExample example);
}