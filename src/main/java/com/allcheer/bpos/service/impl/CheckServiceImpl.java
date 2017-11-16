package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblBtsTransLogDOMapper;
import com.allcheer.bpos.mapper.TblChannelCheckFileInfoDOMapper;
import com.allcheer.bpos.mapper.TblU1CheckFileDetailDOMapper;
import com.allcheer.bpos.service.CheckService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/11/16.
 */
@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private TblChannelCheckFileInfoDOMapper tblChannelCheckFileInfoDOMapper;

    @Autowired
    private TblU1CheckFileDetailDOMapper tblU1CheckFileDetailDOMapper;

    @Autowired
    private TblBtsTransLogDOMapper tblBtsTransLogDOMapper;

    @Autowired
    private SeqMapper seqMapper;

    @Override
    public List<TblChannelCheckFileInfoDO> getChannelCheckFile(String transDate, String gateId,String userName) {
        List<TblChannelCheckFileInfoDO> tblChannelCheckFileInfoDOList = new ArrayList<>();
        if(gateId.equals("U1")) {
            String filePath = getU1CheckFileByFtp(transDate);
            if(filePath == null) {
                throw new BposException("未获取到钱宝对账文件");
            }
            tblChannelCheckFileInfoDOList = saveU1ChannelCheckFileInfo(transDate,filePath,userName);
        }
       return tblChannelCheckFileInfoDOList;
    }

    @Override
    public List<TblU1CheckFileDetailDO> getU1ChannelCheckFileDetail(String fileNo) {
        TblU1CheckFileDetailDOExample tblU1CheckFileDetailDOExample = new TblU1CheckFileDetailDOExample();
        tblU1CheckFileDetailDOExample.createCriteria().andFileNoEqualTo(fileNo);
        return tblU1CheckFileDetailDOMapper.selectByExample(tblU1CheckFileDetailDOExample);
    }

    @Override
    @Transactional
    public Map checkTrade(String transDate, String gateId, String userName) {
        Map resultMap = new HashMap();
        transDate = DateUtil.string10Tostring8(transDate);
        TblChannelCheckFileInfoDOExample tblChannelCheckFileInfoDOExample = new TblChannelCheckFileInfoDOExample();
        tblChannelCheckFileInfoDOExample.createCriteria().andTransDateEqualTo(transDate).andGateIdEqualTo(gateId);
        List<TblChannelCheckFileInfoDO> tblChannelCheckFileInfoDOList = tblChannelCheckFileInfoDOMapper.selectByExample(tblChannelCheckFileInfoDOExample);
        if (tblChannelCheckFileInfoDOList == null || tblChannelCheckFileInfoDOList.size() == 0) {
            throw new BposException("对账文件还未获取");
        }
        TblChannelCheckFileInfoDO tblChannelCheckFileInfoDO = tblChannelCheckFileInfoDOList.get(0);
        if (tblChannelCheckFileInfoDO.getCheckStat().equals("3")) {
            throw new BposException("对账已完成,无须再次对账");
        }
        if (tblChannelCheckFileInfoDO.getCheckStat().equals("1")) {
            throw new BposException("对账进行中,无须再次对账");
        }

        tblChannelCheckFileInfoDO.setCheckStat("1");
        tblChannelCheckFileInfoDO.setUserName(userName);
        tblChannelCheckFileInfoDO.setUpdateTime(DateUtil.getCurrentTime());
        tblChannelCheckFileInfoDOMapper.updateByPrimaryKeySelective(tblChannelCheckFileInfoDO);

        if (gateId.equals("U1")) {
            TblU1CheckFileDetailDOExample tblU1CheckFileDetailDOExample = new TblU1CheckFileDetailDOExample();
            tblU1CheckFileDetailDOExample.createCriteria().
                    andFileNoEqualTo(tblChannelCheckFileInfoDO.getFileNo()).
                    andTrntypEqualTo("1101");
            List<TblU1CheckFileDetailDO> tblU1CheckFileDetailDOList = tblU1CheckFileDetailDOMapper.selectByExample(tblU1CheckFileDetailDOExample);
                for (TblU1CheckFileDetailDO tblU1CheckFileDetailDO : tblU1CheckFileDetailDOList) {
                    checkU1SingleTrade(tblU1CheckFileDetailDO);
            }
        }

        tblChannelCheckFileInfoDO.setCheckStat("3");
        tblChannelCheckFileInfoDO.setUserName(userName);
        tblChannelCheckFileInfoDO.setUpdateTime(DateUtil.getCurrentTime());
        tblChannelCheckFileInfoDOMapper.updateByPrimaryKeySelective(tblChannelCheckFileInfoDO);

        resultMap.put("statusCode",200);
        resultMap.put("message","操作成功");

        return resultMap;
    }

    @Override
    public Pagination<TblU1CheckFileDetailDO> queryU1ErrorTradeByPage(Map<String,String> map) {
        TblU1CheckFileDetailDOExample tblU1CheckFileDetailDOExample = new TblU1CheckFileDetailDOExample();
        TblU1CheckFileDetailDOExample.Criteria criteria = tblU1CheckFileDetailDOExample.createCriteria();
        List<TblU1CheckFileDetailDO> tblU1CheckFileDetailDOList = new ArrayList<>();
        if(StringUtils.isNotBlank(map.get("channelMerId"))) {
            criteria.andMercnoEqualTo(map.get("channelMerId"));
        }
        if(StringUtils.isNotBlank(map.get("transBeginDate"))) {
            criteria.andSysdateGreaterThanOrEqualTo(map.get("transBeginDate"));
        }
        if(StringUtils.isNotBlank(map.get("transEndTime"))) {
            criteria.andSysdateLessThanOrEqualTo(map.get("transEndTime"));
        }

        if(StringUtils.isNotBlank(map.get("checkStat"))) {
            criteria.andCheckStatEqualTo(map.get("checkStat"));
        }

        if(StringUtils.isNotBlank(map.get("transStat"))&&!map.get("transStat").equals("S")) {
            int count = 0;
            int pageCurrent = Integer.valueOf(map.get("pageCurrent"));
            int pageSize = Integer.valueOf(map.get("pageSize"));

            Pagination pagination = new Pagination(count, pageCurrent, pageSize);
            PageHelper.startPage(pageCurrent, pageSize);
            pagination.addResult(tblU1CheckFileDetailDOList);
            return pagination;
        }
        int count = tblU1CheckFileDetailDOMapper.countByExample(tblU1CheckFileDetailDOExample);
        int pageCurrent = Integer.valueOf(map.get("pageCurrent"));
        int pageSize = Integer.valueOf(map.get("pageSize"));

        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        tblU1CheckFileDetailDOList = tblU1CheckFileDetailDOMapper.selectByExample(tblU1CheckFileDetailDOExample);
        pagination.addResult(tblU1CheckFileDetailDOList);
        return pagination;
    }

    @Override
    public List<TblBtsTransLogDO> queryU1PlatTrade(String termtrc, String refnum) {
        TblBtsTransLogDOExample tblBtsTransLogDOExample = new TblBtsTransLogDOExample();
        tblBtsTransLogDOExample.createCriteria().
                andPosSeqIdEqualTo(termtrc).
                andRefNumEqualTo(refnum);
        return tblBtsTransLogDOMapper.selectByExample(tblBtsTransLogDOExample);
    }

    @Override
    public Map u1ManualCheck(String manualCheckStat, String ordId, String posSeqId, String refNum) {
        Map resultMap = new HashMap();
        TblBtsTransLogDOExample tblBtsTransLogDOExample = new TblBtsTransLogDOExample();
        tblBtsTransLogDOExample.createCriteria().
                andOrdIdEqualTo(ordId).andChkFlagEqualTo("I");
        TblBtsTransLogDO tblBtsTransLogDO = new TblBtsTransLogDO();
        tblBtsTransLogDO.setTransStat(manualCheckStat);
        tblBtsTransLogDO.setChkFlag("S");
        tblBtsTransLogDOMapper.updateByExampleSelective(tblBtsTransLogDO,tblBtsTransLogDOExample);

        TblU1CheckFileDetailDOExample tblU1CheckFileDetailDOExample = new TblU1CheckFileDetailDOExample();
        tblU1CheckFileDetailDOExample.createCriteria().
                andTermtrcEqualTo(posSeqId).
                andRefnumEqualTo(refNum).
                andCheckStatNotEqualTo("2");
        TblU1CheckFileDetailDO tblU1CheckFileDetailDO = new TblU1CheckFileDetailDO();
        tblU1CheckFileDetailDO.setCheckStat("2");

        tblU1CheckFileDetailDOMapper.updateByExampleSelective(tblU1CheckFileDetailDO,tblU1CheckFileDetailDOExample);
        resultMap.put("statusCode",200);
        resultMap.put("message","操作成功");

        return resultMap;
    }

    @Transactional
    private void checkU1SingleTrade(TblU1CheckFileDetailDO tblU1CheckFileDetailDO) {
        TblBtsTransLogDOExample tblBtsTransLogDOExample = new TblBtsTransLogDOExample();
        tblBtsTransLogDOExample.createCriteria().andGateIdEqualTo("U1").
                andOrdAmtEqualTo(tblU1CheckFileDetailDO.getTranamt()).
                andRefNumEqualTo(tblU1CheckFileDetailDO.getRefnum()).
                andPosSeqIdEqualTo(tblU1CheckFileDetailDO.getTermtrc()).
                andTransStatEqualTo("S");
        List<TblBtsTransLogDO> tblBtsTransLogDOList = tblBtsTransLogDOMapper.selectByExample(tblBtsTransLogDOExample);
        if(tblBtsTransLogDOList == null || tblBtsTransLogDOList.size() == 0) {
            tblU1CheckFileDetailDO.setCheckStat("1");
        } else {
            TblBtsTransLogDO tblBtsTransLogDO = tblBtsTransLogDOList.get(0);
            tblBtsTransLogDO.setChkFlag("S");
            tblU1CheckFileDetailDO.setCheckStat("2");
            tblBtsTransLogDOMapper.updateByExample(tblBtsTransLogDO,tblBtsTransLogDOExample);
        }
        tblU1CheckFileDetailDOMapper.updateByPrimaryKeySelective(tblU1CheckFileDetailDO);
    }

    private String getU1CheckFileByFtp(String transDate) {
        transDate = DateUtil.string10Tostring8(transDate);
        FTPClient ftpClient = new FTPClient();
        FTPConfig ftpConfig = new FTPConfig();
        ftpConfig.setHost("116.228.6.67");
        ftpConfig.setPort(3022);
        ftpConfig.setUserName("ftpshxinke1");
        ftpConfig.setPassword("ftpshxinke230FF431");
        ftpClient.setFtpConfig(ftpConfig);
        boolean result = false;
        try {
            ftpClient.connectServer();
            result = ftpClient.download("/chkfile_"+transDate+"_52900085.txt",SystemConstant.QIANBAO_CHECK_FILE_PATH+"chkfile_"+transDate+"_52900085.txt");
            try {
                ftpClient.destroy();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        if(result) {

            return SystemConstant.QIANBAO_CHECK_FILE_PATH+"chkfile_"+transDate+"_52900085.txt";
        } else {
            return null;
        }
    }

    @Transactional
    private List<TblChannelCheckFileInfoDO> saveU1ChannelCheckFileInfo(String transDate, String filePath, String userName) {
        transDate = DateUtil.string10Tostring8(transDate);
        Map resultMap = new HashMap();
        TblChannelCheckFileInfoDOKey tblChannelCheckFileInfoDOKey = new TblChannelCheckFileInfoDOKey();
        tblChannelCheckFileInfoDOKey.setFileType("2");
        tblChannelCheckFileInfoDOKey.setGateId("U1");
        tblChannelCheckFileInfoDOKey.setTransDate(transDate);
        tblChannelCheckFileInfoDOKey.setFilePath(filePath);
        TblChannelCheckFileInfoDO tblChannelCheckFileInfoDO = tblChannelCheckFileInfoDOMapper.selectByPrimaryKey(tblChannelCheckFileInfoDOKey);
        if(tblChannelCheckFileInfoDO != null) {
            throw new BposException("已存在钱宝对账文件数据");
        }
        tblChannelCheckFileInfoDO = new TblChannelCheckFileInfoDO();
        String fileNo = getFileNo();
        tblChannelCheckFileInfoDO.setFileNo(fileNo);
        tblChannelCheckFileInfoDO.setFilePath(filePath);
        tblChannelCheckFileInfoDO.setGateId("U1");
        tblChannelCheckFileInfoDO.setUserName(userName);
        tblChannelCheckFileInfoDO.setCheckStat("0");
        tblChannelCheckFileInfoDO.setUserName(userName);
        tblChannelCheckFileInfoDO.setUpdateTime(DateUtil.getCurrentTime());
        tblChannelCheckFileInfoDO.setCreateTime(DateUtil.getCurrentTime());
        tblChannelCheckFileInfoDO.setFileType("2");
        tblChannelCheckFileInfoDO.setTransDate(transDate);
        tblChannelCheckFileInfoDOMapper.insert(tblChannelCheckFileInfoDO);

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            TblU1CheckFileDetailDO tblU1CheckFileDetailDO = new TblU1CheckFileDetailDO();
            tblU1CheckFileDetailDO.setFileNo(fileNo);
            tblU1CheckFileDetailDO.setCheckStat("0");
            tblU1CheckFileDetailDO.setUserName(userName);
            String line;
            while((line = bufferedReader.readLine())!=null){
                if(StringUtils.isBlank(line)) {
                    continue;
                }
                String[] data = line.split("\\|");
                tblU1CheckFileDetailDO.setMercno(data[0]);
                tblU1CheckFileDetailDO.setTermid(data[1]);
                tblU1CheckFileDetailDO.setSysdate(data[2]);
                tblU1CheckFileDetailDO.setLoctime(data[3]);
                tblU1CheckFileDetailDO.setTrntyp(data[4]);
                tblU1CheckFileDetailDO.setPriacno(data[5]);
                tblU1CheckFileDetailDO.setTranamt(data[6]);
                tblU1CheckFileDetailDO.setTermtrc(data[7]);
                tblU1CheckFileDetailDO.setRefnum(data[8]);
                tblU1CheckFileDetailDO.setAuthid(data[9]);
                tblU1CheckFileDetailDO.setUpdateTime(DateUtil.getCurrentTime());
                tblU1CheckFileDetailDO.setCreateTime(DateUtil.getCurrentTime());
                tblU1CheckFileDetailDOMapper.insert(tblU1CheckFileDetailDO);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BposException("钱宝对账文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BposException("读取钱宝对账文件异常");
        }

        TblChannelCheckFileInfoDOExample tblChannelCheckFileInfoDOExample = new TblChannelCheckFileInfoDOExample();
        tblChannelCheckFileInfoDOExample.createCriteria().
                andGateIdEqualTo("U1").
                andTransDateEqualTo(transDate);
        return tblChannelCheckFileInfoDOMapper.selectByExample(tblChannelCheckFileInfoDOExample);
    }

    private String getFileNo() {
        long checkFileSeq = seqMapper.getSequenceNextVal("SEQ_CHECK_FILE_NO");
        return DateUtil.getCurrentDate() + StringUtils.leftPad(String.valueOf(checkFileSeq), 7, '0');
    }

}
