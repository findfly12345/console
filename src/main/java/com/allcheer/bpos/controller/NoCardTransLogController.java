package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.WCINCOMEDO;
import com.allcheer.bpos.form.WCTransLogForm;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.WCTransLogService;
import com.allcheer.bpos.util.*;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
@RequestMapping("nocard")
public class NoCardTransLogController {

    private static final Logger logger = LoggerFactory.getLogger(NoCardTransLogController.class);

    @Autowired
    private WCTransLogService wctransLogService;

    @Autowired
    private InstitutionService institutionService;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        WCTransLogForm trans = new WCTransLogForm();
        

        trans.setIncomePlatform(Constant.NO_CARD_GATE_ID);
        
        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = wctransLogService.getWCIncomeListCount(trans);

        Pagination<WCINCOMEDO> page = new Pagination<WCINCOMEDO>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<WCINCOMEDO> transList = wctransLogService.getTransList(trans);
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                String orderAmt = transList.get(i).getIncomeAmount();

                if (StringUtils.filedNotNull(orderAmt)) {
                    transList.get(i).setIncomeAmount(
                            AmtUtil.amtFormat((new BigDecimal(orderAmt).divide(new BigDecimal(100))).toString()));
                }

                String feeAmt = transList.get(i).getTransFee();

                if (StringUtils.filedNotNull(feeAmt)) {
                    transList.get(i).setTransFee(
                            AmtUtil.amtFormat((new BigDecimal(feeAmt).divide(new BigDecimal(100))).toString()));
                }

            }
        }

        String selectAmt = "0";
        for (WCINCOMEDO _WCINCOMEDO : transList)
            selectAmt = AmtUtil.add(selectAmt,
                    _WCINCOMEDO.getIncomeAmount() == null ? "0" : _WCINCOMEDO.getIncomeAmount());
        page.addResult(transList);
        page.setSelectAmt(selectAmt);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "translog/noCardPage";
    }

    @RequiresAuthentication
    @RequestMapping(value = "goPageYes")
    public String goPage2(HttpServletRequest request) {
        WCTransLogForm trans = new WCTransLogForm();
        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = wctransLogService.getWCIncomeListCount(trans);
        
        trans.setIncomePlatform(Constant.NO_CARD_GATE_ID);

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();

        Pagination<WCINCOMEDO> page = new Pagination<WCINCOMEDO>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));

        List<WCINCOMEDO> transList = wctransLogService.getTransList(trans);
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                String orderAmt = transList.get(i).getIncomeAmount();

                if (StringUtils.filedNotNull(orderAmt)) {
                    transList.get(i).setIncomeAmount(
                            AmtUtil.amtFormat((new BigDecimal(orderAmt).divide(new BigDecimal(100))).toString()));
                }

                String feeAmt = transList.get(i).getTransFee();

                if (StringUtils.filedNotNull(feeAmt)) {
                    transList.get(i).setTransFee(
                            AmtUtil.amtFormat((new BigDecimal(feeAmt).divide(new BigDecimal(100))).toString()));
                }

            }
        }

        String selectAmt = "0";
        for (WCINCOMEDO _WCINCOMEDO : transList)
            selectAmt = AmtUtil.add(selectAmt,
                    _WCINCOMEDO.getIncomeAmount() == null ? "0" : _WCINCOMEDO.getIncomeAmount());
        page.addResult(transList);
        page.setSelectAmt(selectAmt);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "translog/noCardPage";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("trans") WCTransLogForm trans, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = wctransLogService.getWCIncomeListCount(trans);

        trans.setIncomePlatform(Constant.NO_CARD_GATE_ID);
        
        Pagination<WCINCOMEDO> page = new Pagination<WCINCOMEDO>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<WCINCOMEDO> transList = wctransLogService.getTransList(trans);
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                String orderAmt = transList.get(i).getIncomeAmount();

                if (StringUtils.filedNotNull(orderAmt)) {
                    transList.get(i).setIncomeAmount(
                            AmtUtil.amtFormat((new BigDecimal(orderAmt).divide(new BigDecimal(100))).toString()));
                }

                String feeAmt = transList.get(i).getTransFee();

                if (StringUtils.filedNotNull(feeAmt)) {
                    transList.get(i).setTransFee(
                            AmtUtil.amtFormat((new BigDecimal(feeAmt).divide(new BigDecimal(100))).toString()));
                }
            }
        }

        String selectAmt = "0";
        for (WCINCOMEDO _WCINCOMEDO : transList)
            selectAmt = AmtUtil.add(selectAmt,
                    _WCINCOMEDO.getIncomeAmount() == null ? "0" : _WCINCOMEDO.getIncomeAmount());

        page.addResult(transList);
        page.setSelectAmt(selectAmt);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", page);

        return "translog/noCardPage";
    }

    @RequestMapping("download")
    @RequiresAuthentication
    public String doDownload(@ModelAttribute("trans") WCTransLogForm trans, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = null;
        
        trans.setIncomePlatform(Constant.NO_CARD_GATE_ID);

        try {
            List<WCINCOMEDO> transList = wctransLogService.getTransList(trans);
            outputStream = response.getOutputStream();
            String fileName = new String(("WCTransInfo").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName + "_" + DateUtil.getDateAndTime() + ".xls");// 组装附件名称和格式
            wctransLogService.exportSettlementInfo(transList, outputStream);
        } catch (IOException ex) {
            logger.error("导出文件异常: ", ex);
        } catch (Exception ex) {
            logger.error("导出文件系统异常: ", ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error("关闭流异常: ", e);
            }
        }
        return null;
    }
}
