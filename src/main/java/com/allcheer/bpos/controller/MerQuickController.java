package com.allcheer.bpos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblMerAuditRecordDO;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.vo.MerDetailInfo;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.form.QuickMerFeeForm;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.service.AddressService;
import com.allcheer.bpos.service.InstitutionService;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.QuickMerService;
import com.allcheer.bpos.util.CalcModeUtil;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;
import com.google.common.base.Strings;

/**
 * Lambert 2017
 */

@Transactional
@Controller
@RequestMapping(value = "/mer")

public class MerQuickController {

    private final static Logger logger = LoggerFactory.getLogger(MerQuickController.class);

    @Autowired
    private MerAgentService merAgentService;

    @Autowired
    private InstitutionService institutionService;
    
    @Autowired
    QuickMerService quickMerService;
    
    @Autowired
    MerchInfoService merchInfoService;
    
    @Autowired
    TblBtsInstDOMapper BtsInstDOMapper;
    
    @Autowired
    AddressService addressService;
    
    /**
     * 快捷支付商户列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/get_quick_mer_list")
    public String GetQuickMerList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {

        //获得机构
        List<TblBtsInstDO> BtsInstDOList = institutionService.getALLInst();
        
        //获得代理商列表
        List<TblAgentInfoDo> AgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());
           
        merInfoForm.setQuickMark("on");
        merInfoForm.setQuickStatus("Y");
        Pagination<TblMerInfoVO> QuickMerPagination = quickMerService.queryQuickMerList(merInfoForm);
        
        merInfoForm.setPagination(QuickMerPagination);
        merInfoForm.setInstDOList(BtsInstDOList);
        merInfoForm.setTblAgentInfoDos(AgentInfoDoList);

        return "/mer/mer_quick_list";
    }
    
    /**
     * 快捷支付商户列表
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/query_quick_mer_list", method = RequestMethod.POST)
    public ModelAndView QueryQuickMerList(@ModelAttribute("merInfoForm") MerInfoForm merInfoForm, ModelAndView modelAndView) {

        //获得机构
        List<TblBtsInstDO> BtsInstDOList = institutionService.getALLInst();
        
        //获得代理商列表
        List<TblAgentInfoDo> AgentInfoDoList = merAgentService.findAgentInfos(new TblAgentInfoDo());
          
        //机构和代理商不能同时选择
		String instId = merInfoForm.getInstId();
		String agentId = merInfoForm.getAgentId();	
		if(StringUtils.isNotBlank(instId) && StringUtils.isNotBlank(agentId)){
			modelAndView.getModel().put("statusCode", 300);
			modelAndView.getModel().put("message", "请选择机构或者代理商其中之一查询");
		}
		      
		merInfoForm.getQuickStatus();
		merInfoForm.getQuickMark();
       
        Pagination<TblMerInfoVO> QuickMerPagination = quickMerService.queryQuickMerList(merInfoForm);
                
        merInfoForm.setPagination(QuickMerPagination);
        merInfoForm.setInstDOList(BtsInstDOList);
        merInfoForm.setTblAgentInfoDos(AgentInfoDoList);        
        modelAndView.setViewName("/mer/mer_quick_list");
          
        return modelAndView;
        
    }
        
    /**
     * 快捷支付报备
     */
    @RequestMapping(value = "/register_quick_mer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> RegisterQuickMer (HttpServletRequest request, HttpServletResponse response) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
    	String merId = request.getParameter("merId");
    	String channel = request.getParameter("channel");

    	if (("HY").equals(channel)){
    		Map<String, String> status = quickMerService.registerQuickMerToChannel(merId, channel);
    		
    		if(status ==null || status.isEmpty()){
                resultMap.put("statusCode", 300);
                resultMap.put("message", "翰银快捷报备失败");
                return resultMap;    			
    		} else if(status.containsKey("ERROR")){
                resultMap.put("statusCode", 300);
                resultMap.put("message", status.get("ERROR"));
                return resultMap;     						
    		} else {
                resultMap.put("statusCode", 200);
                resultMap.put("message", "翰银快捷报备成功");
                return resultMap;  
             }
    	}  		
        return resultMap;
    }   
    
