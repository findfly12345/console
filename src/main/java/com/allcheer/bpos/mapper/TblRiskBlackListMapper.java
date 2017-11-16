package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblRiskBlackList;
import com.allcheer.bpos.entity.TblRiskBlackListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblRiskBlackListMapper {
    int countByExample(TblRiskBlackListExample example);

    int deleteByExample(TblRiskBlackListExample example);

    int insert(TblRiskBlackList record);

    int insertSelective(TblRiskBlackList record);

    List<TblRiskBlackList> selectByExample(TblRiskBlackListExample example);

    int updateByExampleSelective(@Param("record") TblRiskBlackList record, @Param("example") TblRiskBlackListExample example);

    int updateByExample(@Param("record") TblRiskBlackList record, @Param("example") TblRiskBlackListExample example);
}