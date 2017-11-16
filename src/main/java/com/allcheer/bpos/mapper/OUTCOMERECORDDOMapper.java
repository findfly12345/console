package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.OUTCOMERECORDDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OUTCOMERECORDDOMapper {
    int countByExample(OUTCOMERECORDDOExample example);

    int deleteByExample(OUTCOMERECORDDOExample example);

    int deleteByPrimaryKey(String id);

    int insert(OUTCOMERECORDDO record);

    int insertSelective(OUTCOMERECORDDO record);

    List<OUTCOMERECORDDO> selectByExample(OUTCOMERECORDDOExample example);

    OUTCOMERECORDDO selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OUTCOMERECORDDO record, @Param("example") OUTCOMERECORDDOExample example);

    int updateByExample(@Param("record") OUTCOMERECORDDO record, @Param("example") OUTCOMERECORDDOExample example);

    int updateByPrimaryKeySelective(OUTCOMERECORDDO record);

    int updateByPrimaryKey(OUTCOMERECORDDO record);
}