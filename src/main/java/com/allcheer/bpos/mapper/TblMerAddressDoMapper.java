package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblMerAddressDo;
import com.allcheer.bpos.entity.TblMerAddressDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblMerAddressDoMapper {
    int countByExample(TblMerAddressDoExample example);

    int deleteByExample(TblMerAddressDoExample example);

    int insert(TblMerAddressDo record);

    int insertSelective(TblMerAddressDo record);

    List<TblMerAddressDo> selectByExample(TblMerAddressDoExample example);

    int updateByExampleSelective(@Param("record") TblMerAddressDo record, @Param("example") TblMerAddressDoExample example);

    int updateByExample(@Param("record") TblMerAddressDo record, @Param("example") TblMerAddressDoExample example);
}