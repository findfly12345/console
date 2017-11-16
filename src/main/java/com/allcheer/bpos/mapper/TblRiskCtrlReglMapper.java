package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblRiskCtrlRegl;
import com.allcheer.bpos.entity.TblRiskCtrlReglExample;
import com.allcheer.bpos.entity.TblRiskCtrlReglKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblRiskCtrlReglMapper {
    int countByExample(TblRiskCtrlReglExample example);

    int deleteByExample(TblRiskCtrlReglExample example);

    int deleteByPrimaryKey(TblRiskCtrlReglKey key);

    int insert(TblRiskCtrlRegl record);

    int insertSelective(TblRiskCtrlRegl record);

    List<TblRiskCtrlRegl> selectByExample(TblRiskCtrlReglExample example);

    TblRiskCtrlRegl selectByPrimaryKey(TblRiskCtrlReglKey key);

    int updateByExampleSelective(@Param("record") TblRiskCtrlRegl record, @Param("example") TblRiskCtrlReglExample example);

    int updateByExample(@Param("record") TblRiskCtrlRegl record, @Param("example") TblRiskCtrlReglExample example);

    int updateByPrimaryKeySelective(TblRiskCtrlRegl record);

    int updateByPrimaryKey(TblRiskCtrlRegl record);
}