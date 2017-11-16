package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblMemberFeeProfit;
import com.allcheer.bpos.entity.TblMemberFeeProfitExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblMemberFeeProfitMapper {
    int countByExample(TblMemberFeeProfitExample example);

    int deleteByExample(TblMemberFeeProfitExample example);

    int insert(TblMemberFeeProfit record);

    int insertSelective(TblMemberFeeProfit record);

    List<TblMemberFeeProfit> selectByExample(TblMemberFeeProfitExample example);

    int updateByExampleSelective(@Param("record") TblMemberFeeProfit record, @Param("example") TblMemberFeeProfitExample example);

    int updateByExample(@Param("record") TblMemberFeeProfit record, @Param("example") TblMemberFeeProfitExample example);
    
	int countByCust(@Param("memberId") String memberId, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("transType") String transType);
    
    public List<TblMemberFeeProfit> selectByCust(@Param("memberId") String memberId, @Param("startDate") String startDate,@Param("endDate") String endDate,@Param("transType") String transType);

}