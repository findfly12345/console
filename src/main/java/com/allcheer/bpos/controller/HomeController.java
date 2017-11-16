package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.vo.TradeAmtVO;
import com.allcheer.bpos.service.HomeService;
import com.allcheer.bpos.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 15/12/29.
 */
@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView goToHomePage(ModelAndView modelAndView) {
        Map map = homeService.getTradeAmt();

        List<TradeAmtVO> posTradeAmtVOList = (List<TradeAmtVO>) map.get("posTradeAmtVOList");
        List<TradeAmtVO> weChatTradeAmtVOList = (List<TradeAmtVO>) map.get("weChatTradeAmtVOList");
        List<TradeAmtVO> noCardTradeAmtVOList = (List<TradeAmtVO>) map.get("noCardTradeAmtVOList");
        String currentDate = DateUtil.getCurrentDate();
        String yesterDay = DateUtil.getCurrentYesterDay();

        String posTradeAmt = "0.00";
        String posHisTradeAmt = "0.00";
        if (posTradeAmtVOList != null && posTradeAmtVOList.size() != 0) {
            if (posTradeAmtVOList.size() == 1) {
                if (posTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                    posHisTradeAmt = posTradeAmtVOList.get(0).getSumAmt();
                } else {
                    posTradeAmt = posTradeAmtVOList.get(0).getSumAmt();
                }
            } else {
                if (posTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                    posHisTradeAmt = posTradeAmtVOList.get(0).getSumAmt();
                } else {
                    posTradeAmt = posTradeAmtVOList.get(0).getSumAmt();
                }

                if (posTradeAmtVOList.get(1).getTradeDate().equals(yesterDay)) {
                    posHisTradeAmt = posTradeAmtVOList.get(1).getSumAmt();
                } else {
                    posTradeAmt = posTradeAmtVOList.get(1).getSumAmt();
                }

            }
        }

        String weChatTradeAmt = "0.00";
        String weChatHisTradeAmt = "0.00";
        if (weChatTradeAmtVOList != null && weChatTradeAmtVOList.size() != 0) {
            if (weChatTradeAmtVOList.size() == 1) {
                if (weChatTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                    weChatHisTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                    weChatTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                }
            } else {

                if (weChatTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                    weChatHisTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                    weChatTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                }

                if (weChatTradeAmtVOList.get(1).getTradeDate().equals(yesterDay)) {
                    weChatHisTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(1).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                    weChatTradeAmt = new BigDecimal(weChatTradeAmtVOList.get(1).getSumAmt()).divide(new BigDecimal("100")).toString();
                }
            }

        }

        String noCardTradeAmt = "0.00";
        String noCardHistTradeAmt = "0.00";
        if (noCardTradeAmtVOList != null && noCardTradeAmtVOList.size() != 0) {
            if (noCardTradeAmtVOList.size() == 1) {
                if (noCardTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                	noCardHistTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                	noCardTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                }
            } else {
                if (noCardTradeAmtVOList.get(0).getTradeDate().equals(yesterDay)) {
                	noCardHistTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                	noCardTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(0).getSumAmt()).divide(new BigDecimal("100")).toString();
                }

                if (noCardTradeAmtVOList.get(1).getTradeDate().equals(yesterDay)) {
                	noCardHistTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(1).getSumAmt()).divide(new BigDecimal("100")).toString();
                } else {
                	noCardTradeAmt = new BigDecimal(noCardTradeAmtVOList.get(1).getSumAmt()).divide(new BigDecimal("100")).toString();
                }
            }
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        posTradeAmt = decimalFormat.format(new BigDecimal(posTradeAmt));
        posHisTradeAmt = decimalFormat.format(new BigDecimal(posHisTradeAmt));
        weChatTradeAmt = decimalFormat.format(new BigDecimal(weChatTradeAmt));
        weChatHisTradeAmt = decimalFormat.format(new BigDecimal(weChatHisTradeAmt));
        noCardTradeAmt = decimalFormat.format(new BigDecimal(noCardTradeAmt));
        noCardHistTradeAmt = decimalFormat.format(new BigDecimal(noCardHistTradeAmt));

        modelAndView.getModel().put("posTradeAmt", posTradeAmt);
        modelAndView.getModel().put("posHisTradeAmt", posHisTradeAmt);
        modelAndView.getModel().put("weChatTradeAmt", weChatTradeAmt);
        modelAndView.getModel().put("weChatHisTradeAmt", weChatHisTradeAmt);
        modelAndView.getModel().put("noCardTradeAmt", noCardTradeAmt);
        modelAndView.getModel().put("noCardHistTradeAmt", noCardHistTradeAmt);
        modelAndView.setViewName("home");

        return modelAndView;
    }
}
