package com.allcheer.bpos.controller;

import com.allcheer.bpos.form.TradeListForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fireWorks on 2016/2/26.
 */
@Controller
@RequestMapping(value = "/TC")
public class TradeInfoListController {

    @RequestMapping(value = "/trade_info_list", method = RequestMethod.GET)
    public String goTradeInfoPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("tradeListForm") TradeListForm tradeListForm) {
        return "tradeinfolist";
    }

}
