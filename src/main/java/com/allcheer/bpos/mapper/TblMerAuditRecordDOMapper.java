package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblMerAuditRecordDO;
import com.allcheer.bpos.entity.TblMerAuditRecordDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TblMerAuditRecordDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int countByExample(TblMerAuditRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int deleteByExample(TblMerAuditRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String auditId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int insert(TblMerAuditRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int insertSelective(TblMerAuditRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    List<TblMerAuditRecordDO> selectByExample(TblMerAuditRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    TblMerAuditRecordDO selectByPrimaryKey(String auditId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblMerAuditRecordDO record, @Param("example") TblMerAuditRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblMerAuditRecordDO record, @Param("example") TblMerAuditRecordDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblMerAuditRecordDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_AUDIT_RECORD
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblMerAuditRecordDO record);


    List<TblMerAuditRecordDO> selectByCondition(Map<String, String> queryMap);

    long countByCondition(Map<String, String> queryMap);
}