package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsInstDOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TblBtsInstDOMapper {

	  int countByExample(TblBtsInstDOExample example);

	    int deleteByExample(TblBtsInstDOExample example);

	    int deleteByPrimaryKey(String instCode);

	    int insert(TblBtsInstDO record);

	    int insertSelective(TblBtsInstDO record);

	    List<TblBtsInstDO> selectByExample(TblBtsInstDOExample example);

	    TblBtsInstDO selectByPrimaryKey(String instCode);

	    int updateByExampleSelective(@Param("record") TblBtsInstDO record, @Param("example") TblBtsInstDOExample example);

	    int updateByExample(@Param("record") TblBtsInstDO record, @Param("example") TblBtsInstDOExample example);

	    int updateByPrimaryKeySelective(TblBtsInstDO record);

	    int updateByPrimaryKey(TblBtsInstDO record);
	    
	    String instCodeNext();

	TblBtsInstDO instAndProfit(@Param("instId") String instId);
}