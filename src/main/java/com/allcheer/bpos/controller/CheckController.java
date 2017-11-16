package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblBtsTransLogDO;
import com.allcheer.bpos.entity.TblChannelCheckFileInfoDO;
import com.allcheer.bpos.entity.TblU1CheckFileDetailDO;
import com.allcheer.bpos.form.TradeForm;
import com.allcheer.bpos.service.CheckService;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 2016/11/16.
 */
@Controller
@RequestMapping(value = "/check")
public class CheckController {


    @Autowired
    private CheckService checkService;


    @RequestMapping(value = "/get_channel_check_file", method = RequestMethod.GET)
    public ModelAndView goToGetChannnelCheckFilePage(ModelAndView modelAndView) {
        modelAndView.setViewName("/check/get_channel_check_file");
        return modelAndView;
    }

    @RequestMapping(value = "/get_channel_check_file", method = RequestMethod.POST)
    public ModelAndView getChannnelCheckFile(@Param("transDate") String transDate,
                                             @Param("gateId") String gateId,
                                             HttpSession session,
                                             ModelAndView modelAndView) {

        UserBO user = (UserBO) session
                .getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        List<TblChannelCheckFileInfoDO> tblChannelCheckFileInfoDOList = new ArrayList<>();

        tblChannelCheckFileInfoDOList = checkService.getChannelCheckFile(transDate, gateId, userName);
        modelAndView.getModel().put("tblChannelCheckFileInfoDOList", tblChannelCheckFileInfoDOList);
        modelAndView.setViewName("/check/get_channel_check_file");

        return modelAndView;
    }

    @RequestMapping(value = "/get_channel_check_file_detail", method = RequestMethod.GET)
    public ModelAndView getChannelCheckFileDetail(@RequestParam("fileNo") String fileNo,
                                                  @RequestParam("gateId") String gateId,
                                                  ModelAndView modelAndView) {
        if (gateId.equals("U1")) {
            modelAndView.setViewName("/check/u1_channel_check_file_detail");
            List<TblU1CheckFileDetailDO> tblU1CheckFileDetailDOList = checkService.getU1ChannelCheckFileDetail(fileNo);
            modelAndView.addObject("resultList", tblU1CheckFileDetailDOList);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/check_trade", method = RequestMethod.GET)
    public ModelAndView goToCheckTradePage(ModelAndView modelAndView) {
        modelAndView.setViewName("/check/check_trade");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/check_trade", method = RequestMethod.POST)
    public Map checkTrade(@Param("transDate") String transDate,
                          @Param("gateId") String gateId,
                          HttpSession session,
                          ModelAndView modelAndView) {

        UserBO user = (UserBO) session
                .getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();

        return checkService.checkTrade(transDate, gateId, userName);
    }

    @RequestMapping(value = "/error_trade_handling", method = RequestMethod.GET)
    public ModelAndView goToErrorTradeHandlingPage(@ModelAttribute("tradeForm") TradeForm tradeForm, ModelAndView modelAndView) {
        modelAndView.setViewName("/check/error_trade_handling");

        return modelAndView;
    }

    @RequestMapping(value = "/error_trade_handling", method = RequestMethod.POST)
    public ModelAndView errorTradeHandling(@ModelAttribute("tradeForm") TradeForm tradeForm, ModelAndView modelAndView) {
        Map<String, String> conditionMap = new HashMap();

        if (StringUtils.isNotBlank(tradeForm.getChannelMerId())) {
            conditionMap.put("channelMerId", tradeForm.getChannelMerId());
        }

        if (StringUtils.isNotBlank(tradeForm.getTransBeginDate())) {
            conditionMap.put("transBeginDate", DateUtil.string10Tostring8(tradeForm.getTransBeginDate()));
        }

        if (StringUtils.isNotBlank(tradeForm.getTransEndDate())) {
            conditionMap.put("transEndTime", DateUtil.string10Tostring8(tradeForm.getTransEndDate()));
        }

        if (StringUtils.isNotBlank(tradeForm.getTransStat())) {
            conditionMap.put("transStat", tradeForm.getTransStat());
        }

        if (StringUtils.isNotBlank(tradeForm.getCheckStat())) {
            conditionMap.put("checkStat", tradeForm.getCheckStat());
        }

        conditionMap.put("pageCurrent", tradeForm.getPageCurrent());
        conditionMap.put("pageSize", tradeForm.getPageSize());

        if (tradeForm.getGateId().equals("U1")) {
            Pagination<TblU1CheckFileDetailDO> tblU1CheckFileDetailDOPagination = checkService.queryU1ErrorTradeByPage(conditionMap);
            tradeForm.setPagination(tblU1CheckFileDetailDOPagination);
            modelAndView.setViewName("/check/u1_error_trade_handling");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/query_u1_plat_trade", method = RequestMethod.GET)
    public ModelAndView goToQueryPlatTradePage(@RequestParam("termtrc") String termtrc,
                                               @RequestParam("refnum") String refnum,
                                               ModelAndView modelAndView) {
        List<TblBtsTransLogDO> tblBtsTransLogDOList = checkService.queryU1PlatTrade(termtrc, refnum);
        if (tblBtsTransLogDOList == null || tblBtsTransLogDOList.size() == 0) {
            throw new BposException("未找到相关交易,请线下核实");
        }
        modelAndView.getModel().put("resultList", tblBtsTransLogDOList);
        modelAndView.setViewName("/check/query_u1_plat_trade");

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/u1_manual_check", method = RequestMethod.POST)
    public Map u1ManualCheck(@RequestParam("manualCheckStat") String manualCheckStat,
                             @RequestParam("ordId") String ordId,
                             @RequestParam("posSeqId") String posSeqId,
                             @RequestParam("refNum") String refNum,
                             ModelAndView modelAndView) {
        
        return checkService.u1ManualCheck(manualCheckStat, ordId, posSeqId, refNum);
    }


}
