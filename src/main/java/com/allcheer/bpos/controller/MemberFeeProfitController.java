package com.allcheer.bpos.controller;

import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.form.InstProfitInfoForm;
import com.allcheer.bpos.form.MemberFeeProfitForm;
import com.allcheer.bpos.form.TransLogForm;
import com.allcheer.bpos.mapper.TblInstProfitInfoMapper;
import com.allcheer.bpos.mapper.TblMemberFeeProfitMapper;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.TransLogService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
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
import java.util.List;


@Controller
@RequestMapping("profit")
public class MemberFeeProfitController {

    private static final Logger logger = LoggerFactory.getLogger(MemberFeeProfitController.class);

    @Autowired
    private TblMemberFeeProfitMapper tblMemberFeeProfitMapper;

    @Autowired
    private TblInstProfitInfoMapper tblInstProfitInfoMapper;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private TransLogService transLogService;

    @RequiresAuthentication
    @RequestMapping(value = "inst")
    public String goToInstLoginPage(HttpServletRequest request) {
        InstProfitInfoForm instProfitInfoForm = new InstProfitInfoForm();

        int pageCurrent = 1;
        int pageSize = 100;

        TblInstProfitInfoExample tblInstProfitInfoExample = new TblInstProfitInfoExample();
        tblInstProfitInfoExample.createCriteria();
        int transSize = tblInstProfitInfoMapper.countByExample(tblInstProfitInfoExample);

        Pagination<TblInstProfitInfo> pagination = new Pagination<TblInstProfitInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblInstProfitInfo> tblInstProfitInfoList = tblInstProfitInfoMapper.selectByExample(tblInstProfitInfoExample);
        pagination.addResult(tblInstProfitInfoList);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("profit", instProfitInfoForm);

        return "profit/inst_info";
    }


    @RequestMapping("exportIinstProfit")
    @RequiresAuthentication
    public String exportIinstProfitPage(HttpSession session, HttpServletRequest request, @ModelAttribute("pnrOrdLogFomr") TransLogForm pnrOrdLogFomr) {
        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        return "profit/exportIinstProfit";
    }

    @RequestMapping("exportChannelReport")
    @RequiresAuthentication
    public String exportChannelReport(HttpSession session, HttpServletRequest request, @ModelAttribute("pnrOrdLogFomr") TransLogForm pnrOrdLogFomr) {
        return "profit/exportChannelReport";
    }


    @RequestMapping("downloadChannelReport")
    @RequiresAuthentication
    public String doDownloaddownloadChannelReport(@ModelAttribute("pnrOrdLogFomr") TransLogForm trans, HttpSession session,
                                                  HttpServletRequest request, HttpServletResponse response) {
        List<InstProfitDO> transList = transLogService.rumGroupChannelReport(trans);
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fileName = new String(("机构分润总汇").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName + "_" + DateUtil.getDateAndTime() + ".xls");// 组装附件名称和格式
            transLogService.exporchannelReport(transList, outputStream, trans);
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

    @RequestMapping("download")
    @RequiresAuthentication
    public String doDownload(@ModelAttribute("pnrOrdLogFomr") TransLogForm trans, HttpSession session,
                             HttpServletRequest request, HttpServletResponse response) {
        List<InstProfitDO> transList = transLogService.rumGroupCardFlagByInstInstId(trans);
        response.setContentType("application/binary;charset=ISO8859_1");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fileName = new String(("机构分润总汇").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + fileName + "_" + DateUtil.getDateAndTime() + ".xls");// 组装附件名称和格式
            transLogService.exporInstProfitInfo(transList, outputStream, trans);
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

    @RequiresAuthentication
    @RequestMapping(value = "agent")
    public String goToAgentLoginPage(HttpServletRequest request) {
        MemberFeeProfitForm memberFeeProfitForm = new MemberFeeProfitForm();

        int pageCurrent = 1;
        int pageSize = 100;

        TblMemberFeeProfitExample TblMemberFeeProfitExample = new TblMemberFeeProfitExample();
        TblMemberFeeProfitExample.createCriteria();
        int transSize = tblMemberFeeProfitMapper.countByExample(TblMemberFeeProfitExample);

        Pagination<TblMemberFeeProfit> pagination = new Pagination<TblMemberFeeProfit>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblMemberFeeProfit> tblRouteControlList = tblMemberFeeProfitMapper.selectByExample(TblMemberFeeProfitExample);

        pagination.addResult(tblRouteControlList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("profit", memberFeeProfitForm);

        return "profit/agent_info";
    }

    @RequestMapping("instsearch")
    @RequiresAuthentication
    public String doInstSelect(@ModelAttribute("instForm") InstProfitInfoForm instForm, HttpSession session,
                               HttpServletRequest request, HttpServletResponse response) {
        String instId = null;
        String startDate = null;
        String endDate = null;
        String transType = null;

        if (!StringUtils.isBlank(instForm.getInstId())) {
            instId = instForm.getInstId();
        }

        if (!StringUtils.isBlank(instForm.getStartDate())) {
            startDate = instForm.getStartDate();
        }

        if (!StringUtils.isBlank(instForm.getEndDate())) {
            endDate = instForm.getEndDate();
        }

        if (!StringUtils.isBlank(instForm.getTransType())) {
            transType = instForm.getTransType();
        }

        int pageCurrent = Integer.parseInt(instForm.getPageCurrent());
        int pageSize = Integer.parseInt(instForm.getPageSize());

        int transSize = tblInstProfitInfoMapper.countByCust(instId, startDate, endDate, transType);

        Pagination<TblInstProfitInfo> pagination = new Pagination<TblInstProfitInfo>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblInstProfitInfo> tblInstProfitInfoList = tblInstProfitInfoMapper.selectByCust(instId, startDate, endDate, transType);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        request.setAttribute("tblBtsInstDOList", tblBtsInstDOList);
        pagination.addResult(tblInstProfitInfoList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("profit", instForm);

        return "profit/inst_info";
    }

    @RequestMapping("agentsearch")
    @RequiresAuthentication
    public String doAgentSelect(@ModelAttribute("profitForm") MemberFeeProfitForm profitForm, HttpSession session,
                                HttpServletRequest request, HttpServletResponse response) {
        String memberId = null;
        String startDate = null;
        String endDate = null;
        String transType = null;

        if (!StringUtils.isBlank(profitForm.getMemberId())) {
            memberId = profitForm.getMemberId();
        }

        if (!StringUtils.isBlank(profitForm.getStartDate())) {
            startDate = profitForm.getStartDate();
        }

        if (!StringUtils.isBlank(profitForm.getEndDate())) {
            endDate = profitForm.getEndDate();
        }
        if (!StringUtils.isBlank(profitForm.getTransType())) {
            transType = profitForm.getTransType();
        }

        int pageCurrent = Integer.parseInt(profitForm.getPageCurrent());
        int pageSize = Integer.parseInt(profitForm.getPageSize());

        int transSize = tblMemberFeeProfitMapper.countByCust(memberId, startDate, endDate, transType);

        Pagination<TblMemberFeeProfit> pagination = new Pagination<TblMemberFeeProfit>(transSize, pageCurrent, pageSize);
        PageHelper.startPage(pageCurrent, pageSize);

        List<TblMemberFeeProfit> tblMemberFeeProfitList = tblMemberFeeProfitMapper.selectByCust(memberId, startDate, endDate, transType);

        pagination.addResult(tblMemberFeeProfitList);
        request.setAttribute("pageUser", pagination);
        request.setAttribute("profit", profitForm);

        return "profit/agent_info";
    }

}
