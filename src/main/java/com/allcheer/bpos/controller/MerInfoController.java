package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.Minsheng.EnterRepuest;
import com.allcheer.bpos.domain.Minsheng.EnterResponse;
import com.allcheer.bpos.domain.SelectOptionVO;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.vo.MerDetailInfo;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.MerBankInfoForm;
import com.allcheer.bpos.form.MerFeeForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.MerQrcodeForm;
import com.allcheer.bpos.mapper.TblBankInfoMapper;
import com.allcheer.bpos.mapper.TblMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerQrcodeMapper;
import com.allcheer.bpos.service.*;
import com.allcheer.bpos.service.impl.WechatRegisterServiceImpl;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.ParamUtil;
import com.allcheer.bpos.util.StringUtils;
import com.allcheer.bpos.util.TimeUtil;
import com.allcheer.bpos.util.ValidCertID;
import com.allcheer.bpos.util.constant.CommonConstants;
import com.google.common.base.Strings;
import org.apache.commons.beanutils.BeanUtils;
import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.noneDSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.*;

/**
 * Created by fireWorks on 2016/10/25.
 * <p>
 * 机构商户查询
 */
@Controller
@RequestMapping(value = "/mer")
public class MerInfoController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(MerInfoController.class);

    @Autowired
    private Validator validator;

    @Autowired
    private MerChannelPreService merChannelPreService;

    @Autowired
    private MerchInfoService merchInfoService;

    @Autowired
    private LocalService localService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private WechatRegisterService wechatRegisterService;

    @Autowired
    private TblMerQrcodeMapper tblMerQrcodeMapper;

    @Autowired
    QrcodeService qrcodeService;
    
	@Autowired
	AddressService addressService;
	
    @Autowired
    MerAgentService merAgentService;
    
    @Autowired
    TblBankInfoMapper BankInfoMapper;
    

    @Autowired
    private TblMerInfoDOMapper tblMerInfoDOMapper;

    @RequestMapping(value = "/get_mer_info")
    public String goToMerInfoPage(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(merInfoForm);

        List<TblBtsInstDO> tblBtsInstDOList = institutionService.getALLInst();
        Pagination<TblMerInfoVO> tblMerInfoVOPagination = merChannelPreService.queryMerInfoList(queryMap);
        merInfoForm.setPagination(tblMerInfoVOPagination);
        merInfoForm.setInstDOList(tblBtsInstDOList);

        return "/mer/mer_maintenance_list";
    }
        
    @RequestMapping(value = "/query_mer_detail")
    public ModelAndView queryMerDetail(ModelAndView modelAndView, HttpServletRequest request,
                                       @ModelAttribute("merFeeForm") MerFeeForm merFeeForm) {
        String merId = request.getParameter("id");
        MerDetailInfo merDetailInfo = merchInfoService.queryMerDetailInfo(merId);
        List<TblMerFileInfoDO> tblMerFileInfoDOList = merchInfoService.getMerPhotosFileInfo(merId);
        modelAndView.addObject("merInfo", merDetailInfo);
        modelAndView.setViewName("/mer/mer_detail_info");
        modelAndView.addObject("tblMerFileInfoDOList", tblMerFileInfoDOList);

        return modelAndView;
    }


    @RequestMapping(value = "/query_general_mer_detail")
    public ModelAndView queryGeneralMerDetail(ModelAndView modelAndView, HttpServletRequest request) {
        String merId = request.getParameter("id");
        List<TblMerFileInfoDO> emptyList = new ArrayList<TblMerFileInfoDO>();
        MerDetailInfo emptyMerDetailInfo = new MerDetailInfo();
        if (Strings.isNullOrEmpty(merId)) {
            modelAndView.setViewName("/mer/general_mer_detail_info");
            modelAndView.addObject("merInfo", emptyMerDetailInfo);
            modelAndView.addObject("tblMerFileInfoDOList", emptyList);

            return modelAndView;
        }

        MerDetailInfo merDetailInfo = merchInfoService.queryMerDetailInfo(merId);
        List<TblMerFileInfoDO> tblMerFileInfoDOList = merchInfoService.getMerPhotosFileInfo(merId);
        modelAndView.addObject("merInfo", merDetailInfo);
        modelAndView.setViewName("/mer/general_mer_detail_info");
        modelAndView.addObject("tblMerFileInfoDOList", tblMerFileInfoDOList);

        return modelAndView;
    }


    @RequestMapping(value = "/query_mer_bank", method = RequestMethod.GET)
    public String queryMerBank(@ModelAttribute("merBankInfoForm") MerBankInfoForm merBankInfoForm,
                               HttpServletRequest request) {
        String merId = request.getParameter("id");
        merBankInfoForm.setMerId(merId);
        merBankInfoForm = merchInfoService.getMerBankInfo(merBankInfoForm);

        return "/mer/update_mer_settle";
    }

    @RequestMapping(value = "/query_mer_fee", method = RequestMethod.GET)
    public String queryMerFee(@ModelAttribute("merFeeForm") MerFeeForm merFeeForm, HttpServletRequest request) {
        String merId = request.getParameter("id");
        merFeeForm.setMerId(merId);
        merFeeForm = merchInfoService.getMerfee(merFeeForm);

        return "/mer/update_mer_fee";
    }

    @ResponseBody
    @RequestMapping(value = "/update_mer_bank", method = RequestMethod.POST)
    public Map<String, String> updateMerBank(HttpServletRequest request, HttpServletResponse response,
                                             @ModelAttribute("merBankInfoForm") MerBankInfoForm merBankInfoForm) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            resultMap = merchInfoService.updateMerBankInfo(merBankInfoForm);
            if (resultMap != null) {
                if ("200".equals(resultMap.get("statusCode"))) {
                    wechatRegisterService.registerForHanyin(merBankInfoForm.getMerId(), WechatRegisterServiceImpl.HAN_YIN_CHANNEL, CommonConstants.OPER_FLAG_M02);
                    logger.info("========修改进件结束======");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMap.put("statusCode", "300");
            resultMap.put("message", "结算信息更新失败");
        }

        return resultMap;

    }

    @ResponseBody
    @RequestMapping(value = "/update_mer_fee", method = RequestMethod.POST)
    public Map<String, String> updateMerFee(HttpServletRequest request, HttpServletResponse response,
                                            @ModelAttribute("merFeeForm") MerFeeForm merFeeForm) {
        Map<String, String> resultMap = new HashMap<>();

        try {
            resultMap = merchInfoService.updateMerfee(merFeeForm);
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMap.put("statusCode", "300");
            resultMap.put("message", "费率更新失败");
        }
        return resultMap;
    }

    @RequestMapping(value = "/query_city_by_prov", method = RequestMethod.POST)
    @ResponseBody
    public Object getCityByProv(@RequestParam(value = "provinceId", required = false) String provinceId,
                                HttpServletRequest request) {

        List<TblCityDO> cityList = localService.getCityList(provinceId);

        List<SelectOptionVO> resp = new ArrayList<>();
        for (TblCityDO city : cityList) {
            SelectOptionVO selectOptionVo = new SelectOptionVO();
            selectOptionVo.setLabel(city.getCityName());
            selectOptionVo.setValue(city.getCityId());
            resp.add(selectOptionVo);
        }

        return resp;
    }

    @RequestMapping(value = "/query_images")
    public String queryImages(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");

        String src = request.getParameter("src");

        FileInputStream fis = new FileInputStream(src);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
        return null;
    }

    /**
     * 获取商户二维码信息
     * @param request
     * @param response
     * @param modelAndView
     * @param merQrcodeForm
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/query_qrcode_info")
    public ModelAndView getMerQrcode(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView,
                                     @ModelAttribute("merQrcodeForm") MerQrcodeForm merQrcodeForm) throws IOException {

        String merId = request.getParameter("merId");
        TblMerInfoDO merInfoDO = tblMerInfoDOMapper.selectByPrimaryKey(merId);
        if (merInfoDO != null) {
            merQrcodeForm.setMerName(merInfoDO.getMerName());
        }

        //机构,代理商检查
        Map<String, String> instAgentMap = merchInfoService.InstOrAgent(merId);
        if (instAgentMap != null) {
            if (instAgentMap.containsKey("INSTMER")) {
                merQrcodeForm.setInstId(instAgentMap.get("INSTID"));
                merQrcodeForm.setTermId(instAgentMap.get("TERMID"));
            }
            if (instAgentMap.containsKey("AGENTMER")) {
                merQrcodeForm.setAgentId(instAgentMap.get("AGENTID"));
                merQrcodeForm.setTermId(instAgentMap.get("TERMID"));
            }
        }

        TblMerQrcode tblMerQrcode = tblMerQrcodeMapper.selectByPrimaryKey(merId);
        if (tblMerQrcode != null) {
            merQrcodeForm.setQrcode(tblMerQrcode.getQrcode());
            merQrcodeForm.setQrcodePath(tblMerQrcode.getQrcodePath());
        }
        merQrcodeForm.setMerId(merId);
        merQrcodeForm.setSrcPath("/Bpos/mer/show_mer_qrcode?merId=" + merId + "?" + TimeUtil.getCurrentLineTime());
        modelAndView.addObject(merQrcodeForm);
        modelAndView.setViewName("/mer/get_mer_qrcode");

        return modelAndView;
    }


    /**
     * 获取商户二维码信息
     * @param request
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/show_mer_qrcode")
    public String queryMerQrcode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String merId = request.getParameter("merId").split("\\?")[0];
        TblMerQrcode tblMerQrcode = tblMerQrcodeMapper.selectByPrimaryKey(merId);
        String qrcodePath = "";

        if (tblMerQrcode == null) {
            qrcodePath = SystemConstant.QRCODE_NO_FOUND;
        } else {
            qrcodePath = tblMerQrcode.getQrcodePath();
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(qrcodePath);
        } catch (FileNotFoundException e1) {
            fis = new FileInputStream(SystemConstant.QRCODE_NO_FOUND);
        }

        OutputStream os = response.getOutputStream();

        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }

        return null;
    }

    /**
     * 获取商户二维码信息
     * @param request
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/show_mer_qrcode", method = RequestMethod.POST)
    public String queryMerQrcodePost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String merId = request.getParameter("merId").split("\\?")[0];
        TblMerQrcode tblMerQrcode = tblMerQrcodeMapper.selectByPrimaryKey(merId);
        String qrcodePath = "";

        if (tblMerQrcode == null) {
            qrcodePath = SystemConstant.QRCODE_NO_FOUND;
        } else {
            qrcodePath = tblMerQrcode.getQrcodePath();
        }

        response.setContentType("text/html; charset=UTF-8");
        response.setContentType("image/jpeg");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(qrcodePath);
        } catch (FileNotFoundException e1) {
            fis = new FileInputStream(SystemConstant.QRCODE_NO_FOUND);
        }

        OutputStream os = response.getOutputStream();

        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
        return null;
    }

    /**
     * 生成商户二维码信息
     * @param merQrcodeForm
     * @return
     * @throws IOException
     */

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/get_mer_qrcode", method = RequestMethod.POST)
    @ResponseBody
    public Map createMerQrcode(@ModelAttribute("merQrcodeForm") MerQrcodeForm merQrcodeForm) throws IOException {

        Map resultMap = new HashMap();

        String createOrNot = SystemConstant.TO_CREATE_QRCODE;
        Boolean toCreate = false;
        if (StringUtils.isNotBlank(createOrNot) && createOrNot.equals("Y")) {
            toCreate = true;
        }

        if (!toCreate) {
            resultMap.put("status", 300);
            resultMap.put("message", "二维码生成功能未打开");
            return resultMap;
        }

        String merId = merQrcodeForm.getMerId();
        String instId = merQrcodeForm.getInstId();
        String agentId = merQrcodeForm.getAgentId();
        String termId = merQrcodeForm.getTermId();
        //为商户生成二维码图片
        logger.info("生成商户二维码信息表");

        Map<String, String> qrcodeMap = new HashMap<String, String>();
        qrcodeMap = qrcodeService.QrcodeCreate(instId, agentId, merId, termId);

        if (qrcodeMap != null && qrcodeMap.containsKey("imageLocation")) {
            String qrcodeImagePath = qrcodeMap.get("imageLocation");
            Boolean status = qrcodeService.QrcodeSaving(merId, instId, agentId, null, qrcodeImagePath);
            if (!status) {
                resultMap.put("statusCode", 300);
                resultMap.put("message", "保存二维码失败");
            } else {
                resultMap.put("statusCode", 200);
                resultMap.put("message", "保存二维码成功");
            }
        } else {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "生成二维码失败");
        }

        return resultMap;
    }


    /**
     * 商户入驻接口
     *
     * @param response
     * @param request
     * @return
     */
    @RequestMapping(value = "/enter")
    public void acceptMerInfo(@RequestBody String xml, HttpServletResponse response, HttpServletRequest request,
                              BindingResult result) {
        String respMsg = "";
        String errorValidatMessage = "";
        
        try {
            logger.info("BPOS商户入驻:收到商户入驻请求");
        
            String cryptMessage = request.getParameter("sText");
            if (StringUtils.isEmpty(cryptMessage))
                throw new NotifyException(ErrorRespEnum.RESP002035, "传入的参数为空");
            
            String plainRequest = decryptRequest(cryptMessage);
            if (StringUtils.isEmpty(plainRequest))
                throw new NotifyException(ErrorRespEnum.RESP002035, "解密传入参数失败");

            logger.info("BPOS商户入驻:验证请求"); 
            doParamCheck(plainRequest);
            
            Map<String, String> reqMap = ParamUtil.getParamMap(plainRequest);
               
            EnterRepuest req = new EnterRepuest();
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

            logger.info("校验地址编码");
    		String provinceCode = req.getMerProvince();
    		String cityCode = req.getMerCity();
    		String areaCode = req.getMerArea();
    		Map<String, String> addressMap = addressService.getCorrectAddressCodes(provinceCode, cityCode, areaCode);
    		if(addressMap != null && addressMap.containsKey("ERROR")){
    			throw new NotifyException(ErrorRespEnum.RESP099999, addressMap.get("ERROR"));
    		}	
    		req.setMerProvince(addressMap.get("PROVINCE"));
    		req.setMerCity(addressMap.get("CITY"));
    		req.setMerArea(addressMap.get("AREA"));
    		
            logger.debug("BPOS商户入驻: 判断机构是否入驻");
            Boolean isExisted1 = merchInfoService.isInstExist(req.getInsNum());
            if (!isExisted1) {
                throw new NotifyException(ErrorRespEnum.RESP002035, "该机构号不存在");
            }           
            
            logger.debug("BPOS商户入驻: 判断商户是否已经报备");
            String isExisted2 = merchInfoService.merExisted(req);
            if (StringUtils.isNotBlank(isExisted2)) {
                throw new NotifyException(ErrorRespEnum.RESP002035, isExisted2);
            }
            
            logger.debug("BPOS商户入驻: 入驻商户");
            Map<String, String> merResult = merchInfoService.addMerInfo(req);
            
            EnterResponse resp = new EnterResponse();
            resp.setInsNum(req.getInsNum());
            resp.setMerNum(merResult.get("MERID"));
            resp.setRspCode("000000");
            if("03".equals(reqMap.get("payType")) || "04".equals(reqMap.get("payType")) || "99".equals(reqMap.get("payType"))){
            	resp.setTermId(merResult.get("TERMID"));
            }
                 
            JAXBContext context = JAXBContext.newInstance(EnterResponse.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            OutputStream output = new ByteArrayOutputStream();
            marshaller.marshal(resp, output);
            respMsg = output.toString();
        } catch (Exception ex) {
            logger.error("BPOS商户入驻发生异常: ", ex);
            if (ex instanceof NotifyException)
                respMsg = this.buildErrorResp((NotifyException) ex);
            else
                respMsg = this.buildErrorResp(new NotifyException(ErrorRespEnum.RESP009998, "系统错误"));
        }

        logger.debug("step 4: 返回应答");
        super.getJSONFromList(response, respMsg);

    }

    /**
     * 商户入驻参数校验
     */
    @Override
    protected void doParamCheck(String plainRequest) {
    	
    	 Map<String, String> reqMap = ParamUtil.getParamMap(plainRequest);
    	 //支付类型
    	 String payType = reqMap.get("payType");
    	 
    	 //商户类型
    	 String merType = reqMap.get("merType");
    	 
    	 String errorMessage = "";
    	 
    	 if(StringUtils.isBlank(payType)){
    		 errorMessage += "【支付类型不能为空】";	 
    	 } else if(!payType.equals("01") && !payType.equals("02") && !payType.equals("03") && !payType.equals("04") && !payType.equals("05") && !payType.equals("99")){
    		 errorMessage += "【支付类型错误, 只能为01,02,03,04,05或者99";
    	 }
    	 
    	 if(StringUtils.isBlank(merType)){
    		 errorMessage += "【商户类型不能为空】";
    	 }  else if(!merType.equals("0") && !merType.equals("1") && !merType.equals("2")){
    		 errorMessage += "【商户类型错误, 只能为0,1或者2】";
    	 }    	 
    	 
    	 //验证身份证是否正确
    	 String legalPersonCertNum = reqMap.get("legalPersonCertNm");
    	 Boolean certValid = ValidCertID.isIdNum(legalPersonCertNum);
    	 if(!certValid){
    		 errorMessage += "【商户身份证信息错误】";
    	 }
    	 
		// 验证填写的银行信息
		String bankName = reqMap.get("bankName");
		String bankBranchNumber = reqMap.get("debitCardLines");
		String verifyBankResult = merchInfoService.verifyBankInfo(bankName, bankBranchNumber, payType,
				reqMap.get("debitCardNum"));
		if (StringUtils.isNotBlank(verifyBankResult)) {
			errorMessage += verifyBankResult;
		}
    	 
    	 String WXT0 = reqMap.get("WXT0");
    	 String WXT1 = reqMap.get("WXT1");
    	 String ZFBT0 = reqMap.get("ZFBT0");
    	 String ZFBT1 = reqMap.get("ZFBT1");
    	 //二维码支付类型时, 费率不能空
    	 if (payType.equals("01") || payType.equals("02") || payType.equals("04") || payType.equals("99")){
    		 
    		 if(StringUtils.isBlank(WXT0) || StringUtils.isBlank(WXT1) || StringUtils.isBlank(ZFBT0) || StringUtils.isBlank(ZFBT1)){
    			 errorMessage += "【微信费率,支付宝费率不能为空】";
    		 }		 
    		 Double nWXTO = Double.parseDouble(WXT0);
    		 if(nWXTO == 0){
    			 errorMessage += "【微信费率T0费率不能为0】";
    		 }
    		 Double nWXT1 = Double.parseDouble(WXT1);
    		 if(nWXT1 == 0){
    			 errorMessage += "【微信费率T1费率不能为0】";
    		 }
    		 Double nZFBTO = Double.parseDouble(ZFBT0);
    		 if(nZFBTO == 0){
    			 errorMessage += "【支付宝费率T0费率不能为0】";
    		 }
    		 Double nZFBT1 = Double.parseDouble(ZFBT1);
    		 if(nZFBT1 == 0){
    			 errorMessage += "【支付宝费率T1费率不能为0】";
    		 } 		 
    	 }
    	 
    	 String debitCardFee = reqMap.get("debitCardFee");
    	 String debitCardMax = reqMap.get("debitCardMax");
    	 String creditCardFee = reqMap.get("creditCardFee");
    	 
    	 //POS交易支付
    	 if (payType.equals("03") || payType.equals("04") || payType.equals("99")){
    		 
    		 if(StringUtils.isBlank(debitCardFee) || StringUtils.isBlank(debitCardMax) || StringUtils.isBlank(creditCardFee)){
    			 errorMessage += "【POS交易借记卡,借记卡上限,贷记卡费率不能为空】";
    		 }	   		 	 
    	 }

    	 String qcPayFeeT1 = reqMap.get("qcPayFeeT1");
    	 String qcPayFeeRateT1 = reqMap.get("qcPayFeeRateT1");
    	 String qcWithDrawFeeT0 = reqMap.get("qcWithDrawFeeT0");
    	 String qcWithDrawFeeRateT0 = reqMap.get("qcWithDrawFeeRateT0");
    	 
    	 //快捷支付类型
    	 if (payType.equals("05") || payType.equals("99")){
    		 
    		 if(StringUtils.isBlank(qcPayFeeT1) || StringUtils.isBlank(qcPayFeeRateT1)){ 
    			 errorMessage += "【快捷支付-交易手续费每笔/费率不能为空】";
    		 }
			 if(StringUtils.isBlank(qcWithDrawFeeT0) || StringUtils.isBlank(qcWithDrawFeeRateT0)){	
				 errorMessage += "【快捷支付-提现手续费每笔/费率不能为空】";
    		 }   		 	 
    	 } 

    	 //快捷支付类型  - 结算卡必须跟法人姓名一致
    	 if (payType.equals("05") || payType.equals("99")){
    		 String legalPerson = reqMap.get("legalPerson");
    		 String acctName = reqMap.get("debitCardName");
    		 if(!legalPerson.equals(acctName)){
    			 errorMessage += "【快捷支付-法人姓名跟结算卡姓名不一致】"; 
    		 }
    	 }
    	 
    	 //敏感词
    	 String merName = reqMap.get("merName");
		 if(merName.indexOf("投资") > 0  || merName.indexOf("赌") > 0){
			 errorMessage += "【商户名称含有敏感词, 请重新填写】";
		 }     	 
 
    	 //敏感词
		 String regShortName = reqMap.get("regShortName");
		 if(regShortName.indexOf("投资") > 0  || regShortName.indexOf("赌") > 0){
			 errorMessage += "【商户简称含有敏感词, 请重新填写】";
		 }   		 
    	 
    	 //如果注册类型为公司和有执照个人, 则营业执照信息不能为空
    	 //如果无执照个人, 则简称中不能出现公司, 家乐福, 肯德基等字样
    	 if(merType.equals("0") || merType.equals("1")){
    		 if(!reqMap.containsKey("busLincenNumber") || StringUtils.isBlank(reqMap.get("busLincenNumber"))){
    			 errorMessage += "【公司类型或者有执照个体户营业执照不能为空】";
    		 };
    		 if(!reqMap.containsKey("busLincenExpireDate") || StringUtils.isBlank(reqMap.get("busLincenExpireDate"))){
    			 errorMessage += "【公司类型或者有执照个体户营业执照有效期不能为空】";
    		 };
    	 } else if (merType.equals("2")) {
    		 if(regShortName.indexOf("公司") > 0  || regShortName.indexOf("家乐福") > 0 || regShortName.indexOf("肯德基") > 0 || regShortName.indexOf("星巴克") > 0){
    			 errorMessage += "【个人无执照类型,简称中不能出现公司和大品牌企业名】";
    		 } 
    	 }   
     	 
    	 //对于POS入件,验证是否录入终端信息
    	 if(merType.equals("3") || merType.equals("4") || merType.equals("99")){
    		 String outTermNo = reqMap.get("outTermNo");
    		 String outTermName = reqMap.get("outTermName");
    		 String outTermType = reqMap.get("outTermType");
    		 String outTermProv = reqMap.get("outTermProv");
    		 String outTermCity = reqMap.get("outTermCity");
    		 String outTermArea = reqMap.get("outTermArea");
    		 String outTermAddress = reqMap.get("outTermAddress");
    		 String outTermSN = reqMap.get("outTermSN");
  
    		 if(StringUtils.isBlank(outTermNo)){
    			 errorMessage += "【POS入件-终端号不能为空】"; 
    		 }
    		 if(StringUtils.isBlank(outTermName)){
    			 errorMessage += "【POS入件-终端名称不能为空】"; 
    		 }
    		 if(StringUtils.isBlank(outTermType)){
    			 errorMessage += "【POS入件-终端类型不能为空】"; 
    		 }
    		 if(StringUtils.isBlank(outTermProv)){
    			 errorMessage += "【POS入件-终端号安装省不能为空】"; 
    		 }
    		 if(StringUtils.isBlank(outTermCity)){
    			 errorMessage += "【POS入件-终端号安装城市不能为空】"; 
    		 }
    		 if(StringUtils.isBlank(outTermArea)){
    			 errorMessage += "【POS入件-终端号安装县区】"; 
    		 }
    		 if(StringUtils.isBlank(outTermAddress)){
    			 errorMessage += "【POS入件-终端号安装详细地址】"; 
    		 }
    		 if(StringUtils.isBlank(outTermSN)){
    			 errorMessage += "【POS入件-终端号SN号不能为空】"; 
    		 }
             if(!outTermType.equals("0") && !outTermType.equals("1") && !outTermType.equals("02")){
            	 errorMessage += "【POS入件-终端号类型不正确,只能为0,1,2】"; 
             }
    	 }
    	 
    	 if (StringUtils.isNotBlank(errorMessage)){
    		 throw new NotifyException(ErrorRespEnum.RESP099999,  errorMessage);
    	 }
    		 
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
