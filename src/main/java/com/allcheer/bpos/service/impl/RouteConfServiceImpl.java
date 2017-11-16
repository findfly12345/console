package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.MerRouteBO;
import com.allcheer.bpos.domain.MerSelectBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblBankPosInfoDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMerDOMapper;
import com.allcheer.bpos.mapper.TblMerRouteConfCountDOMapper;
import com.allcheer.bpos.service.RouteConfService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.*;

/**
 * Created by fireWorks on 2016/3/9.
 */
@Service("routeConfServiceImpl")
public class RouteConfServiceImpl implements RouteConfService {

    private static final Logger logger = LoggerFactory.getLogger(RouteConfServiceImpl.class);

    @Autowired
    TblMerRouteConfCountDOMapper merRouteConfCountDOMapper;

    @Autowired
    TblBtsInstMerDOMapper instMerDOMapper;

    @Autowired
    SeqMapper seqMapper;

    @Autowired
    TblBankPosInfoDOMapper bankPosInfoDOMapper;

    @Override
    public Pagination<MerRouteBO> getMerRouteTradeCountRules(MerRouteBO merRouteBO) {
        List<MerRouteBO> merRouteBOList = new ArrayList<>();

        TblMerRouteConfCountDOExample merRouteConfCountDOExample = new TblMerRouteConfCountDOExample();
        TblMerRouteConfCountDOExample.Criteria cri = merRouteConfCountDOExample.createCriteria();

        if(merRouteBO.getId() != null){
            cri.andIdEqualTo(merRouteBO.getId());
        }

        if(merRouteBO.getMerId() != null){
            cri.andMerIdEqualTo(merRouteBO.getMerId());
        }

        if(merRouteBO.getCurrentTradeCount() != null){
            cri.andCurrentTradeCountEqualTo(merRouteBO.getCurrentTradeCount());
        }

        int count = merRouteConfCountDOMapper.countByExample(merRouteConfCountDOExample);
        Pagination pagination = new Pagination(count,merRouteBO.getPageCurrent(),merRouteBO.getPageSize());
        PageHelper.startPage(merRouteBO.getPageCurrent(),merRouteBO.getPageSize());
        List<TblMerRouteConfCountDO> merRouteConfCountDOList = merRouteConfCountDOMapper.selectByExample(merRouteConfCountDOExample);

        if (merRouteConfCountDOList == null || merRouteConfCountDOList.size() == 0){
            return null;
        }

        for(TblMerRouteConfCountDO merRouteConfCountDO : merRouteConfCountDOList){
            MerRouteBO merRouteBO1 = new MerRouteBO();
            merRouteBO1.setId(merRouteConfCountDO.getId());
            merRouteBO1.setMerId(merRouteConfCountDO.getMerId());
            merRouteBO1.setCurrentTradeCount(merRouteConfCountDO.getCurrentTradeCount());
            String[] bigMerIdArray = merRouteConfCountDO.getRefsBigMers().split(",");
            List<String> bigMerIdList = Arrays.asList(bigMerIdArray);
            merRouteBO1.setRefsBigMers(bigMerIdList);

            merRouteBOList.add(merRouteBO1);

        }
        pagination.addResult(merRouteBOList);

        return pagination;

    }

    public Map addNewTradeCountRuleForRoute(MerRouteBO merRouteBO){
        Map resultMap = new HashMap();

//        Boolean isMerBindToInst = isMerBindToInst(merRouteBO.getMerId());
//        if(!isMerBindToInst){
//            resultMap.put("statusCode", 300);
//            resultMap.put("message", "商户号不存在");
//            return resultMap;
//        }

        Boolean isExist = false;
        TblMerRouteConfCountDOExample merRouteConfCountDOExample = new TblMerRouteConfCountDOExample();
        merRouteConfCountDOExample.createCriteria().andMerIdEqualTo(merRouteBO.getMerId()).andCurrentTradeCountEqualTo(merRouteBO.getCurrentTradeCount());
        List<TblMerRouteConfCountDO> merRouteConfCountDOList = merRouteConfCountDOMapper.selectByExample(merRouteConfCountDOExample);
        if(merRouteConfCountDOList == null || merRouteConfCountDOList.size()==0){
            isExist = false;

        }else{
            isExist = true;
        }

        TblMerRouteConfCountDO tblMerRouteConfCountDO = new TblMerRouteConfCountDO();
        if(isExist){
            tblMerRouteConfCountDO.setId(merRouteConfCountDOList.get(0).getId());
        }else{
            tblMerRouteConfCountDO.setId(String.valueOf(seqMapper.getMerTradeCountRuleSeq()));
        }
        tblMerRouteConfCountDO.setMerId(merRouteBO.getMerId());
        tblMerRouteConfCountDO.setCurrentTradeCount(merRouteBO.getCurrentTradeCount());

        if(merRouteBO.getRefsBigMers() == null || merRouteBO.getRefsBigMers().size() == 0){

        }else {
            for(String bigMer : merRouteBO.getRefsBigMers()){
                if(!isMerBindToInst(bigMer)){
                    resultMap.put("statusCode", 300);
                    resultMap.put("message", "大商户不存在："+bigMer);
                    return resultMap;
                }
            }
            String refsBigMers = ListToString(merRouteBO.getRefsBigMers());

            tblMerRouteConfCountDO.setRefsBigMers(refsBigMers);
        }
//        if(merRouteBO.getMerSelectBOList() != null && merRouteBO.getMerSelectBOList().size() != 0){
//            List<String> bigMerList = new ArrayList<>();
//            for (MerSelectBO merSelectBO :merRouteBO.getMerSelectBOList()){
//                if(merSelectBO.getChecked() == "true"){
//                    String bigMer = merSelectBO.getMerId();
//                    bigMerList.add(bigMer);
//                }
//            }
//            String refsBigMers = ListToString(bigMerList);
//            if(isExist){
//                refsBigMers = merRouteConfCountDOList.get(0).getRefsBigMers()+","+refsBigMers;
//            }
//
//            tblMerRouteConfCountDO.setRefsBigMers(refsBigMers);
//        }

        try {
            if(isExist){
                merRouteConfCountDOMapper.updateByPrimaryKeySelective(tblMerRouteConfCountDO);

            }else {
                merRouteConfCountDOMapper.insert(tblMerRouteConfCountDO);
            }
            resultMap.put("statusCode", 200);
            resultMap.put("message", "规则已保存");
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "规则保存失败");
        }

