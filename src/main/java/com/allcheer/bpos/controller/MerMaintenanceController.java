package com.allcheer.bpos.controller;

import com.allcheer.bpos.check.entity.constant.InstMerFileTypeEnum;
import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.Minsheng.IntoPicAddressRepuest;
import com.allcheer.bpos.domain.Minsheng.IntoPicAddressResponse;
import com.allcheer.bpos.domain.Minsheng.IntoPiecesRepuest;
import com.allcheer.bpos.domain.Minsheng.IntoPiecesResponse;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblInstMerAddBatchInfoDO;
import com.allcheer.bpos.entity.TblInstMerAddBatchInfoDOExample;
import com.allcheer.bpos.entity.TblInstMerAddBatchInfoDOExample.Criteria;
import com.allcheer.bpos.entity.TblInstMerAddDetailInfoDO;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.AddInstMerForm;
import com.allcheer.bpos.form.InstBatchResultForm;
import com.allcheer.bpos.form.MerChannelPreResultForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.mapper.TblInstMerAddBatchInfoDOMapper;
import com.allcheer.bpos.service.AddInstMerService;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.MerChannelPreService;
import com.allcheer.bpos.service.QianBaoService;
import com.allcheer.bpos.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.*;

/**
 * Created by APPLE on 16/10/18.
 */
