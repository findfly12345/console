package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.ChannelInfoBO;
import com.allcheer.bpos.entity.TblBankPosInfoDO;
import com.allcheer.bpos.entity.TblBankPosInfoDOExample;
import com.allcheer.bpos.entity.TblBankPosInfoDOKey;
import com.allcheer.bpos.mapper.TblBankPosInfoDOMapper;
import com.allcheer.bpos.service.ChannelService;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fireWorks on 2016/3/10.
 */
@Service("channelService")
public class ChannelServiceImpl implements ChannelService {

    private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    TblBankPosInfoDOMapper bankPosInfoDOMapper;

    public Pagination<ChannelInfoBO> getTheChannel(ChannelInfoBO channelInfoBO){
        TblBankPosInfoDOExample tblBankPosInfoDOExample = new TblBankPosInfoDOExample();
        TblBankPosInfoDOExample.Criteria cri = tblBankPosInfoDOExample.createCriteria();

        TblBankPosInfoDOKey bankPosInfoDOkey = new TblBankPosInfoDOKey();

        if(channelInfoBO.getGateId() != null){
            cri.andGateIdEqualTo(channelInfoBO.getGateId());
            bankPosInfoDOkey.setGateId(channelInfoBO.getGateId());

        }
        if(channelInfoBO.getPosMerId() != null){
            cri.andPosMerIdEqualTo(channelInfoBO.getPosMerId());
            bankPosInfoDOkey.setPosMerId(channelInfoBO.getPosMerId());
        }
        if(channelInfoBO.getPosTermId() != null){
            cri.andPosTermIdEqualTo(channelInfoBO.getPosTermId());
            bankPosInfoDOkey.setPosTermId(channelInfoBO.getPosTermId());
        }

        int count = bankPosInfoDOMapper.countByExample(tblBankPosInfoDOExample);
        Pagination pagination = new Pagination(count, channelInfoBO.getPageCurrent(), channelInfoBO.getPageSize());
        PageHelper.startPage(channelInfoBO.getPageCurrent(), channelInfoBO.getPageSize());
        List<TblBankPosInfoDO> bankPosInfoDOList = bankPosInfoDOMapper.selectByExample(tblBankPosInfoDOExample);

        if(bankPosInfoDOList == null || bankPosInfoDOList.size() == 0){
            return null;
        }

        List<ChannelInfoBO> channelInfoBOList  = new ArrayList<>();
        for(TblBankPosInfoDO bankPosInfoDO:bankPosInfoDOList){
            ChannelInfoBO channelInfoBO1 = new ChannelInfoBO();
            channelInfoBO1.setGateId(bankPosInfoDO.getGateId());
            channelInfoBO1.setPosMerId(bankPosInfoDO.getPosMerId());
            channelInfoBO1.setPosTermId(bankPosInfoDO.getPosTermId());
            channelInfoBO1.setBatchId(bankPosInfoDO.getBatchId());
            channelInfoBO1.setInstId(bankPosInfoDO.getInstId());
            channelInfoBO1.setInstName(bankPosInfoDO.getInstName());
            channelInfoBO1.setMainKey(bankPosInfoDO.getMainKey());
            channelInfoBO1.setMacKey(bankPosInfoDO.getMacKey());
            channelInfoBO1.setPinKey(bankPosInfoDO.getPinKey());
            channelInfoBO1.setTdKey(bankPosInfoDO.getTdKey());

            channelInfoBOList.add(channelInfoBO1);

        }
        pagination.addResult(channelInfoBOList);
        return pagination;

    }

    public ChannelInfoBO selectTheChannel(ChannelInfoBO channelInfoBO){
        TblBankPosInfoDOKey bankPosInfoDOKey = new TblBankPosInfoDOKey();
        bankPosInfoDOKey.setGateId(channelInfoBO.getGateId());
        bankPosInfoDOKey.setPosMerId(channelInfoBO.getPosMerId());
        bankPosInfoDOKey.setPosTermId(channelInfoBO.getPosTermId());

        TblBankPosInfoDO bankPosInfoDO= bankPosInfoDOMapper.selectByPrimaryKey(bankPosInfoDOKey);

        return convertToChannelInfoBO(bankPosInfoDO);
    }

    public Map saveChannelInfo(ChannelInfoBO channelInfoBO){
        Map resultMap = new HashMap();
        Boolean isExist = isChannelInfoExist(channelInfoBO);

        TblBankPosInfoDO bankPosInfoDO = new TblBankPosInfoDO();
        bankPosInfoDO.setGateId(channelInfoBO.getGateId());
        bankPosInfoDO.setPosTermId(channelInfoBO.getPosTermId());
        bankPosInfoDO.setPosMerId(channelInfoBO.getPosMerId());
        bankPosInfoDO.setBatchId(channelInfoBO.getBatchId());
        bankPosInfoDO.setInstId(channelInfoBO.getInstId());
        bankPosInfoDO.setInstName(channelInfoBO.getInstName());
        bankPosInfoDO.setMacKey(channelInfoBO.getMacKey());
        bankPosInfoDO.setMainKey(channelInfoBO.getMainKey());
        bankPosInfoDO.setTdKey(channelInfoBO.getTdKey());
        bankPosInfoDO.setPinKey(channelInfoBO.getPinKey());

        try {
            if (isExist) {
                bankPosInfoDOMapper.updateByPrimaryKeySelective(bankPosInfoDO);
            } else {
                bankPosInfoDOMapper.insert(bankPosInfoDO);
            }
            resultMap.put("statusCode", 200);
            resultMap.put("message", "操作成功!");
        }catch (DataIntegrityViolationException ex){
            logger.info("数据已经存在："+bankPosInfoDO.getGateId()+" "+bankPosInfoDO.getPosMerId()+" "+bankPosInfoDO.getPosTermId()+" "+ex.getMessage());
            resultMap.put("statusCode", 200);
            resultMap.put("message", "数据已经存在!");
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "操作失败!");

        }