    /**
     * 快捷商户详细信息
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/query_quick_mer_detail")
    public ModelAndView queryQuickMerDetail(ModelAndView modelAndView, HttpServletRequest request) {
        
    	String merId = request.getParameter("id");
        List<TblMerFileInfoDO> emptyList = new ArrayList<TblMerFileInfoDO>();
        MerDetailInfo emptyMerDetailInfo = new MerDetailInfo();
        //没有参数
        if (Strings.isNullOrEmpty(merId)) {
            modelAndView.setViewName("/mer/mer_quick_edit");
            modelAndView.addObject("merInfo", emptyMerDetailInfo);
            modelAndView.addObject("tblMerFileInfoDOList", emptyList);
            return modelAndView;
        }

        Map<String, String> merTypes = merchInfoService.InstOrAgent(merId);	
        TblAgentInfoDo AgentInfoDo = null;
        TblMerInfoDO MerInfoDO = null;
        TblBtsInstDO BtsInstDO = null;
        
        if(merTypes !=null && merTypes.size() >0){
        	if(merTypes.containsKey("INSTID")){
        		BtsInstDO = quickMerService.queryInstById(merTypes.get("INSTID").toString());
        	}else if(merTypes.containsKey("AGENTID")) {
        		AgentInfoDo = quickMerService.queryAgentById(merId); 		
        	}
        }
        
        MerInfoDO = quickMerService.queryMerById(merId);      
        
        TblMerBankInfoDO MerBankInfoDO = quickMerService.queryMerBankById(merId);
        List<TblMerFeeInfoDO> tblMerFeeInfoDOS = quickMerService.queryMerFeeById(merId);
        QuickMerFeeForm quickMerFeeForm = new QuickMerFeeForm();
        TblMerFileInfoDO tblMerFileInfoDO = quickMerService.queryFilesByMerId(merId);
        TblMerRelevanceMccDo tblMerRelevanceMccDo = quickMerService.queryTblMerRelevanceMccDoById(merId);
        TblCustAddressDo CustAddressDo = quickMerService.queryMerAddressCodeById(merId);

        String feeType = "";
        String[] calc = new String[10];
        
        for (TblMerFeeInfoDO tblMerFeeInfoDO : tblMerFeeInfoDOS) {
            feeType = tblMerFeeInfoDO.getFeeType();
            String calcMode = tblMerFeeInfoDO.getCalcMode().toString();
            if(calcMode.indexOf("AMT") == -1){
            	calc[0] = calcMode;
            	calc[1] = "9999";
            } else {
            	calc = CalcModeUtil.splitCalcMode(tblMerFeeInfoDO.getCalcMode(), false).split(",");
            }
            
            switch (feeType) {
                case "01":
                	quickMerFeeForm.setFee01(calc[0]);
                	quickMerFeeForm.setFee01L(calc[1]);
                    break;
                case "02":
                	quickMerFeeForm.setFee02(calc[0]);
                    break;
                case "03":
                	quickMerFeeForm.setFee03(calc[0]);
                    break;
                case "04":
                	quickMerFeeForm.setFee04(calc[0]);
                    break;
                case "05":
                	quickMerFeeForm.setFee05(calc[0]);
                    break;
                case "06":
                	quickMerFeeForm.setFee06(calc[0]);
                    break;
                case "07":
                	quickMerFeeForm.setFee07(calc[0]);
                    break;
                case "08":
                	quickMerFeeForm.setFee08(calc[0]);
                    break;
                case "09":
                	quickMerFeeForm.setFee09(calc[0]);
                    break;                   
                case "Q1":
                	quickMerFeeForm.setFeeQ1(calc[0]);
                    break;
                case "Q2":
                	quickMerFeeForm.setFeeQ2(calc[0]);
                    break;
                case "Q3":
                	quickMerFeeForm.setFeeQ3(calc[0]);
                    break;
                case "Q4":
                	quickMerFeeForm.setFeeQ4(calc[0]);
                    break;
                default:
                    break;
            }
        }
        
        modelAndView.addObject("tblMerFileInfoDO", tblMerFileInfoDO);
        modelAndView.addObject("tblAgentInfoDo", AgentInfoDo);
        modelAndView.addObject("tblBtsInstDo", BtsInstDO);
        modelAndView.addObject("tblMerInfoDO", MerInfoDO);
        modelAndView.addObject("tblMerBankInfoDO", MerBankInfoDO);
        modelAndView.addObject("quickMerFeeForm", quickMerFeeForm);
        modelAndView.addObject("tblMerRelevanceMccDo", tblMerRelevanceMccDo);
        modelAndView.addObject("CustAddressDo", CustAddressDo);

        TblMerAuditRecordDO tblMerAuditRecordDO = merAgentService.queryMerAuditReocrdByMerId(merId);
        if (tblMerAuditRecordDO == null) {
            tblMerAuditRecordDO = new TblMerAuditRecordDO();
        }
        modelAndView.addObject("tblMerAuditRecordDO", tblMerAuditRecordDO);
        modelAndView.setViewName("/mer/mer_quick_edit");
        
    	return modelAndView;        
        
    }   
  
    /**
     * 快捷商户 -  更新
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/update_quick_mer", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateQuickMer(HttpSession session, ModelAndView modelAndView, HttpServletRequest request, @ModelAttribute("merInfoForm") MerInfoForm merInfoForm) {
           	
    	Map<String, Object> resultMap = new HashMap<>();
 
    	logger.info("检验更新参数");
    	Map<String, String> verifyResult = verifyQuickMerUpdateInfo(merInfoForm);
    	if(verifyResult.containsKey("ERROR")){
        	resultMap.put("statusCode", 300);
        	resultMap.put("message", verifyResult.get("ERROR"));    
        	return resultMap;
    	}
    	
    	logger.info("获取登陆用户信息");
        UserBO user = (UserBO) session.getAttribute(SystemConstant.USER_SESSION_KEY);   
        String userName = user.getUsrName();
        merInfoForm.setUserName(userName);

        logger.info("更新商户信息");
        
        String busExpire = merInfoForm.getBusLicExpire();
        String legalExpire = merInfoForm.getLegalPersonCertExpire();
        if(StringUtils.isNotBlank(busExpire)){
        	busExpire.replace("-", "");
        	merInfoForm.setBusLicExpire(busExpire.replace("-", "").toString().trim());
        }
        if(StringUtils.isNotBlank(legalExpire)){
        	busExpire.replace("-", "");
        	merInfoForm.setLegalPersonCertExpire(legalExpire.replace("-", "").toString().trim());
        }        
        merInfoForm.getBusLicExpire();
        merInfoForm.getLegalPersonCertExpire();
    	Map<String, String> UpdateStatus = quickMerService.updateQuickMer(merInfoForm);
    	if(UpdateStatus.containsKey("ERROR")){
        	resultMap.put("statusCode", 300);
        	resultMap.put("message", UpdateStatus.get("ERROR"));    
        	return resultMap;    		
    	}
    	
        logger.info("更新商户信息");
    	Map<String, String> modifyStatus = quickMerService.modifyQuickMerToChannel(merInfoForm.getMerId(), "HY");
    	if(modifyStatus.containsKey("ERROR")){
        	resultMap.put("statusCode", 300);
        	resultMap.put("message", modifyStatus.get("ERROR"));    
        	return resultMap;    		
    	}    	
 	
    	resultMap.put("statusCode", 200);
    	resultMap.put("message", "商户信息更新成功");
	
    	return resultMap;              
    }  
    
    /**
     * 快捷商户修改信息检验
     * @param modelAndView
     * @param request
     * @return
     */
    private Map<String, String> verifyQuickMerUpdateInfo(MerInfoForm merInfoForm) {
        
    	Map<String, String> resultMap = new HashMap<>();
    	
    	logger.info("检验城市编码");
    	String provinceCode = merInfoForm.getProvinceCode();
    	String cityCode = merInfoForm.getCityCode();
    	String areaCode = merInfoForm.getAreaCode();
    	Map<String, String> addressMap = addressService.getCorrectAddressCodes(provinceCode, cityCode, areaCode);
    	if(addressMap.containsKey("ERROR")){
        	resultMap.put("ERROR", addressMap.get("ERROR"));    
        	return resultMap;
    	}
    	return resultMap; 
    }       
    
    
    
