package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.domain.AgentFeeBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblBtsInstMccFeeInfoDOMapper;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/7.
 */

@Service("instBtsService")
public class InstBtsServiceImpl implements InstBtsService {

    @Autowired
    private TblBtsInstDOMapper tblBtsInstDOMapper;

    @Autowired
    private TblBtsInstMccFeeInfoDOMapper tblBtsInstMccFeeInfoDOMapper;

    @Override
    public Pagination<TblBtsInstDO> queryInstList(TblBtsInstDO tblBtsInstDO) {
        TblBtsInstDOExample tblBtsInstDOExample = new TblBtsInstDOExample();
        TblBtsInstDOExample.Criteria criteria = tblBtsInstDOExample.createCriteria();

        if (StringUtils.isNotBlank(tblBtsInstDO.getInstName())) {
            criteria.andInstNameLike("%" + tblBtsInstDO.getInstName() + "%");
        }

        if (StringUtils.isNotBlank(tblBtsInstDO.getInstCode())) {
            criteria.andInstCodeEqualTo(tblBtsInstDO.getInstCode());
        }
        int count = tblBtsInstDOMapper.countByExample(tblBtsInstDOExample);
        int pageCurrent = Integer.valueOf(tblBtsInstDO.getPageCurrent());
        int pageSize = Integer.valueOf(tblBtsInstDO.getPageSize());
        Pagination pagination = new Pagination(count, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);
        List<TblBtsInstDO> tblBtsInstDOList = tblBtsInstDOMapper.selectByExample(tblBtsInstDOExample);

        if (tblBtsInstDOList == null || tblBtsInstDOList.size() == 0) {
            return null;
        }
        pagination.addResult(tblBtsInstDOList);
        return pagination;
    }

    @Override
    public AgentFeeBO getInstFeeInfo(String instId) {
        AgentFeeBO agentFeeBO = new AgentFeeBO();
        try {
            TblBtsInstMccFeeInfoDOExample tblBtsInstMccFeeInfoDOExample = new TblBtsInstMccFeeInfoDOExample();
            tblBtsInstMccFeeInfoDOExample.createCriteria().andInstIdEqualTo(instId);
            List<TblBtsInstMccFeeInfoDO> tblBtsInstFeeInfoDOList = tblBtsInstMccFeeInfoDOMapper.selectByExample(tblBtsInstMccFeeInfoDOExample);
            for (TblBtsInstMccFeeInfoDO tbl : tblBtsInstFeeInfoDOList) {
                String mccType = tbl.getMccType();
                mccType = mccType.replace("0", "");
                int type = Integer.parseInt(mccType);
                String code = CalcModeUtil.splitCalcMode(tbl.getCalcMode(), false);
                String rate = code.substring(0, code.indexOf(","));
                String max = "";
                switch (type) {
                    case 1:
                        max = code.split(",")[1];
                        agentFeeBO.setCode01(rate);
                        agentFeeBO.setCode01L(max);
                        break;
                    case 2:
                        agentFeeBO.setCode02(rate);
                        break;
                    case 3:
                        agentFeeBO.setCode03(rate);
                        break;
                    case 4:
                        agentFeeBO.setCode04(rate);
                        break;
                    case 5:
                        agentFeeBO.setCode05(rate);
                        break;
                    case 6:
                        agentFeeBO.setCode06(rate);
                        break;
                    case 7:
                        agentFeeBO.setCode07(rate);
                        break;
                    case 8:
                        agentFeeBO.setCode08(rate);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            throw new BposException("查询已有机构费率失败!");
        }
        return agentFeeBO;


    }

    @Override
    public Map updateFeeSetttingsForInst(AgentFeeBO agentFeeBO, String instId) {
        Map resultMap = new HashMap();
        try {
            TblBtsInstMccFeeInfoDOExample tblBtsInstMccFeeInfoDOExample = new TblBtsInstMccFeeInfoDOExample();
            tblBtsInstMccFeeInfoDOExample.createCriteria().andInstIdEqualTo(instId);
            List<TblBtsInstMccFeeInfoDO> tblBtsInstFeeInfoDOList = tblBtsInstMccFeeInfoDOMapper.selectByExample(tblBtsInstMccFeeInfoDOExample);
            Map<String, TblBtsInstMccFeeInfoDO> map = new HashMap<String, TblBtsInstMccFeeInfoDO>();
            for (TblBtsInstMccFeeInfoDO tt : tblBtsInstFeeInfoDOList) {
                map.put(tt.getMccType(), tt);
            }
            Field[] field = agentFeeBO.getClass().getDeclaredFields();
            String name = "";
            String value = "";
            String fee01L = "";
            String calc_mode = "";
            for (int j = 0; j < field.length; j++) {
                name = field[j].getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = agentFeeBO.getClass().getMethod("get" + name);
                value = (String) m.invoke(agentFeeBO);
                if (StringUtils.isBlank(value))
                    continue;
                if ("AgentId".equals(name) || "InstId".equals(name)) {
                    continue;
                } else {
                    if ("Code01L".equals(name)) {
                        fee01L = value;
                        continue;
                    } else {
                        name = name.substring(4);
                        if ("01".equals(name))
                            calc_mode = "MTM(0," + fee01L + ",AMT*" + value + ")";
                        else
                            calc_mode = "AMT*" + value;
                        if (map.get(name) != null) {
                            TblBtsInstMccFeeInfoDO tblBtsInstMccFeeInfoDO = map.get(name);
                            tblBtsInstMccFeeInfoDO.setCalcMode(calc_mode);
                            TblBtsInstMccFeeInfoDOExample tblBtsInstMccFeeInfoDOExample1 = new TblBtsInstMccFeeInfoDOExample();
                            tblBtsInstMccFeeInfoDOExample.createCriteria().andInstIdEqualTo(instId).andMccTypeEqualTo(name);

                            tblBtsInstMccFeeInfoDOMapper.updateByExampleSelective(tblBtsInstMccFeeInfoDO, tblBtsInstMccFeeInfoDOExample1);
                        } else {
                            TblBtsInstMccFeeInfoDO tblBtsInstMccFeeInfoDO = new TblBtsInstMccFeeInfoDO();
                            tblBtsInstMccFeeInfoDO.setInstId(instId);
                            tblBtsInstMccFeeInfoDO.setMccType(name);
                            tblBtsInstMccFeeInfoDO.setCalcMode(calc_mode);
                            tblBtsInstMccFeeInfoDOMapper.insertSelective(tblBtsInstMccFeeInfoDO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "更新失败!");
            return resultMap;
        }
        resultMap.put("statusCode", 200);
        resultMap.put("message", "更新成功!");
        return resultMap;
    }
}
