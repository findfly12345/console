package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblBtsBMerRConfDO;
import com.allcheer.bpos.entity.TblBtsBMerRConfDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblBtsBMerRConfDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int countByExample(TblBtsBMerRConfDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int deleteByExample(TblBtsBMerRConfDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String bigMerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int insert(TblBtsBMerRConfDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int insertSelective(TblBtsBMerRConfDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    List<TblBtsBMerRConfDO> selectByExample(TblBtsBMerRConfDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    TblBtsBMerRConfDO selectByPrimaryKey(String bigMerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblBtsBMerRConfDO record, @Param("example") TblBtsBMerRConfDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblBtsBMerRConfDO record, @Param("example") TblBtsBMerRConfDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblBtsBMerRConfDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_BTS_BMR_CONF
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblBtsBMerRConfDO record);
}