package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblTermInfo;
import com.allcheer.bpos.entity.TblTermInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblTermInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int countByExample(TblTermInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int deleteByExample(TblTermInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String termId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int insert(TblTermInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int insertSelective(TblTermInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    List<TblTermInfo> selectByExample(TblTermInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    TblTermInfo selectByPrimaryKey(String termId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblTermInfo record, @Param("example") TblTermInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblTermInfo record, @Param("example") TblTermInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblTermInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_TERM_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblTermInfo record);
}