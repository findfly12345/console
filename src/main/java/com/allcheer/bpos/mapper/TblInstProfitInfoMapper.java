package com.allcheer.bpos.mapper;


import com.allcheer.bpos.entity.TblInstProfitInfo;
import com.allcheer.bpos.entity.TblInstProfitInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblInstProfitInfoMapper {
    int countByExample(TblInstProfitInfoExample example);

    int deleteByExample(TblInstProfitInfoExample example);

    int insert(TblInstProfitInfo record);

    int insertSelective(TblInstProfitInfo record);

    List<TblInstProfitInfo> selectByExample(TblInstProfitInfoExample example);

    int updateByExampleSelective(@Param("record") TblInstProfitInfo record, @Param("example") TblInstProfitInfoExample example);

    int updateByExample(@Param("record") TblInstProfitInfo record, @Param("example") TblInstProfitInfoExample example);
   
    int countByCust(@Param("instId") String instId, @Param("startDate") String startDate,@Param("endDate") String endDate, @Param("transType")String transType);
    
    public List<TblInstProfitInfo> selectByCust(@Param("instId") String instId, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("transType")String transType);

}