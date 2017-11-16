package com.allcheer.bpos.service;

import com.allcheer.bpos.domain.MerRouteBO;
import com.allcheer.bpos.util.Pagination;

import java.util.Map;

/**
 * Created by fireWorks on 2016/3/9.
 */
public interface RouteConfService {

    public Pagination<MerRouteBO> getMerRouteTradeCountRules(MerRouteBO merRouteBO);

    public Map addNewTradeCountRuleForRoute(MerRouteBO merRouteBO);

    public Map updateTradeCountRuleForRoute(MerRouteBO merRouteBO);

    public String findBigMer(String tradeCount, String merId);

    public Map deleteTradeCountRuleForRoute(String id);
}
