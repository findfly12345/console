package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.OUTCOMERECORDDO;
import com.allcheer.bpos.entity.TBLOutcomeBankRecordDO;
import com.allcheer.bpos.form.OutcomeTransLogForm;
import com.allcheer.bpos.service.OutcomeLogService;
import com.allcheer.bpos.util.AmtUtil;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
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
@RequestMapping("outcome")
public class OutcomeLogController {

    private static final Logger logger = LoggerFactory.getLogger(OutcomeLogController.class);

    @Autowired
    private OutcomeLogService   outcomeLogService;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        OutcomeTransLogForm trans = new OutcomeTransLogForm();

        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = outcomeLogService.getOutcomeTransListCount(trans);

        Pagination<TBLOutcomeBankRecordDO> page = new Pagination<TBLOutcomeBankRecordDO>(transSize,
            pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<TBLOutcomeBankRecordDO> transList = outcomeLogService.getOutcomeTransList(trans);
        String selectAmt = "0";
        for (TBLOutcomeBankRecordDO _TBLOutcomeBankRecordDO : transList)
            selectAmt = AmtUtil.add(
                selectAmt,
                _TBLOutcomeBankRecordDO.getOutcomeAmount() == null ? "0" : _TBLOutcomeBankRecordDO
                    .getOutcomeAmount());
        selectAmt = (new BigDecimal(selectAmt).divide(new BigDecimal(100))).toString();
        page.addResult(transList);
        page.setSelectAmt(selectAmt);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "outcomelog/outcomePage";
    }

    @RequiresAuthentication
    @RequestMapping(value = "goPageYes")
    public String goYesPage(HttpServletRequest request) {
        OutcomeTransLogForm trans = new OutcomeTransLogForm();

        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = outcomeLogService.getOutcomeTransListCount(trans);

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();

        Pagination<TBLOutcomeBankRecordDO> page = new Pagination<TBLOutcomeBankRecordDO>(transSize,
            pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));

        List<TBLOutcomeBankRecordDO> transList = outcomeLogService.getOutcomeTransList(trans);
        String selectAmt = "0";
        for (TBLOutcomeBankRecordDO _TBLOutcomeBankRecordDO : transList)
            selectAmt = AmtUtil.add(
                selectAmt,
                _TBLOutcomeBankRecordDO.getOutcomeAmount() == null ? "0" : _TBLOutcomeBankRecordDO
                    .getOutcomeAmount());
        selectAmt = (new BigDecimal(selectAmt).divide(new BigDecimal(100))).toString();
        page.addResult(transList);
        page.setSelectAmt(selectAmt);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "outcomelog/outcomePage";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("trans") OutcomeTransLogForm trans, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {
        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = outcomeLogService.getOutcomeTransListCount(trans);

        Pagination<TBLOutcomeBankRecordDO> page = new Pagination<TBLOutcomeBankRecordDO>(transSize,
            pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TBLOutcomeBankRecordDO> transList = outcomeLogService.getOutcomeTransList(trans);
        String selectAmt = "0";
        for (TBLOutcomeBankRecordDO _TBLOutcomeBankRecordDO : transList) {
            _TBLOutcomeBankRecordDO.setOutcomeAmount(String.format("%.2f",
                (new BigDecimal(_TBLOutcomeBankRecordDO.getOutcomeAmount() == null ? "0"
                    : _TBLOutcomeBankRecordDO.getOutcomeAmount()).divide(new BigDecimal(100)))));

            _TBLOutcomeBankRecordDO.setOutcomeNetAmount(String.format("%.2f", (new BigDecimal(
                _TBLOutcomeBankRecordDO.getOutcomeNetAmount() == null ? "0"
                    : _TBLOutcomeBankRecordDO.getOutcomeNetAmount()).divide(new BigDecimal(100)))));

            _TBLOutcomeBankRecordDO.setOutcomeFee(String.format("%.2f",
                (new BigDecimal(_TBLOutcomeBankRecordDO.getOutcomeFee() == null ? "0"
                    : _TBLOutcomeBankRecordDO.getOutcomeFee()).divide(new BigDecimal(100)))));

            _TBLOutcomeBankRecordDO
                .setOrderRecvTime(_TBLOutcomeBankRecordDO.getOrderRecvTime()
                                  + _TBLOutcomeBankRecordDO.getOrderRecvDate() == null ? ""
                    : DateUtil.string2Timestamp(
                        _TBLOutcomeBankRecordDO.getOrderRecvDate()
                                + _TBLOutcomeBankRecordDO.getOrderRecvTime(), "yyyyMMddHHmmss")
                        .toString());

            if ("S".equals(_TBLOutcomeBankRecordDO.getOutcomeStatus()))
                _TBLOutcomeBankRecordDO.setOutcomeStatus("成功");
            else if ("P".equals(_TBLOutcomeBankRecordDO.getOutcomeStatus()))
                _TBLOutcomeBankRecordDO.setOutcomeStatus("处理中");
            else if ("F".equals(_TBLOutcomeBankRecordDO.getOutcomeStatus()))
                _TBLOutcomeBankRecordDO.setOutcomeStatus("失败");
            else if ("I".equals(_TBLOutcomeBankRecordDO.getOutcomeStatus()))
                _TBLOutcomeBankRecordDO.setOutcomeStatus("初始");
            else
                _TBLOutcomeBankRecordDO.setOutcomeStatus("未知");

            selectAmt = AmtUtil.add(
                selectAmt,
                _TBLOutcomeBankRecordDO.getOutcomeAmount() == null ? "0" : _TBLOutcomeBankRecordDO
                    .getOutcomeAmount());
        }
        selectAmt = (new BigDecimal(selectAmt).divide(new BigDecimal(100))).toString();
        page.addResult(transList);
        page.setSelectAmt(selectAmt);
        request.setAttribute("pageUser", page);

        return "outcomelog/outcomePage";
    }

    @RequestMapping("download")
    @RequiresAuthentication
    public String doDownload(@ModelAttribute("trans") OutcomeTransLogForm trans,
                             HttpSession session, HttpServletRequest request,
                             HttpServletResponse response) {
        List<TBLOutcomeBankRecordDO> transList = outcomeLogService.getOutcomeTransList(trans);
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            String fileName = new String(("OutcomeInfo").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + "_"
                                                      + DateUtil.getDateAndTime() + ".xls");// 组装附件名称和格式
            outcomeLogService.exportSettlementInfo(transList, outputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
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
