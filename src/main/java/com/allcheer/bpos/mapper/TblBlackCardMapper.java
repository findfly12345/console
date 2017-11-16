package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBlackCard;
import com.allcheer.bpos.entity.TblBlackCardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBlackCardMapper {
    int countByExample(TblBlackCardExample example);

    int deleteByExample(TblBlackCardExample example);

    int insert(TblBlackCard record);

    int insertSelective(TblBlackCard record);

    List<TblBlackCard> selectByExample(TblBlackCardExample example);

    int updateByExampleSelective(@Param("record") TblBlackCard record, @Param("example") TblBlackCardExample example);

    int updateByExample(@Param("record") TblBlackCard record, @Param("example") TblBlackCardExample example);
}