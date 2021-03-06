package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblInstMerAddDetailInfoDO;
import com.allcheer.bpos.entity.TblInstMerAddDetailInfoDOExample;
import com.allcheer.bpos.entity.TblInstMerAddDetailInfoDOKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblInstMerAddDetailInfoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int countByExample(TblInstMerAddDetailInfoDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int deleteByExample(TblInstMerAddDetailInfoDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(TblInstMerAddDetailInfoDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int insert(TblInstMerAddDetailInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int insertSelective(TblInstMerAddDetailInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    List<TblInstMerAddDetailInfoDO> selectByExample(TblInstMerAddDetailInfoDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    TblInstMerAddDetailInfoDO selectByPrimaryKey(TblInstMerAddDetailInfoDOKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblInstMerAddDetailInfoDO record, @Param("example") TblInstMerAddDetailInfoDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblInstMerAddDetailInfoDO record, @Param("example") TblInstMerAddDetailInfoDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblInstMerAddDetailInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_INST_MER_ADD_DETAIL_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblInstMerAddDetailInfoDO record);
}