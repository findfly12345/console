package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblRiskTimeInf;
import com.allcheer.bpos.entity.TblRiskTimeInfExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblRiskTimeInfMapper {
    int countByExample(TblRiskTimeInfExample example);

    int deleteByExample(TblRiskTimeInfExample example);

    int insert(TblRiskTimeInf record);

    int insertSelective(TblRiskTimeInf record);

    List<TblRiskTimeInf> selectByExample(TblRiskTimeInfExample example);

    int updateByExampleSelective(@Param("record") TblRiskTimeInf record, @Param("example") TblRiskTimeInfExample example);

    int updateByExample(@Param("record") TblRiskTimeInf record, @Param("example") TblRiskTimeInfExample example);
    
    int countByCust(@Param("riskSeq") String riskSeq, @Param("riskType") String riskType);

	public List<TblRiskTimeInf> selectByCust(@Param("riskSeq") String riskSeq, @Param("riskType") String riskType);
}