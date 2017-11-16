package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblBtsTransLogDO;
import com.allcheer.bpos.form.TransLogForm;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.TransLogService;
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

/**
 * POS交易管理
 */

@Controller
@RequestMapping("trans")
public class TransLogController {

    private static final Logger logger = LoggerFactory.getLogger(TransLogController.class);

    @Autowired
    private TransLogService transLogService;

    @Autowired
    private InstitutionService institutionService;

    @RequiresAuthentication
    @RequestMapping(value = "gopage")
    public String goToLoginPage(HttpServletRequest request) {
        TransLogForm trans = new TransLogForm();

        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = transLogService.getTransListCount(trans);
        Pagination<TblBtsTransLogDO> page = new Pagination<TblBtsTransLogDO>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(new Date(), "yyyy-MM-dd"));

        List<TblBtsTransLogDO> transList = transLogService.getTransList(trans);
        String selectAmt = "0";
        for (TblBtsTransLogDO tblBtsTransLogDO : transList)
            selectAmt = AmtUtil.add(selectAmt, tblBtsTransLogDO.getOrdAmt() == null ? "0" : tblBtsTransLogDO.getOrdAmt());
        selectAmt = new BigDecimal(selectAmt).toString();
        page.addResult(transList);
        page.setSelectAmt(selectAmt);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "translog/transPage";
    }

    @RequiresAuthentication
    @RequestMapping(value = "goPageYes")
    public String goYesPage(HttpServletRequest request) {
        TransLogForm trans = new TransLogForm();

        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = transLogService.getTransListCount(trans);

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);
        date = calendar.getTime();

        Pagination<TblBtsTransLogDO> page = new Pagination<TblBtsTransLogDO>(transSize, pageCurrent,
                pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        trans.setStartTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));
        trans.setEndTransDateTime(DateUtil.date2String(date, "yyyy-MM-dd"));

        List<TblBtsTransLogDO> transList = transLogService.getTransList(trans);
        String selectAmt = "0";
        for (TblBtsTransLogDO tblBtsTransLogDO : transList)
            selectAmt = AmtUtil.add(selectAmt, tblBtsTransLogDO.getOrdAmt() == null ? "0" : tblBtsTransLogDO.getOrdAmt());
        selectAmt = new BigDecimal(selectAmt).toString();
        page.addResult(transList);
        page.setSelectAmt(selectAmt);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", page);
        request.setAttribute("trans", trans);

        return "translog/transPage";
    }

    @RequestMapping("search")
    @RequiresAuthentication
    public String doSelect(@ModelAttribute("trans") TransLogForm trans, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) {

        int pageCurrent = Integer.parseInt(trans.getPageCurrent());
        int pageSize = Integer.parseInt(trans.getPageSize());
        int transSize = transLogService.getTransListCount(trans);

        Pagination<TblBtsTransLogDO> page = new Pagination<TblBtsTransLogDO>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblBtsTransLogDO> transList = transLogService.getTransList(trans);
        String selectAmt = "0";
        for (TblBtsTransLogDO tblBtsTransLogDO : transList)
            selectAmt = AmtUtil.add(selectAmt, tblBtsTransLogDO.getOrdAmt() == null ? "0" : tblBtsTransLogDO.getOrdAmt());
        selectAmt = new BigDecimal(selectAmt).toString();

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        page.addResult(transList);
        page.setSelectAmt(selectAmt);
        request.setAttribute("pageUser", page);

        return "translog/transPage";
    }

    @RequestMapping("download")
    @RequiresAuthentication
    public String doDownload(@ModelAttribute("trans") TransLogForm trans, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {
        List<TblBtsTransLogDO> transList = transLogService.getTransList(trans);

        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            String fileName = new String(("TransInfo").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName + "_" + DateUtil.getDateAndTime() + ".xls");// 组装附件名称和格式
            transLogService.exportSettlementInfo(transList, outputStream);
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
