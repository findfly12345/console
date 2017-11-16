package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblCustAddressDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblCustAddressDoMapper {
    int countByExample(TblCustAddressDoExample example);

    int deleteByExample(TblCustAddressDoExample example);

    int insert(TblCustAddressDo record);

    int insertSelective(TblCustAddressDo record);

    List<TblCustAddressDo> selectByExample(TblCustAddressDoExample example);

    int updateByExampleSelective(@Param("record") TblCustAddressDo record, @Param("example") TblCustAddressDoExample example);

    int updateByExample(@Param("record") TblCustAddressDo record, @Param("example") TblCustAddressDoExample example);
}