    /**
     * 快捷商户-基础信息
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/query_quick_mer_base_info")
    public ModelAndView queryQuickMerBaseDetail(ModelAndView modelAndView, HttpServletRequest request) {
        
    	String merId = request.getParameter("id");
        TblMerInfoDO MerInfoDO = quickMerService.queryMerById(merId);      
        TblCustAddressDo CustAddressDo = quickMerService.queryMerAddressCodeById(merId);

        modelAndView.addObject("tblMerInfoDO", MerInfoDO);
        modelAndView.addObject("CustAddressDo", CustAddressDo);

        modelAndView.setViewName("/mer/mer_base_info_update");
        
    	return modelAndView;        
        
    }   
    
    /**
     * 快捷商户-基础信息 - 更新
     * @param modelAndView
     * @param request
     * @return
     */
    @RequestMapping(value = "/update_quick_mer_base_info")
    public ModelAndView updateQuickMerBaseInfo(ModelAndView modelAndView, HttpServletRequest request) {
        
    	String merId = request.getParameter("id");
        TblMerInfoDO MerInfoDO = quickMerService.queryMerById(merId);      
        TblCustAddressDo CustAddressDo = quickMerService.queryMerAddressCodeById(merId);

        modelAndView.addObject("tblMerInfoDO", MerInfoDO);
        modelAndView.addObject("CustAddressDo", CustAddressDo);

        modelAndView.setViewName("/mer/mer_quick_mer_base_info");
        
    	return modelAndView;        
        
    }    
    
}
