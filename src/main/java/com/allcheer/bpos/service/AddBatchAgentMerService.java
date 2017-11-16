package com.allcheer.bpos.service;

import com.allcheer.bpos.entity.TblAgentMerAddDetailInfoDO;

import java.util.List;

/**
 * Created by LiuBin on 2017/2/14.
 */
public interface AddBatchAgentMerService {

    public String addBatchAgentMerData(String agetId,String dataFile,String fileType,String userName);

    public List<TblAgentMerAddDetailInfoDO> queryAddDetailInfoByBatchNo(String BatchNo);
}