@Controller
@RequestMapping(value = "/mer")
public class MerMaintenanceController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MerMaintenanceController.class);

    @Autowired
    private Validator validator;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private  AddInstMerService addInstMerService;

    @Autowired
    private MerChannelPreService merChannelPreService;

    @Autowired
    private QianBaoService qianBaoService;

    @Autowired
    private TblInstMerAddBatchInfoDOMapper tblInstMerAddBatchInfoDOMapper;


    @RequestMapping(value = "/add_inst_mer", method = RequestMethod.GET)
    public ModelAndView goToAddInstMerPage(ModelAndView modelAndView) {
        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
        modelAndView.setViewName("/mer/add_inst_mer");

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/upload_inst_mer_file")
    public Map uploadInstMerFile(HttpServletRequest request) {
        Map resultMap = new HashMap<>();
        String filePath = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        String fileName = multipartFile.getOriginalFilename();

        File targetFile = new File(SystemConstant.ADD_INST_MER_FILE_PATH + DateUtil.getCurrentDate(), fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "已存在同名文件!");
            return resultMap;
        }
        try {
            multipartFile.transferTo(targetFile);
            filePath = targetFile.getPath();
        } catch (Exception e) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "文件上传失败!");
            return resultMap;
        }
        resultMap.put("statusCode", 200);
        resultMap.put("message", "文件上传成功!");
        resultMap.put("filename", fileName);
        resultMap.put("filePath", filePath);
        return resultMap;
    }

    @RequestMapping(value = "/add_inst_mer", method = RequestMethod.POST)
    public ModelAndView addInstMer(@ModelAttribute("addInstMerForm") AddInstMerForm addInstMerForm, HttpSession session,
                                   ModelAndView modelAndView) {
        String instId = addInstMerForm.getInstId();
        String fileType = addInstMerForm.getFileType();
        String uploadFileName = addInstMerForm.getUploadFileName();
        String uploadFile = addInstMerForm.getUploadFile();
        UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        String batchNo;
        if (InstMerFileTypeEnum.DATA.getCode().equals(fileType)) {
            batchNo = addInstMerService.addInstMerData(instId, uploadFile, userName);
        } else {
            batchNo = addInstMerService.addInstMerAttachment(instId, uploadFile, uploadFileName, userName);
        }

        modelAndView.getModel().put("tblInstMerAddBatchInfoDO",
                addInstMerService.selectTblInstMerAddBatchInfoDOByPk(batchNo));
        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
        modelAndView.setViewName("/mer/add_inst_mer");

        return modelAndView;
    }

    @RequestMapping(value = "/get_mer_batch_file", method = RequestMethod.GET)
    public String getSetCheckFile(@RequestParam("batchFile") String batchFile,
                                  HttpServletResponse httpServletResponse) {
        String strFileName = "";
        try {
            strFileName = new String(batchFile.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        File file = new File(strFileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            String fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName);
            byte[] buffer = new byte[4 * 1024];
            int size;
            while ((size = fileInputStream.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, size);
            }
        } catch (Exception e) {
            logger.error("获取商户批处理文件异常{}", e);
        }
        return null;
    }

    @RequestMapping(value = "/import_mer_result_detail", method = RequestMethod.GET)
    public ModelAndView setAutoCheckResultDetail(@RequestParam("batchNo") String batchNo, ModelAndView modelAndView) {
        modelAndView.setViewName("/mer/import_mer_result_detail");
        List<TblInstMerAddDetailInfoDO> tblInstMerAddDetailInfoDOList = addInstMerService
                .selectTblInstMerAddDetailInfoDOByBatchNo(batchNo);
        modelAndView.addObject("resultList", tblInstMerAddDetailInfoDOList);

        return modelAndView;
    }

    @RequestMapping(value = "/import_mer_result_file", method = RequestMethod.GET)
    public String newExportRuiShengSetFile(@RequestParam("resultFilePath") String resultFilePath,
                                           HttpServletResponse httpServletResponse) {
        File file = new File(resultFilePath);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            String fileName = file.getName();
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName);
            byte[] buffer = new byte[4 * 1024];
            int size;
            while ((size = fileInputStream.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, size);
            }
        } catch (Exception e) {
            logger.error("获取商户批处理结果文件异常{}", e);
        }
        return null;
    }

    @RequestMapping(value = "/mer_info", method = RequestMethod.GET)
    public ModelAndView goToMerInfoPage(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
                                        ModelAndView modelAndView) {
        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
        modelAndView.setViewName("/mer/mer_info");

        return modelAndView;
    }

    @RequestMapping(value = "/query_mer_info", method = RequestMethod.POST)
    public ModelAndView queryMerInfo(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
                                     ModelAndView modelAndView) {
        Map conditionMap = new HashMap();

        if (StringUtils.isNotBlank(merInfoForm.getInstId())) {
            conditionMap.put("instId", merInfoForm.getInstId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getInstMerId())) {
            conditionMap.put("instMerId", merInfoForm.getInstMerId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getInstTermId())) {
            conditionMap.put("instTermId", merInfoForm.getInstTermId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getMerId())) {
            conditionMap.put("merId", merInfoForm.getMerId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getMerName())) {
            conditionMap.put("merName", merInfoForm.getMerName());
        }

        if (StringUtils.isNotBlank(merInfoForm.getMerStat())) {
            conditionMap.put("merStat", merInfoForm.getMerStat());
        }

        if (StringUtils.isNotBlank(merInfoForm.getStartTime())) {
            conditionMap.put("startTime", DateUtil.stringTostring(merInfoForm.getStartTime(), DateUtil.PATTERN_STANDARD,
                    DateUtil.PATTERN_TIME_14));
        }

        if (StringUtils.isNotBlank(merInfoForm.getEndTime())) {
            conditionMap.put("endTime", DateUtil.stringTostring(merInfoForm.getEndTime(), DateUtil.PATTERN_STANDARD,
                    DateUtil.PATTERN_TIME_14));
        }

        conditionMap.put("pageCurrent", merInfoForm.getPageCurrent());
        conditionMap.put("pageSize", merInfoForm.getPageSize());

        Pagination<TblMerInfoVO> tblMerInfoVOPagination = merChannelPreService.queryMerInfoList(conditionMap);
        merInfoForm.setPagination(tblMerInfoVOPagination);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
        modelAndView.setViewName("/mer/mer_info");

        return modelAndView;
    }

    @RequestMapping(value = "/mer_channel_pre", method = RequestMethod.GET)
    public ModelAndView goToMerChannelPrePage(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm,
                                              ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        merInfoForm.setInstId(httpServletRequest.getParameter("instId"));
        merInfoForm.setInstMerId(httpServletRequest.getParameter("instMerId"));
        merInfoForm.setInstTermId(httpServletRequest.getParameter("instTermId"));
        merInfoForm.setMerId(httpServletRequest.getParameter("merId"));
        merInfoForm.setMerName(httpServletRequest.getParameter("merName"));
        merInfoForm.setStartTime(httpServletRequest.getParameter("startTime"));
        merInfoForm.setEndTime(httpServletRequest.getParameter("endTime"));
        modelAndView.setViewName("/mer/mer_channel_pre");

        return modelAndView;
    }

    @RequestMapping(value = "/mer_channel_pre", method = RequestMethod.POST)
    public String merChannelPre(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, HttpSession session,
                                HttpServletResponse httpServletResponse) {
        UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        String channelId = merInfoForm.getChannelId();
        Map conditionMap = new HashMap();

        conditionMap.put("userName", userName);

        if (StringUtils.isNotBlank(merInfoForm.getInstId())) {
            conditionMap.put("instId", merInfoForm.getInstId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getInstMerId())) {
            conditionMap.put("instMerId", merInfoForm.getInstMerId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getInstTermId())) {
            conditionMap.put("instTermId", merInfoForm.getInstTermId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getMerId())) {
            conditionMap.put("merId", merInfoForm.getMerId());
        }

        if (StringUtils.isNotBlank(merInfoForm.getMerName())) {
            conditionMap.put("merName", merInfoForm.getMerName());
        }

        if (StringUtils.isNotBlank(merInfoForm.getStartTime())) {
            conditionMap.put("startTime", DateUtil.stringTostring(merInfoForm.getStartTime(), DateUtil.PATTERN_STANDARD,
                    DateUtil.PATTERN_TIME_14));
        }
        if (StringUtils.isNotBlank(merInfoForm.getEndTime())) {
            conditionMap.put("endTime", DateUtil.stringTostring(merInfoForm.getEndTime(), DateUtil.PATTERN_STANDARD,
                    DateUtil.PATTERN_TIME_14));
        }
        conditionMap.put("merStat", Constant.AUDIT_REJECT);

        if (channelId.equals("U1")) {
            qianBaoService.downChannelPreFile(conditionMap, httpServletResponse);
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/upload_mer_channel_pre_result_file")
    public Map uploadMerChannelPreResultFile(HttpServletRequest request) {
        Map resultMap = new HashMap<>();
        String filePath = "";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");
        String fileName = multipartFile.getOriginalFilename();

        File targetFile = new File(SystemConstant.MER_CHANNEL_PRE_RESULT_PATH + DateUtil.getCurrentDate(), fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "已存在同名文件!");
            return resultMap;
        }
        try {
            multipartFile.transferTo(targetFile);
            filePath = targetFile.getPath();
        } catch (Exception e) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "文件上传失败!");
            return resultMap;
        }
        resultMap.put("statusCode", 200);
        resultMap.put("message", "文件上传成功!");
        resultMap.put("filename", fileName);
        resultMap.put("filePath", filePath);

        return resultMap;
    }

    @RequestMapping(value = "/mer_channel_pre_result", method = RequestMethod.GET)
    public ModelAndView goToMerChannelPreResultPage(ModelAndView modelAndView) {
        modelAndView.setViewName("/mer/mer_channel_pre_result");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/mer_channel_pre_result", method = RequestMethod.POST)
    public Map merChannelPreResult(
            @ModelAttribute("merChannelPreResultForm") MerChannelPreResultForm merChannelPreResultForm,
            HttpSession session, ModelAndView modelAndView) {
        Map resultMap = new HashMap<>();

        UserBO user = (UserBO) session.getAttribute(com.allcheer.bpos.util.SystemConstant.USER_SESSION_KEY);
        String userName = user.getUsrName();
        String channelId = merChannelPreResultForm.getChannelId();
        if (("U1").equals(channelId)) {
            String file = merChannelPreResultForm.getUploadFile();
            String fileName = merChannelPreResultForm.getUploadFileName();
            File resultFile = new File(file);
            resultMap = qianBaoService.resultUpdate(resultFile, userName);
        }

        return resultMap;
    }

    @RequestMapping(value = "/export_inst_mer_report")
    public String exportInstMerList(HttpServletRequest request,
                                    @ModelAttribute("merInfoForm") MerInfoForm merInfoForm, HttpServletResponse response)
            throws Exception {

        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(merInfoForm);
        List<TblMerInfoVO> tblMerInfoVOList = institutionService.queryInstMerList(queryMap);

        response.setContentType("application/binary;charset=ISO8859_1");

        try {
            String nowTime = DateUtil.date2String(new Date(), "yyyyMMddHHmmss");
            ServletOutputStream outputStream = response.getOutputStream();
            String fileName = new String(("机构商户信息表").getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + "_" + nowTime + ".xls");// 组装附件名称和格式
            institutionService.exportInstMerList(tblMerInfoVOList, outputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    @RequestMapping(value = "/forward_batch_history", method = RequestMethod.GET)
    public ModelAndView goInstBatchHistory(ModelAndView modelAndView, @ModelAttribute("instBatchResultForm") InstBatchResultForm instBatchResultForm) {
        logger.info("获取机构商户号");

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        modelAndView.getModel().put("tblBtsInstDOList", tblBtsInstDOList);
        modelAndView.setViewName("/mer/inst_batch_result_history");

        return modelAndView;
    }

    @RequestMapping(value = "/inst_batch_result_history")
    public ModelAndView queryBatchResultHistory(ModelAndView modelAndView, HttpServletRequest request,
                                                @ModelAttribute("instBatchResultForm") InstBatchResultForm instBatchResultForm,
                                                HttpServletResponse response) {
        Map returmMap = new HashMap();

        String instId = request.getParameter("instCode");
        if (StringUtils.isNotBlank(instId)) {
            List<TblInstMerAddBatchInfoDO> TblInstMerAddBatchInfoDOs = queryBatchNoList(request, instBatchResultForm);
            modelAndView.getModel().put("TblInstMerAddBatchInfoDO", TblInstMerAddBatchInfoDOs);
            modelAndView.addObject(TblInstMerAddBatchInfoDOs);
            modelAndView.setViewName("/mer/inst_batch_result_history");

            return modelAndView;
        }

        return modelAndView;
    }

    @RequestMapping(value = "/select_inst_batch_no")
    public ModelAndView queryInstBatchNo(ModelAndView modelAndView, HttpServletRequest request,
                                         @ModelAttribute("instBatchResultForm") InstBatchResultForm instBatchResultForm,
                                         HttpServletResponse response) {
        modelAndView.setViewName("/mer/select_inst_batch_no");

        return modelAndView;
    }


    /**
     * 获取机构进件历史的列表
     *
     * @param request
     * @param instBatchResultForm
     * @return
     */
    private List<TblInstMerAddBatchInfoDO> queryBatchNoList(HttpServletRequest request, InstBatchResultForm instBatchResultForm) {

        String instId = request.getParameter("instCode");
        String batchDate = instBatchResultForm.getBatchDate();
        if (StringUtils.isBlank(batchDate)) {
            batchDate = DateUtil.date2String(new Date(), "yyyyMMdd");
        }
        String userName = instBatchResultForm.getOperUser();

        TblInstMerAddBatchInfoDOExample tblInstMerAddBatchInfoDOExample = new TblInstMerAddBatchInfoDOExample();
        Criteria criteria = tblInstMerAddBatchInfoDOExample.createCriteria();
        criteria.andInstIdEqualTo(instId).andUpdateTimeLike("%" + batchDate + "%");

        if (StringUtils.isNotBlank(userName)) {
            criteria.andUserNameEqualTo(userName);
        }
        List<TblInstMerAddBatchInfoDO> TblInstMerAddBatchInfoDOs = tblInstMerAddBatchInfoDOMapper.selectByExample(tblInstMerAddBatchInfoDOExample);

        return TblInstMerAddBatchInfoDOs;
    }

    /**
     * 机构商户进件接口
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/intoPieces")
    public void instMerAddBatchInfo(@RequestBody String xml, HttpServletResponse response, HttpServletRequest request,
                                    BindingResult result) {
        String respMsg = "";
        String errorValidatMessage = "";
        try {
            logger.debug("收到商户入驻请求");
            logger.debug("step 1: 机构商户进件(webservice)解密请求");
            String cryptMessage = request.getParameter("sText");
            System.out.println("cryptMessage==" + cryptMessage);
            if (StringUtils.isEmpty(cryptMessage))
                throw new NotifyException(ErrorRespEnum.RESP002035, "传入的参数为空");
            String plainRequest = decryptRequest(cryptMessage);
            System.out.println("plainRequest==" + plainRequest);
            if (StringUtils.isEmpty(plainRequest))
                throw new NotifyException(ErrorRespEnum.RESP002035, "传入的参数为空");

            logger.debug("step2:机构商户进件(webservice) 验证请求");
            Map<String, String> reqMap = ParamUtil.getParamMap(plainRequest);
            IntoPiecesRepuest req = new IntoPiecesRepuest();
            BeanUtils.populate(req, reqMap);

            validator.validate(req, result);
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                Iterator<ObjectError> iterator = allErrors.iterator();
                ObjectError error = null;
                while (iterator.hasNext()) {
                    error = iterator.next();
                    errorValidatMessage = errorValidatMessage + "【" + error.getDefaultMessage() + "】";
                }
                throw new NotifyException(ErrorRespEnum.RESP002035, errorValidatMessage);
            }

            logger.debug("step3: 机构商户进件(webservice)业务逻辑处理");
            addInstMerService.instMerAddBatchInfo(req);
            IntoPiecesResponse resp = new IntoPiecesResponse();
            resp.setInsNum(req.getInstId());
            resp.setMerNum(req.getMerId());
            resp.setRspCode("000000");
            JAXBContext context = JAXBContext.newInstance(IntoPiecesResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            OutputStream output = new ByteArrayOutputStream();
            marshaller.marshal(resp, output);
            respMsg = output.toString();
        } catch (Exception ex) {
            logger.error("Wechat充值下单请求异常: ", ex);
            if (ex instanceof NotifyException)
                respMsg = this.buildErrorResp((NotifyException) ex);
            else
                respMsg = this.buildErrorResp(new NotifyException(ErrorRespEnum.RESP009998, "系统错误"));
        }

        logger.debug("step 4: 返回应答");
        super.getJSONFromList(response, respMsg);

    }

    /**
     * 机构商户图片信息接口
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/intoPicAddress")
    public void intoMerInfoPic(@RequestBody String xml, HttpServletResponse response, HttpServletRequest request,
                               BindingResult result) {
        String respMsg = "";
        String errorValidatMessage = "";
        try {
            logger.debug("收到商户入驻请求");
            logger.debug("step 1: 机构商户附件(webservice)解密请求");
            String cryptMessage = request.getParameter("sText");
            System.out.println("cryptMessage==" + cryptMessage);
            if (StringUtils.isEmpty(cryptMessage))
                throw new NotifyException(ErrorRespEnum.RESP002035, "传入的参数为空");
            String plainRequest = decryptRequest(cryptMessage);
            System.out.println("plainRequest==" + plainRequest);
            if (StringUtils.isEmpty(plainRequest))
                throw new NotifyException(ErrorRespEnum.RESP002035, "传入的参数为空");

            logger.debug("step2:机构商户附件(webservice) 验证请求");
            Map<String, String> reqMap = ParamUtil.getParamMap(plainRequest);
            IntoPicAddressRepuest req = new IntoPicAddressRepuest();
            BeanUtils.populate(req, reqMap);
            validator.validate(req, result);
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                Iterator<ObjectError> iterator = allErrors.iterator();
                ObjectError error = null;
                while (iterator.hasNext()) {
                    error = iterator.next();
                    errorValidatMessage = errorValidatMessage + "【" + error.getDefaultMessage() + "】";
                }
                throw new NotifyException(ErrorRespEnum.RESP002035, errorValidatMessage);
            }

            logger.debug("step3: 机构商户附件(webservice)业务逻辑处理");
            addInstMerService.instMerPicAddressInfo(req);
            IntoPicAddressResponse resp = new IntoPicAddressResponse();
            resp.setInsNum(req.getInstId());
            resp.setMerNum(req.getMerId());
            resp.setRspCode("000000");
            JAXBContext context = JAXBContext.newInstance(IntoPicAddressResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            OutputStream output = new ByteArrayOutputStream();
            marshaller.marshal(resp, output);
            respMsg = output.toString();
        } catch (Exception ex) {
            logger.error("Wechat充值下单请求异常: ", ex);
            if (ex instanceof NotifyException)
                respMsg = this.buildErrorResp((NotifyException) ex);
            else
                respMsg = this.buildErrorResp(new NotifyException(ErrorRespEnum.RESP009998, "系统错误"));
        }

        logger.debug("step 4: 返回应答");
        super.getJSONFromList(response, respMsg);

    }

    @Override
    protected void doParamCheck(String plainRequest) {
    }

    @Override
    protected String doBusiness(String plainRequest) {
        return null;
    }

    @Override
    protected String buildErrorResp(NotifyException notifyException) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<ROOT>");
        sb.append("<RSPCOD>").append(notifyException.getErrorResp().getRespCode()).append("</RSPCOD>");
        sb.append("<RSPMSG>").append(notifyException.getErrorMesg()).append("</RSPMSG>");
        sb.append("</ROOT>");

        logger.debug("返回报文: " + sb.toString());
        return sb.toString();
    }

    @Override
    protected String buildSuccessResp() {
        return null;
    }

    protected String buildSuccessResp(Map<String, String> respValues) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<ROOT>");
        sb.append(buildXMLBody(respValues));
        sb.append("</ROOT>");

        logger.debug("返回报文: " + sb.toString());
        return sb.toString();
    }

}
