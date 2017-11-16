package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.entity.vo.TradeAmtVO;
import com.allcheer.bpos.mapper.HomeMapper;
import com.allcheer.bpos.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/10/25.
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    HomeMapper homeMapper;

    @Override
    public Map getTradeAmt() {
        List<TradeAmtVO> posTradeAmtVOList = homeMapper.queryPosTradeAmt();

        List<TradeAmtVO> weChatTradeAmtVOList = homeMapper.queryWeChatTradeAmt();

        List<TradeAmtVO> noCardTradeAmtVOList = homeMapper.queryNoCardTradeAmt();

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("posTradeAmtVOList",posTradeAmtVOList);
        resultMap.put("weChatTradeAmtVOList",weChatTradeAmtVOList);
        resultMap.put("noCardTradeAmtVOList",noCardTradeAmtVOList);
        return resultMap;
    }
}
