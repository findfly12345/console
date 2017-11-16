package com.allcheer.bpos.mapper;

import com.allcheer.bpos.entity.vo.TradeAmtVO;

import java.util.List;

/**
 * Created by APPLE on 2016/10/22.
 */
public interface HomeMapper {

    List<TradeAmtVO> queryPosTradeAmt();

    List<TradeAmtVO> queryWeChatTradeAmt();

    List<TradeAmtVO> queryNoCardTradeAmt();
}
