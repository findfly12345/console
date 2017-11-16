package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.Minsheng.IntoPicAddressRepuest;
import com.allcheer.bpos.domain.Minsheng.IntoPiecesRepuest;
import com.allcheer.bpos.entity.TblInstMerAddBatchInfoDO;
import com.allcheer.bpos.entity.TblInstMerAddDetailInfoDO;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by APPLE on 16/10/19.
 */
public interface AddInstMerService {

    String addInstMerData(String instId,String dataFile,String userName);

    String addInstMerAttachment(String instId,String attachmentFile,String attachmentFileName ,String userName);

    TblInstMerAddBatchInfoDO selectTblInstMerAddBatchInfoDOByPk(String batchNo);

    void newResultFile(String batchNo, ServletOutputStream outputStream);

    List<TblInstMerAddDetailInfoDO> selectTblInstMerAddDetailInfoDOByBatchNo(String batchNo);
    void instMerAddBatchInfo(IntoPiecesRepuest req);
    public void instMerPicAddressInfo(IntoPicAddressRepuest req);

}