        return resultMap;
    }

    @Override
    public Map updateTradeCountRuleForRoute(MerRouteBO merRouteBO){
        Map resultMap = new HashMap();

        TblMerRouteConfCountDO tblMerRouteConfCountDO = new TblMerRouteConfCountDO();

        tblMerRouteConfCountDO.setId(merRouteBO.getId());
        tblMerRouteConfCountDO.setCurrentTradeCount(merRouteBO.getCurrentTradeCount());

        if(merRouteBO.getRefsBigMers() == null || merRouteBO.getRefsBigMers().size() == 0){

        }else {
            String refsBigMers = ListToString(merRouteBO.getRefsBigMers());

            tblMerRouteConfCountDO.setRefsBigMers(refsBigMers);
        }

        try {
            merRouteConfCountDOMapper.updateByPrimaryKeySelective(tblMerRouteConfCountDO);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "规则已保存");
        }catch (Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "规则保存失败");
        }

        return resultMap;
    }

    @Override
    public Map deleteTradeCountRuleForRoute(String id){
        Map resultMap = new HashMap();

        try {
            merRouteConfCountDOMapper.deleteByPrimaryKey(id);
            resultMap.put("statusCode", 200);
            resultMap.put("message", "规则已删除");
        } catch (Exception ex){
            logger.error(ex.getMessage());
            resultMap.put("statusCode", 300);
            resultMap.put("message", "规则删除失败");
        }
        return resultMap;
    }

    private String ListToString(List<String> stringList){
        StringBuilder result = new StringBuilder();
        boolean flag=false;

        for (String str : stringList){
            if(flag){
                result.append(",");
            }else{
                flag = true;
            }
            result.append(str);
        }

        return result.toString();

    }

    @Override
    public String findBigMer(String tradeCount, String merId){
        TblMerRouteConfCountDOExample merRouteConfCountDOExample = new TblMerRouteConfCountDOExample();
        merRouteConfCountDOExample.createCriteria().andMerIdEqualTo(merId).andCurrentTradeCountEqualTo(tradeCount);
        List<TblMerRouteConfCountDO> merRouteConfCountDOList = merRouteConfCountDOMapper.selectByExample(merRouteConfCountDOExample);
        if(merRouteConfCountDOList == null || merRouteConfCountDOList.size() == 0){
            return null;
        }
        String bigMers = merRouteConfCountDOList.get(0).getRefsBigMers();
        String[] bigMersArray = bigMers.split(",");
        String now = DateUtil.getCurrentTime();
        String val = now.substring(now.length()-3,now.length()-1);
        int rCount = Integer.parseInt(val)%bigMersArray.length;

        return bigMersArray[rCount];

    }

    private Boolean isMerBindToInst(String merId){

        return true;
//        Boolean result = false;
//
//        TblBankPosInfoDOExample bankPosInfoDOExample = new TblBankPosInfoDOExample();
//        bankPosInfoDOExample.createCriteria().andPosMerIdEqualTo(merId);
//        List<TblBankPosInfoDO> bankPosInfoDOList = bankPosInfoDOMapper.selectByExample(bankPosInfoDOExample);
//        if(bankPosInfoDOList == null || bankPosInfoDOList.size() == 0){
//            result =  false;
//        }else{
//            result = true;
//        }
//
//        TblBtsInstMerDOExample instMerDOExample = new TblBtsInstMerDOExample();
//        instMerDOExample.createCriteria().andMerIdEqualTo(formatMerId(merId));
//        List<TblBtsInstMerDO> instMerDOList = instMerDOMapper.selectByExample(instMerDOExample);
//        if(instMerDOList == null || instMerDOList.size()==0){
//            result = false;
//        }else{
//            result = true;
//        }
//        return result;
    }

    private String formatMerId(String str){
        String padSpace = StringUtils.repeat(" ",15-str.length());
        str = str + padSpace;
        return str;
    }

}
