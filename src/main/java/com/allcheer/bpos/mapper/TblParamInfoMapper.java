package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblParamInfo;
import com.allcheer.bpos.entity.TblParamInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblParamInfoMapper {
    int countByExample(TblParamInfoExample example);

    int deleteByExample(TblParamInfoExample example);

    int insert(TblParamInfo record);

    int insertSelective(TblParamInfo record);

    List<TblParamInfo> selectByExample(TblParamInfoExample example);

    int updateByExampleSelective(@Param("record") TblParamInfo record, @Param("example") TblParamInfoExample example);

    int updateByExample(@Param("record") TblParamInfo record, @Param("example") TblParamInfoExample example);

	int countByCust(@Param("paramNum") String paramNum, @Param("paramName") String paramName);

	public List<TblParamInfo> selectByCust(@Param("paramNum") String paramNum, @Param("paramName") String paramName);
}