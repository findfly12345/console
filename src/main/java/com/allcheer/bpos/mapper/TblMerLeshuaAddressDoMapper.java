package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblMerLeshuaAddressDo;
import com.allcheer.bpos.entity.TblMerLeshuaAddressDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblMerLeshuaAddressDoMapper {
    int countByExample(TblMerLeshuaAddressDoExample example);

    int deleteByExample(TblMerLeshuaAddressDoExample example);

    int insert(TblMerLeshuaAddressDo record);

    int insertSelective(TblMerLeshuaAddressDo record);

    List<TblMerLeshuaAddressDo> selectByExample(TblMerLeshuaAddressDoExample example);

    int updateByExampleSelective(@Param("record") TblMerLeshuaAddressDo record, @Param("example") TblMerLeshuaAddressDoExample example);

    int updateByExample(@Param("record") TblMerLeshuaAddressDo record, @Param("example") TblMerLeshuaAddressDoExample example);
}