package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.InstAccountingSummaryDO;
import com.allcheer.bpos.entity.InstAccountingSummaryDOExample;
import com.allcheer.bpos.entity.InstAccountingSummaryDOKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstAccountingSummaryDOMapper {
	
    int countByExample(InstAccountingSummaryDOExample example);

    int deleteByExample(InstAccountingSummaryDOExample example);

    int deleteByPrimaryKey(InstAccountingSummaryDOKey key);

    int insert(InstAccountingSummaryDO record);

    int insertSelective(InstAccountingSummaryDO record);

    List<InstAccountingSummaryDO> selectByExample(InstAccountingSummaryDOExample example);

    InstAccountingSummaryDO selectByPrimaryKey(InstAccountingSummaryDOKey key);

    int updateByExampleSelective(@Param("record") InstAccountingSummaryDO record, @Param("example") InstAccountingSummaryDOExample example);

    int updateByExample(@Param("record") InstAccountingSummaryDO record, @Param("example") InstAccountingSummaryDOExample example);

    int updateByPrimaryKeySelective(InstAccountingSummaryDO record);

    int updateByPrimaryKey(InstAccountingSummaryDO record);
}