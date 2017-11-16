package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblAreaCode;
import com.allcheer.bpos.entity.TblAreaCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblAreaCodeMapper {
    int countByExample(TblAreaCodeExample example);

    int deleteByExample(TblAreaCodeExample example);

    int insert(TblAreaCode record);

    int insertSelective(TblAreaCode record);

    List<TblAreaCode> selectByExample(TblAreaCodeExample example);

    int updateByExampleSelective(@Param("record") TblAreaCode record, @Param("example") TblAreaCodeExample example);

    int updateByExample(@Param("record") TblAreaCode record, @Param("example") TblAreaCodeExample example);
}