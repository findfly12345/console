package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.TblMerQrcode;
import com.allcheer.bpos.entity.TblMerQrcodeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblMerQrcodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int countByExample(TblMerQrcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int deleteByExample(TblMerQrcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String merId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int insert(TblMerQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int insertSelective(TblMerQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    List<TblMerQrcode> selectByExample(TblMerQrcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    TblMerQrcode selectByPrimaryKey(String merId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TblMerQrcode record, @Param("example") TblMerQrcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TblMerQrcode record, @Param("example") TblMerQrcodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TblMerQrcode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TBL_MER_QRCODE
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TblMerQrcode record);
}