        return resultMap;
    }

    public Map deleteChannelInfo(ChannelInfoBO channelInfoBO){
        Map resultMap = new HashMap();

        TblBankPosInfoDOKey bankPosInfoDOKey = new TblBankPosInfoDOKey();
        bankPosInfoDOKey.setGateId(channelInfoBO.getGateId());
        bankPosInfoDOKey.setPosMerId(channelInfoBO.getPosMerId());
        bankPosInfoDOKey.setPosTermId(channelInfoBO.getPosTermId());
        try {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "删除成功!");
            bankPosInfoDOMapper.deleteByPrimaryKey(bankPosInfoDOKey);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "删除失败!");
        }

        return resultMap;
    }

    private ChannelInfoBO convertToChannelInfoBO(TblBankPosInfoDO bankPosInfoDO){
        ChannelInfoBO channelInfoBO = new ChannelInfoBO();
        channelInfoBO.setGateId(bankPosInfoDO.getGateId());
        channelInfoBO.setPosMerId(bankPosInfoDO.getPosMerId());
        channelInfoBO.setPosTermId(bankPosInfoDO.getPosTermId());
        channelInfoBO.setBatchId(bankPosInfoDO.getBatchId());
        channelInfoBO.setInstId(bankPosInfoDO.getInstId());
        channelInfoBO.setInstName(bankPosInfoDO.getInstName());

        return channelInfoBO;

    }

    private Boolean isChannelInfoExist(ChannelInfoBO channelInfoBO){

        TblBankPosInfoDOKey bankPosInfoDOKey = new TblBankPosInfoDOKey();
        bankPosInfoDOKey.setGateId(channelInfoBO.getGateId());
        bankPosInfoDOKey.setPosMerId(channelInfoBO.getPosMerId());
        bankPosInfoDOKey.setPosTermId(channelInfoBO.getPosTermId());

        TblBankPosInfoDO bankPosInfoDO= bankPosInfoDOMapper.selectByPrimaryKey(bankPosInfoDOKey);

        if(bankPosInfoDO == null ){
            return false;
        }
        return true;


    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map importChannelInfo(List<List<Object>> uploadFileList){
        Map resultMap = new HashMap();


        TblBankPosInfoDOExample bankPosInfoDOExample = new TblBankPosInfoDOExample();
        bankPosInfoDOExample.createCriteria().andGateIdEqualTo(uploadFileList.get(0).get(0).toString()).andPosMerIdEqualTo(uploadFileList.get(0).get(1).toString()).andPosTermIdEqualTo(uploadFileList.get(0).get(2).toString());
        List<TblBankPosInfoDO> bankPosInfoDOList = bankPosInfoDOMapper.selectByExample(bankPosInfoDOExample);
            if (bankPosInfoDOList != null && bankPosInfoDOList.size() != 0) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "记录已经存在");
                return resultMap;
            }

            for (List<Object> linked : uploadFileList) {
//                Cannel instMerKeyBO = new InstMerKeyBO();
                TblBankPosInfoDO tblBankPosInfoDO = new TblBankPosInfoDO();
                tblBankPosInfoDO.setGateId(linked.get(0).toString().split("\\.")[0]);
                tblBankPosInfoDO.setPosMerId(linked.get(1).toString().split("\\.")[0]);
                tblBankPosInfoDO.setPosTermId(linked.get(2).toString().split("\\.")[0]);
                if(linked.size() >= 4) {
                    tblBankPosInfoDO.setBatchId(linked.get(3).toString().split("\\.")[0]);
                }
                if(linked.size() >= 5) {
                    tblBankPosInfoDO.setInstId(linked.get(4).toString().split("\\.")[0]);
                }
                if(linked.size() >= 6) {
                    tblBankPosInfoDO.setInstName(linked.get(5).toString().trim());
                }
                if(linked.size() >= 7) {
                    tblBankPosInfoDO.setMainKey(linked.get(6).toString().trim());
                }
                if(linked.size() >= 8) {
                    tblBankPosInfoDO.setMacKey(linked.get(7).toString().trim());
                }
                if(linked.size() >= 9) {
                    tblBankPosInfoDO.setPinKey(linked.get(8).toString().trim());
                }
                if(linked.size() >= 10) {
                    tblBankPosInfoDO.setTdKey(linked.get(9).toString().trim());
                }

                bankPosInfoDOMapper.insert(tblBankPosInfoDO);
            }
        resultMap.put("statusCode", 200);
        resultMap.put("message", "导入成功");
        return resultMap;
    }

}
