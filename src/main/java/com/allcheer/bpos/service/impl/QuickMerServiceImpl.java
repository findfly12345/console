package com.allcheer.bpos.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allcheer.bpos.constant.NotifyException;
import com.allcheer.bpos.constant.SystemConstant;
import com.allcheer.bpos.entity.TblAgentInfoDo;
import com.allcheer.bpos.entity.TblAgentInfoDoExample;
import com.allcheer.bpos.entity.TblAgentMerTermDo;
import com.allcheer.bpos.entity.TblAgentMerTermDoExample;
import com.allcheer.bpos.entity.TblBtsInstDO;
import com.allcheer.bpos.entity.TblCustAddressDo;
import com.allcheer.bpos.entity.TblCustAddressDoExample;
import com.allcheer.bpos.entity.TblInstRouteControl;
import com.allcheer.bpos.entity.TblInstRouteControlExample;
import com.allcheer.bpos.entity.TblMerBankInfoDO;
import com.allcheer.bpos.entity.TblMerBankInfoDOExample;
import com.allcheer.bpos.entity.TblMerFeeInfoDO;
import com.allcheer.bpos.entity.TblMerFeeInfoDOExample;
import com.allcheer.bpos.entity.TblMerFileInfoDO;
import com.allcheer.bpos.entity.TblMerFileInfoDOExample;
import com.allcheer.bpos.entity.TblMerInfoDO;
import com.allcheer.bpos.entity.TblMerInfoDOExample;
import com.allcheer.bpos.entity.TblMerRelevanceMccDo;
import com.allcheer.bpos.entity.TblMerRelevanceMccDoExample;
import com.allcheer.bpos.entity.TblOnlineUserLoginDO;
import com.allcheer.bpos.entity.TblOnlineUserLoginDOExample;
import com.allcheer.bpos.entity.TblOnlineUserMapDO;
import com.allcheer.bpos.entity.TblOnlineUserMapDOExample;
import com.allcheer.bpos.entity.TblQuickMerRelDO;
import com.allcheer.bpos.entity.TblQuickMerRelDOExample;
import com.allcheer.bpos.entity.TblQuickMerRelDOExample.Criteria;
import com.allcheer.bpos.entity.Enum.ErrorRespEnum;
import com.allcheer.bpos.entity.vo.TblAgentMerInfoVO;
import com.allcheer.bpos.entity.vo.TblMerInfoVO;
import com.allcheer.bpos.form.MerFeeForm;
import com.allcheer.bpos.form.MerInfoForm;
import com.allcheer.bpos.mapper.TblAgentInfoDoMapper;
import com.allcheer.bpos.mapper.TblAgentMerTermDoMapper;
import com.allcheer.bpos.mapper.TblBtsInstDOMapper;
import com.allcheer.bpos.mapper.TblCustAddressDoMapper;
import com.allcheer.bpos.mapper.TblInstMerTermRelDOMapper;
import com.allcheer.bpos.mapper.TblInstRouteControlMapper;
import com.allcheer.bpos.mapper.TblMerBankInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFeeInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerFileInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerInfoDOMapper;
import com.allcheer.bpos.mapper.TblMerRelevanceMccDoMapper;
import com.allcheer.bpos.mapper.TblOnlineUserLoginDOMapper;
import com.allcheer.bpos.mapper.TblOnlineUserMapDOMapper;
import com.allcheer.bpos.mapper.TblQuickMerRelDOMapper;
import com.allcheer.bpos.service.MerAgentService;
import com.allcheer.bpos.service.MerChannelPreService;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.QuickMerService;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.ParserUtil;
import com.allcheer.bpos.util.StringUtils;
import com.allcheer.bpos.util.TimeUtil;
import com.github.pagehelper.PageHelper;

/**
 * Created by peng.ll on 2017/3/1.
 */

@Service("QuickMerService")
public class QuickMerServiceImpl implements QuickMerService {

    private final static Logger logger = LoggerFactory.getLogger(QuickMerServiceImpl.class);
	
	@Autowired
	TblQuickMerRelDOMapper QuickMerRelDOMapper;

    @Autowired
    TblMerInfoDOMapper MerInfoDOMapper;
	
    @Autowired
    MerchInfoService merchInfoService;
    
    @Autowired
    TblInstRouteControlMapper InstRouteControlMapper;
    
    @Autowired
    MerAgentService merAgentService;
   
    @Autowired
    MerChannelPreService merChannelPreService;
    
    @Autowired
    TblInstMerTermRelDOMapper InstMerTermRelDOMapper;
    
    @Autowired
    TblAgentInfoDoMapper AgentInfoDoMapper;
    
    @Autowired
    TblMerBankInfoDOMapper MerBankInfoDOMapper;

    @Autowired
    TblMerFeeInfoDOMapper MerFeeInfoDOMapper;
    
    @Autowired
    TblAgentMerTermDoMapper AgentMerTermDoMapper;    
    
    @Autowired
    TblBtsInstDOMapper BtsInstDOMapper;
    
    @Autowired
    TblMerFileInfoDOMapper MerFileInfoDOMapper;
    
    @Autowired
    TblCustAddressDoMapper CustAddressDoMapper;
      
    @Autowired
    TblMerRelevanceMccDoMapper MerRelevanceMccDoMapper;
    
    @Autowired
    TblOnlineUserLoginDOMapper OnlineUserLoginDOMapper;
    
    @Autowired
    TblOnlineUserMapDOMapper  OnlineUserMapDOMapper;
    
    private final static String QUICK_CHANNEL_HY = "U5";
   
	/**
	 * 查询快捷支付商户列表
	 */
	@Override
	public Pagination<TblMerInfoVO> queryQuickMerList(MerInfoForm merForm) {

		String quickMark = merForm.getQuickMark();
		
		if(StringUtils.isNotBlank(quickMark) && quickMark.equals("on")){
			//从TBL_QUICK_MER_REL中获取商户
			return quickMerList(merForm);
				
		} else {
			
			String instId = merForm.getInstId();
			String agentId = merForm.getAgentId();
			if(StringUtils.isBlank(instId) && StringUtils.isBlank(agentId)){
				//从TBL_MER_INFO里获取商户信息
				return merList(merForm);
	
			} else if(!StringUtils.isBlank(instId)){
				//从TBL_INST_MER_TERM_REL里获取商户信息
				return instMerList(merForm);			
				
			} else if(!StringUtils.isBlank(agentId)){
				//从TBL_AGENT_MER_TERM_REL里获取商户信息
				return agentMerList(merForm);
				
			} 

		}
		return null;
	}


	/**
	 * 从TBL_MER_INFO里获取商户列表
	 * @return 
	 */
	@SuppressWarnings({ "unchecked" })
	private Pagination<TblMerInfoVO> merList (MerInfoForm merForm) {
		
		String merId = merForm.getMerId();
		String merName = merForm.getMerName();
		String formQuickStatus = merForm.getQuickStatus();
		Boolean MerQuick = false;
		TblMerInfoDOExample MerInfoDOExample = new TblMerInfoDOExample();
		TblMerInfoDOExample.Criteria MerCriteria = MerInfoDOExample.createCriteria();
		if(StringUtils.isNotBlank(merId)){
			MerCriteria.andMerIdEqualTo(merId);
		} 
		if(StringUtils.isNotBlank(merName)){
			MerCriteria.andMerNameLike("%" + merName + "%");
		} 				
	
		int count = MerInfoDOMapper.countByExample(MerInfoDOExample);
		int pageCurrent = Integer.valueOf((String) merForm.getPageCurrent());
		int pageSize = Integer.valueOf((String) merForm.getPageSize());
		Pagination pagination = new Pagination<>(count, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);
		List<TblMerInfoDO> MerInfoDoList = MerInfoDOMapper.selectByExample(MerInfoDOExample);
		
		List<TblMerInfoVO> QuickMerList = new ArrayList<>();
		
		for (TblMerInfoDO MerInfoDO: MerInfoDoList){

			MerQuick = false;
			
			TblMerInfoVO MerInfoVO = new TblMerInfoVO();
			MerInfoVO.setMerId(MerInfoDO.getMerId());
			MerInfoVO.setMerName(MerInfoDO.getMerName());
			
			//检查机构商户或者代理商商户
			Map<String, String> mapCategory = merchInfoService.InstOrAgent(MerInfoDO.getMerId());
			String instCode = "";
			if(mapCategory.containsKey("INSTID")){
				instCode = mapCategory.get("INSTID");
				MerInfoVO.setInstId(instCode);
				MerInfoVO.setAgentId("");
			}else if(mapCategory.containsKey("AGENTID")){
				instCode = mapCategory.get("AGENTID");
				MerInfoVO.setAgentId(instCode);
				MerInfoVO.setInstId("");
			}else {
				instCode = "";
				MerInfoVO.setInstId(instCode);
				MerInfoVO.setAgentId(instCode);
			}			

			TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
			InstRouteControlExample.createCriteria().andInstMerIdEqualTo(MerInfoDO.getMerId()).andInstCodeEqualTo(instCode).andGateIdEqualTo(QUICK_CHANNEL_HY);
			List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
			if(InstRouteControlList !=null && InstRouteControlList.size() > 0){
				MerInfoVO.setChannelMerId(InstRouteControlList.get(0).getBankMerId());
				MerInfoVO.setRegistedQuickHY("Y");
				MerQuick = true;
			}	
			
			if(formQuickStatus.equals("N")){
				if (MerQuick){
					continue;
				}
			}else if(formQuickStatus.equals("Y")){
				if (!MerQuick){
					continue;
				}
			}
			
			//商户报备状态
			MerInfoVO.setMerStat(MerInfoDO.getMerStat());
			
			QuickMerList.add(MerInfoVO);
			
		}		
		
		pagination.addResult(QuickMerList);
		return pagination;		
		
		
	}

	/**
	 * 只获取快捷商户
	 */
	@SuppressWarnings("unchecked")
	private Pagination<TblMerInfoVO> quickMerList(MerInfoForm merForm) {
		
		int count = QuickMerRelDOMapper.countByExample(formatQuickSearchFields(merForm));
		int pageCurrent = Integer.valueOf((String) merForm.getPageCurrent());
		int pageSize = Integer.valueOf((String) merForm.getPageSize());

		// 只查找无卡商户 - TBL_QUICK_MER_REL
		Pagination pagination = new Pagination<>(count, pageCurrent, pageSize);
		PageHelper.startPage(pageCurrent, pageSize);

		List<TblQuickMerRelDO> QuickMerRelDoList = QuickMerRelDOMapper.selectByExample(formatQuickSearchFields(merForm));
		List<TblMerInfoVO> QuickMerList = new ArrayList<>();
		
		for (TblQuickMerRelDO QuickMerRelDO: QuickMerRelDoList){

			TblMerInfoVO MerInfoVO = new TblMerInfoVO();
			MerInfoVO.setMerId(QuickMerRelDO.getMerId());
			MerInfoVO.setMerName(QuickMerRelDO.getMerName());
			MerInfoVO.setQuickStatus(QuickMerRelDO.getStatus());
			MerInfoVO.setInstId(QuickMerRelDO.getInstId());
			MerInfoVO.setAgentId(QuickMerRelDO.getAgentId());
			
			//检查商户报备状态
			TblMerInfoDO MerInfoDO = MerInfoDOMapper.selectByPrimaryKey(QuickMerRelDO.getMerId());
			MerInfoVO.setMerStat(MerInfoDO.getMerStat());
			
			//检查渠道报备信息
			Map<String, String> mapCategory = merchInfoService.InstOrAgent(QuickMerRelDO.getMerId());
			String instCode = "";
			if(mapCategory.containsKey("INSTID")){
				instCode = mapCategory.get("INSTID");
			}else if(mapCategory.containsKey("AGENTID")){
				instCode = mapCategory.get("AGENTID");
			}else {
				instCode = "000000000000000";
			}
			TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
			InstRouteControlExample.createCriteria().andInstMerIdEqualTo(QuickMerRelDO.getMerId()).andInstCodeEqualTo(instCode).andGateIdEqualTo(QUICK_CHANNEL_HY);
			List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
			if(InstRouteControlList !=null && InstRouteControlList.size() > 0){
				MerInfoVO.setChannelMerId(InstRouteControlList.get(0).getBankMerId());
				MerInfoVO.setRegistedQuickHY("Y");
			}
			
			//商户报备状态
			MerInfoVO.setMerStat(MerInfoDO.getMerStat());
			
			QuickMerList.add(MerInfoVO);
		}		
		
		pagination.addResult(QuickMerList);
		return pagination;
	}


	/**
	 * 获取代理商下的商户咧表
	 */
	@SuppressWarnings("unchecked")
	private Pagination<TblMerInfoVO> agentMerList(MerInfoForm merForm) {
		
        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(merForm);
 
		String formQuickStatus = merForm.getQuickStatus();
		Boolean MerQuick = false;
        
		int pageCurrent = Integer.valueOf((String) merForm.getPageCurrent());
		int pageSize = Integer.valueOf((String) merForm.getPageSize());
        
        List<TblAgentMerInfoVO> AgentMerList = new ArrayList<>();
        List<TblMerInfoVO> QuickMerList = new ArrayList<>();
        Pagination<TblAgentMerInfoVO> AgentMerInfoVOPagination = merAgentService.queryAgentMerInfoList(queryMap);
        
        if(AgentMerInfoVOPagination !=null){
        	AgentMerList = AgentMerInfoVOPagination.getList();
        } else {
    		Pagination pagination = new Pagination<>(0, pageCurrent, pageSize);
    		PageHelper.startPage(pageCurrent, pageSize);        	
    		pagination.addResult(QuickMerList);
    		return pagination;        	
        }
		
		Pagination pagination = new Pagination<>(AgentMerInfoVOPagination.getItemCount(), AgentMerInfoVOPagination.getPageIndex(), AgentMerInfoVOPagination.getPageSize());
		PageHelper.startPage(pageCurrent, pageSize);        	     
        
		for (TblAgentMerInfoVO AgentMerInfoVO: AgentMerList){

			MerQuick = false;
			
			TblMerInfoVO MerInfoVO = new TblMerInfoVO();
			MerInfoVO.setMerId(AgentMerInfoVO.getMerId());
			MerInfoVO.setAgentId(AgentMerInfoVO.getAgentId());
			MerInfoVO.setMerName(AgentMerInfoVO.getMerName());
			MerInfoVO.setMerStat(AgentMerInfoVO.getMerStat());
			MerInfoVO.setInstId("");
				
			//检查渠道报备信息
			Map<String, String> mapCategory = merchInfoService.InstOrAgent(merForm.getMerId());
			String instCode = AgentMerInfoVO.getAgentId();
			
			//检查快捷商户报备状态
			TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
			InstRouteControlExample.createCriteria().andInstMerIdEqualTo(merForm.getMerId()).andInstCodeEqualTo(instCode).andGateIdEqualTo(QUICK_CHANNEL_HY);
			List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
			if(InstRouteControlList !=null && InstRouteControlList.size() > 0){
				MerInfoVO.setChannelMerId(InstRouteControlList.get(0).getBankMerId());
				MerInfoVO.setRegistedQuickHY("Y");
				MerQuick = true;
			}	
			
			if(formQuickStatus.equals("N")){
				if (MerQuick){
					continue;
				}
			}else if(formQuickStatus.equals("Y")){
				if (!MerQuick){
					continue;
				}
			}
			
			QuickMerList.add(MerInfoVO);
		}		
		
		pagination.addResult(QuickMerList);
		return pagination;
	}
	
	
	/**
	 * 获取机构商户下的列表
	 */
	@SuppressWarnings("unchecked")
	private Pagination<TblMerInfoVO> instMerList(MerInfoForm merForm) {
		
		String formQuickStatus = merForm.getQuickStatus();
		Boolean MerQuick = false;
		
        Map<String, String> queryMap = new HashMap<>();
        queryMap = Bean2Map.beanToMapNoFill(merForm);
 
		int pageCurrent = Integer.valueOf((String) merForm.getPageCurrent());
		int pageSize = Integer.valueOf((String) merForm.getPageSize());
        
        List<TblMerInfoVO> InstQuickMerList = new ArrayList<>();
        List<TblMerInfoVO> QuickMerList = new ArrayList<>();
        Pagination<TblMerInfoVO> InstMerInfoVOPagination = merChannelPreService.queryMerInfoList(queryMap);
   
        if(InstMerInfoVOPagination !=null){
        	InstQuickMerList = InstMerInfoVOPagination.getList();
        } else {
    		Pagination pagination = new Pagination<>(0, pageCurrent, pageSize);
    		PageHelper.startPage(pageCurrent, pageSize);        	
    		pagination.addResult(QuickMerList);
    		return pagination;        	
        }
		
		Pagination pagination = new Pagination<>(InstMerInfoVOPagination.getItemCount(), InstMerInfoVOPagination.getPageIndex(), InstMerInfoVOPagination.getPageSize());
		PageHelper.startPage(pageCurrent, pageSize);        	     
        
		for (TblMerInfoVO InstMerInfoVO: InstQuickMerList){

			MerQuick = true;
			
			TblMerInfoVO MerInfoVO = new TblMerInfoVO();
			MerInfoVO.setMerId(InstMerInfoVO.getMerId());
			MerInfoVO.setAgentId("");
			MerInfoVO.setMerName(InstMerInfoVO.getMerName());
			MerInfoVO.setMerStat(InstMerInfoVO.getMerStat());
			MerInfoVO.setInstId(InstMerInfoVO.getInstId());
					
			//检查渠道报备信息
			String instCode = InstMerInfoVO.getInstId();
			TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
			InstRouteControlExample.createCriteria().andInstMerIdEqualTo(merForm.getMerId()).andInstCodeEqualTo(instCode).andGateIdEqualTo(QUICK_CHANNEL_HY);
			List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
			if(InstRouteControlList !=null && InstRouteControlList.size() > 0){
				MerInfoVO.setChannelMerId(InstRouteControlList.get(0).getBankMerId());
				MerInfoVO.setRegistedQuickHY("Y");
				MerQuick = true;
			}	
			
			if(formQuickStatus.equals("N")){
				if (MerQuick){
					continue;
				}
			}else if(formQuickStatus.equals("Y")){
				if (!MerQuick){
					continue;
				}
			}
		
			QuickMerList.add(MerInfoVO);
		}		
		
		pagination.addResult(QuickMerList);
		return pagination;
	}

	/**
	 * 统计quick_mer
	 */
	private TblQuickMerRelDOExample formatQuickSearchFields(MerInfoForm merForm) {

		String instId = merForm.getInstId();
		String merId = merForm.getMerId();
		String agentId = merForm.getAgentId();
		String merName = merForm.getMerName();
		String status = merForm.getQuickStatus();

		TblQuickMerRelDOExample QuickMerRelDOExample = new TblQuickMerRelDOExample();
		Criteria cretiria = QuickMerRelDOExample.createCriteria();

		
		if (StringUtils.isNotBlank(merId)) {
			cretiria.andMerIdEqualTo(merId);
		}
		if (StringUtils.isNotBlank(instId)) {
			cretiria.andInstIdEqualTo(instId);
		}
		if (StringUtils.isNotBlank(agentId)) {
			cretiria.andAgentIdEqualTo(agentId);
		}
		if (StringUtils.isNotBlank(merName)) {
			cretiria.andMerNameLike("%" + merName + "%");
		}
		if (StringUtils.isNotBlank(status)) {
			if(status.equals("Y")){
				cretiria.andStatusEqualTo(status);
			}else if (status.equals("N")){
				cretiria.andStatusIsNull();
			}
		}
		return QuickMerRelDOExample;
	}

	/**
	 * 注册翰银快捷支付
	 */
	@Override
	public Map<String, String> registerQuickMerToChannel(String merId, String channel) {

		Map<String, String> resultMap = new HashMap<>();

		//检查商户是否在快捷商户表中
		String instCode = "";
		TblQuickMerRelDOExample QuickMerRelDOExample = new TblQuickMerRelDOExample();
		QuickMerRelDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblQuickMerRelDO> QuickMerRelDoList = QuickMerRelDOMapper.selectByExample(QuickMerRelDOExample);
		if(QuickMerRelDoList !=null && QuickMerRelDoList.size() >0){
			
		}else {
			TblQuickMerRelDO QuickMerRelDO = new TblQuickMerRelDO();
			QuickMerRelDO.setMerId(merId);
			TblMerInfoDO MerInfoDO = MerInfoDOMapper.selectByPrimaryKey(QuickMerRelDO.getMerId());
			QuickMerRelDO.setMerName(MerInfoDO.getMerName());
			QuickMerRelDO.setStatus("");
			//检查渠道报备信息
			Map<String, String> mapCategory = merchInfoService.InstOrAgent(QuickMerRelDO.getMerId());
			if(mapCategory.containsKey("INSTID")){
				instCode = mapCategory.get("INSTID");
				QuickMerRelDO.setInstId(instCode);
				QuickMerRelDO.setInstMerId(mapCategory.get("INSTMERID"));
				QuickMerRelDO.setInstTermId(mapCategory.get("TERMID"));
				QuickMerRelDO.setAgentId("");
			}else if(mapCategory.containsKey("AGENTID")){
				instCode = mapCategory.get("AGENTID");
				QuickMerRelDO.setAgentId(instCode);
			}else {
				instCode = "000000000000000";
			}
			//检查渠道报备信息
			int inCount = QuickMerRelDOMapper.insertSelective(QuickMerRelDO);
			if(inCount <= 0){
				resultMap.put("ERROR",  "系统错误 -快捷商户关联失败");
				return resultMap;
			}
		}	
	
		TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
		InstRouteControlExample.createCriteria().andInstMerIdEqualTo(merId).andInstCodeEqualTo(instCode).andGateIdEqualTo(QUICK_CHANNEL_HY);
		List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
		if(InstRouteControlList !=null && InstRouteControlList.size() > 0){
			resultMap.put("ERROR",  "此商户已经报备翰银快捷"); 
			return resultMap;
		}		
		
		//新增Action=A
		String param = "MERID=" + merId + "&ACTION=" + "A" + "&CHANNEL=" + channel;
		
		HttpURLConnection httpUrlConnection = null;
		
		URL registerurl;
		try {
			registerurl = new URL(SystemConstant.MER_CENTER_URL + "/mer_register/quick_pay_register");
			logger.info("快捷商户注册地址:" + registerurl);
			URLConnection urlConnection = registerurl.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();
			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			int resultCode = httpUrlConnection.getResponseCode();
			
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
							
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(sb1.toString());
                logger.info("快捷支付报备调用接口返回:" + respMap);

				if(respMap == null || respMap.isEmpty()){
					resultMap.put("ERROR",  "快捷支付报备失败, 请查看!"); 
					return resultMap;
				}
				
				if(respMap.containsKey("RSPCOD") && respMap.get("RSPCOD").equals("SUCCESS")){
					resultMap.put("SUCCESS",  "快捷支付报备成功"); 
				} else {
					resultMap.put("ERROR",  "快捷支付报备失败:" + respMap.get("RSPMSG")); 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("ERROR", "快捷支付报备失败 -系统错误!");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("ERROR", "快捷支付报备失败 -系统错误!");
		}		
		return resultMap;
	}
	
	/**
	 * 注册翰银快捷支付
	 */
	@Override
	public Map<String, String> modifyQuickMerToChannel(String merId, String channel) {

		Map<String, String> resultMap = new HashMap<>();

		//检查商户是否在快捷商户表中
		String instCode = "";
		TblQuickMerRelDOExample QuickMerRelDOExample = new TblQuickMerRelDOExample();
		QuickMerRelDOExample.createCriteria().andMerIdEqualTo(merId);
		List<TblQuickMerRelDO> QuickMerRelDoList = QuickMerRelDOMapper.selectByExample(QuickMerRelDOExample);
		if(QuickMerRelDoList !=null && QuickMerRelDoList.size() >0){
			
		}else {
			TblQuickMerRelDO QuickMerRelDO = new TblQuickMerRelDO();
			QuickMerRelDO.setMerId(merId);
			TblMerInfoDO MerInfoDO = MerInfoDOMapper.selectByPrimaryKey(QuickMerRelDO.getMerId());
			QuickMerRelDO.setMerName(MerInfoDO.getMerName());
			QuickMerRelDO.setStatus("");
			//检查渠道报备信息
			Map<String, String> mapCategory = merchInfoService.InstOrAgent(QuickMerRelDO.getMerId());
			if(mapCategory.containsKey("INSTID")){
				instCode = mapCategory.get("INSTID");
				QuickMerRelDO.setInstId(instCode);
				QuickMerRelDO.setInstMerId(mapCategory.get("INSTMERID"));
				QuickMerRelDO.setInstTermId(mapCategory.get("TERMID"));
				QuickMerRelDO.setAgentId("");
			}else if(mapCategory.containsKey("AGENTID")){
				instCode = mapCategory.get("AGENTID");
				QuickMerRelDO.setAgentId(instCode);
			}else {
				instCode = "000000000000000";
			}
			//检查渠道报备信息
			int inCount = QuickMerRelDOMapper.insertSelective(QuickMerRelDO);
			if(inCount <= 0){
				resultMap.put("ERROR",  "系统错误 -快捷商户关联失败");
				return resultMap;
			}
		}	
	
		TblInstRouteControlExample InstRouteControlExample = new TblInstRouteControlExample();
		InstRouteControlExample.createCriteria().andInstMerIdEqualTo(merId).andGateIdEqualTo(QUICK_CHANNEL_HY);
		List<TblInstRouteControl> InstRouteControlList = InstRouteControlMapper.selectByExample(InstRouteControlExample);
		if(InstRouteControlList == null || InstRouteControlList.size() == 0){			
			return resultMap;
		}		
		
		//更改操作M全部属性修改
		String param = "MERID=" + merId + "&ACTION=" + "M" + "&CHANNEL=" + channel;
		
		HttpURLConnection httpUrlConnection = null;
		
		URL registerurl;
		try {
			registerurl = new URL(SystemConstant.MER_CENTER_URL + "/mer_modify/quick_pay_modify");
			logger.info("快捷商户注册地址:" + registerurl);
			URLConnection urlConnection = registerurl.openConnection();
			httpUrlConnection = (HttpURLConnection) urlConnection;

			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestMethod("POST");

			httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpUrlConnection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpUrlConnection.setRequestProperty("Charset", "UTF-8");

			httpUrlConnection.connect();
			DataOutputStream dos = new DataOutputStream(httpUrlConnection.getOutputStream());
			dos.writeBytes(param);
			dos.flush();
			dos.close();
			int resultCode = httpUrlConnection.getResponseCode();
			
			if (HttpURLConnection.HTTP_OK == resultCode) {
				StringBuffer sb1 = new StringBuffer();
				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb1.append(readLine).append("\n");
				}
				responseReader.close();
							
				Map<String, String> respMap = ParserUtil.convertNodesFromXml(sb1.toString());
                logger.info("快捷支付报备调用接口返回:" + respMap);

				if(respMap == null || respMap.isEmpty()){
					resultMap.put("ERROR",  "快捷支付商户修改失败, 请查看!"); 
					return resultMap;
				}
				
				if(respMap.containsKey("RSPCOD") && respMap.get("RSPCOD").equals("SUCCESS")){
					resultMap.put("SUCCESS",  "快捷支付商户修改成功"); 
				} else {
					resultMap.put("ERROR",  "快捷支付商户修改失败:" + respMap.get("RSPMSG")); 
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("ERROR", "快捷支付商户修改失败 -系统错误!");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("ERROR", "快捷支付商户修改失败 -系统错误!");
		}		
		return resultMap;
	}

	/**
	 * 更新快捷商户信息
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, String> updateQuickMer (MerInfoForm merForm) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		try {
			TblMerInfoDO MerInfoDO = new TblMerInfoDO();
			MerInfoDO.setMerId(merForm.getMerId());
			MerInfoDO.setMerName(merForm.getMerName());
			MerInfoDO.setMerType(merForm.getMerType());
			MerInfoDO.setRegName(merForm.getRegName());
			MerInfoDO.setRegShortName(merForm.getRegShortName());
			MerInfoDO.setMerAddress(merForm.getMerAddress());
			MerInfoDO.setBusLicNm(merForm.getBusLicNm());
			MerInfoDO.setBusLicExpire(merForm.getBusLicExpire());
			MerInfoDO.setLegalPerson(merForm.getLegalPerson());
			MerInfoDO.setLegalPersonCertType(merForm.getLegalPersonCertType());
			MerInfoDO.setLegalPersonCertNm(merForm.getLegalPersonCertNm());
			MerInfoDO.setLegalPersonCertExpire(merForm.getLegalPersonCertExpire());
			MerInfoDO.setContactPerson(merForm.getContactPerson());
			MerInfoDO.setContactMobile(merForm.getContactMobile());
			MerInfoDO.setContactEmail(merForm.getContactEmail());		
			String currentDate = TimeUtil.getCurrentDate();
			String currentTime = TimeUtil.getCurrentTime();
			MerInfoDO.setUserName(merForm.getUserName());
			MerInfoDO.setUpdateTime(currentDate + currentTime);

			int i = MerInfoDOMapper.updateByPrimaryKeySelective(MerInfoDO); 
			if(i != 1){
				throw new Exception("商户信息更新失败");
			}
			
			//更新地址码信息
			TblCustAddressDo CustAddressDo;
			TblCustAddressDoExample CustAddressDoExample;
			CustAddressDo = new TblCustAddressDo();
			CustAddressDo.setMerId(merForm.getMerId());
			CustAddressDo.setMerAddress(merForm.getMerAddress());
			CustAddressDo.setProvinceCode(merForm.getProvinceCode());
			CustAddressDo.setCityCode(merForm.getCityCode());
			CustAddressDo.setAreaCode(merForm.getAreaCode());
			CustAddressDo.setMerAddress(merForm.getMerAddress());		
			CustAddressDoExample = new TblCustAddressDoExample();
			CustAddressDoExample.createCriteria().andMerIdEqualTo(merForm.getMerId());
			int c = CustAddressDoMapper.updateByExampleSelective(CustAddressDo, CustAddressDoExample);
			if(c <= 0){
				int c1 = CustAddressDoMapper.insertSelective(CustAddressDo);
				if(c1 <=0 ){
					throw new Exception("商户地址码更新失败");
				}
			}
						
			//更新结算行信息	        
	        TblMerBankInfoDO MerBankInfoDO = new TblMerBankInfoDO();
	        MerBankInfoDO.setMerId(merForm.getMerId());
	        MerBankInfoDO.setBankName(merForm.getBankName());
	        MerBankInfoDO.setProvName(merForm.getProvName());
	        MerBankInfoDO.setCityName(merForm.getCityName());
	        MerBankInfoDO.setBankBranchName(merForm.getBankBranchName());
	        MerBankInfoDO.setCnaps(merForm.getCnaps());
	        MerBankInfoDO.setIsPrivate(merForm.getIsPrivate());
	        MerBankInfoDO.setAcctName(merForm.getAcctName());
	        MerBankInfoDO.setAcctNo(merForm.getAcctNo());
	        MerBankInfoDO.setUpdateTime(currentDate + currentTime);	
	        MerBankInfoDO.setUserName(merForm.getUserName());
	        int d = MerBankInfoDOMapper.updateByPrimaryKeySelective(MerBankInfoDO);
			if(d <= 0){
				throw new Exception("商户结算码更新失败");
			}			
			
			//费率更新
			String termId = "";
	        Map<String, String> merTypes = merchInfoService.InstOrAgent(merForm.getMerId());	
	        if(merTypes !=null && merTypes.size() >0){
	        	if(merTypes.containsKey("INSTID")){
	        		termId = merTypes.get("TERMID");
	        	}else if(merTypes.containsKey("AGENTID")) {
	        		termId = merTypes.get("TERMID");
	        	}else {
	        		termId = "99999999";
	        	}
	        } else {
	        	termId = "99999999";
	        }
	
			MerFeeForm merFeeForm = new MerFeeForm();
			merFeeForm.setMerId(merForm.getMerId());
			merFeeForm.setFee01(merForm.getFee01());
			merFeeForm.setFee01L(merForm.getFee01L());
			merFeeForm.setFee02(merForm.getFee02());
			merFeeForm.setFee03(merForm.getFee03());
			merFeeForm.setFee04(merForm.getFee04());
			merFeeForm.setFee05(merForm.getFee05());
			merFeeForm.setFee06(merForm.getFee06());
			merFeeForm.setFee07(merForm.getFee07());
			merFeeForm.setFee08(merForm.getFee08());
			merFeeForm.setFee09(merForm.getFee09());
			merFeeForm.setFeeQ1(merForm.getFeeQ1());
			merFeeForm.setFeeQ2(merForm.getFeeQ2());
			merFeeForm.setFeeQ3(merForm.getFeeQ3());
			merFeeForm.setFeeQ4(merForm.getFeeQ4());
			
			merFeeForm.setUserName(merForm.getUserName());
			merFeeForm.setTermId(termId);
				
			try {
				
				Map<String, Object> feeUpStatus = merchInfoService.updateMerfee(merFeeForm);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("商户费率更新失败");
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("ERROR", e.getMessage());
		    return resultMap;
		}		
		return resultMap;
	}	
	
	/**
	 * 查询代理商信息
	 * @param merId
	 * @return
	 */
    @Override
    public TblAgentInfoDo queryAgentById(String merId) {
    	
        TblAgentMerTermDoExample tblAgentMerTermDoExample = new TblAgentMerTermDoExample();
        tblAgentMerTermDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblAgentMerTermDo> tblAgentMerTermDos = AgentMerTermDoMapper.selectByExample(tblAgentMerTermDoExample);
        if (tblAgentMerTermDos.size() == 0) {
            return new TblAgentInfoDo();
        }
        TblAgentMerTermDo tblAgentMerTermDo = tblAgentMerTermDos.get(0);
        String agentId = tblAgentMerTermDo.getAgentId();
        TblAgentInfoDoExample tblAgentInfoDoExample = new TblAgentInfoDoExample();
        tblAgentInfoDoExample.createCriteria().andMemberIdEqualTo(agentId);
        List<TblAgentInfoDo> tblAgentInfoDos = AgentInfoDoMapper.selectByExample(tblAgentInfoDoExample);
        if (tblAgentInfoDos.size() == 0) {
            return new TblAgentInfoDo();
        }
        return tblAgentInfoDos.get(0);
    }

	/**
	 * 查询机构商信息
	 */
    @Override
	public TblBtsInstDO queryInstById(String instId){
    	
    	TblBtsInstDO BtsInstDO = BtsInstDOMapper.selectByPrimaryKey(instId);
    	return BtsInstDO;
    }	   
    
    /**
     * 查询商户信息
     */
    @Override
    public TblMerInfoDO queryMerById(String merId) {
        TblMerInfoDOExample tblMerInfoDOExample = new TblMerInfoDOExample();
        tblMerInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerInfoDO> tblMerInfoDOS = MerInfoDOMapper.selectByExample(tblMerInfoDOExample);
        if (tblMerInfoDOS.size() == 0) {
            return new TblMerInfoDO();
        }
        return tblMerInfoDOS.get(0);
    }

    /**
     * 查询结算信息
     */
    @Override
    public TblMerBankInfoDO queryMerBankById(String merId) {
        TblMerBankInfoDOExample tblMerBankInfoDOExample = new TblMerBankInfoDOExample();
        tblMerBankInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerBankInfoDO> tblMerBankInfoDOS = MerBankInfoDOMapper.selectByExample(tblMerBankInfoDOExample);
        if (tblMerBankInfoDOS.size() == 0) {
            return new TblMerBankInfoDO();
        }
        return tblMerBankInfoDOS.get(0);
    }

    /**
     * 查询费率信息
     */
    @Override
    public List<TblMerFeeInfoDO> queryMerFeeById(String merId) {
        TblMerFeeInfoDOExample tblMerFeeInfoDOExample = new TblMerFeeInfoDOExample();
        tblMerFeeInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerFeeInfoDO> tblMerFeeInfoDOS = MerFeeInfoDOMapper.selectByExample(tblMerFeeInfoDOExample);
        if (tblMerFeeInfoDOS.size() == 0) {
            return new ArrayList<TblMerFeeInfoDO>();
        }
        return tblMerFeeInfoDOS;
    }
    
    /**
     * 附件信息
     * @param merId
     * @return
     */
    @Override
    public TblMerFileInfoDO queryFilesByMerId(String merId) {
    	
        TblMerFileInfoDOExample tblMerFileInfoDOExample = new TblMerFileInfoDOExample();
        tblMerFileInfoDOExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerFileInfoDO> tblMerFileInfoDOS = MerFileInfoDOMapper.selectByExample(tblMerFileInfoDOExample);
        if (tblMerFileInfoDOS.size() > 0) {
            return tblMerFileInfoDOS.get(0);
        } else
            return new TblMerFileInfoDO();
    }
    
    /**
     * 查询MCC信息
     */
    @Override
    public TblMerRelevanceMccDo queryTblMerRelevanceMccDoById(String merId) {
    	
        TblMerRelevanceMccDoExample tblMerRelevanceMccDoExample = new TblMerRelevanceMccDoExample();
        tblMerRelevanceMccDoExample.createCriteria().andMerIdEqualTo(merId);
        List<TblMerRelevanceMccDo> tblMerRelevanceMccDos = MerRelevanceMccDoMapper
                .selectByExample(tblMerRelevanceMccDoExample);
        if (tblMerRelevanceMccDos != null && tblMerRelevanceMccDos.size() != 0) {
            return tblMerRelevanceMccDos.get(0);
        }
        return null;
    }    
    
    
	@Override
	public Pagination<TblMerInfoVO> queryMerList(MerInfoForm merForm) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 商户地址码信息
	 */
	@Override
	public TblCustAddressDo queryMerAddressCodeById(String merId) {

		TblCustAddressDo CustAddressDo = new TblCustAddressDo();
		TblCustAddressDoExample CustAddressDoExample = new TblCustAddressDoExample();
		CustAddressDoExample.createCriteria().andMerIdEqualTo(merId);
		List<TblCustAddressDo> CustAddressDoList  = CustAddressDoMapper.selectByExample(CustAddressDoExample);
		if(CustAddressDoList != null && CustAddressDoList.size() > 0 ) {
			CustAddressDo = CustAddressDoList.get(0);
		}
		return CustAddressDo;

	}
	
	/**
	 * 保存快捷商户登陆信息
	 */
	@Override	
	public Boolean saveQuickMerIntoSystem(TblMerInfoDO MerInfoDo, String instCode, String agentCode){
		
		TblQuickMerRelDO QuickMerRelDO = new TblQuickMerRelDO();
		QuickMerRelDO.setMerId(MerInfoDo.getMerId());
		QuickMerRelDO.setMerName(MerInfoDo.getMerName());
		QuickMerRelDO.setStatus("");
		//检查渠道报备信息
		if(StringUtils.isBlank(instCode)){
			instCode = "00000000";
		}
		if(StringUtils.isBlank(agentCode)){
			agentCode = "000000000000000";
		}		
		//检查渠道报备信息
		int inCount = QuickMerRelDOMapper.insertSelective(QuickMerRelDO);
		if(inCount <= 0){
           return false;
		}		
				
		// 更新快捷用户登录表
		TblOnlineUserLoginDOExample OnlineUserLoginDOExample = new TblOnlineUserLoginDOExample();
		OnlineUserLoginDOExample.createCriteria().andUsrIdEqualTo(MerInfoDo.getContactMobile());
		List<TblOnlineUserLoginDO> OnlineUserLoginDOList = OnlineUserLoginDOMapper.selectByExample(OnlineUserLoginDOExample);
		if (OnlineUserLoginDOList == null || OnlineUserLoginDOList.size() == 0) {
			TblOnlineUserLoginDO OnlineUserLoginDO = new TblOnlineUserLoginDO();
			OnlineUserLoginDO.setUsrId(MerInfoDo.getContactMobile());
			OnlineUserLoginDO.setUsrName(MerInfoDo.getLegalPerson());
			OnlineUserLoginDO.setUsrPwd(CustomCredentialsMatcher.encrypt("111111"));
			OnlineUserLoginDO.setUsrRemark("快捷支付用户");
			OnlineUserLoginDO.setUsrDisableTag("0");
			OnlineUserLoginDO.setUsrEmail(MerInfoDo.getLegalPersonCertNm());
			OnlineUserLoginDO.setUsrCreateBy("merCenter");
			OnlineUserLoginDO.setUsrCreateDate(TimeUtil.getCurrentDate());
			OnlineUserLoginDO.setUsrType("QU");
			int u = OnlineUserLoginDOMapper.insertSelective(OnlineUserLoginDO);
			if (u <= 0) {
				return false;
			}
		}
		
		// 更新H5快捷用户关联表
		TblOnlineUserMapDOExample OnlineUserMapDOExample = new TblOnlineUserMapDOExample();
		OnlineUserMapDOExample.createCriteria().andMerIdEqualTo(MerInfoDo.getMerId());
		List<TblOnlineUserMapDO> OnlineUserMapDOList = OnlineUserMapDOMapper.selectByExample(OnlineUserMapDOExample);
		if (OnlineUserMapDOList == null || OnlineUserMapDOList.size() == 0) {

			TblOnlineUserMapDO OnlineUserMapDO = new TblOnlineUserMapDO();
			OnlineUserMapDO.setMerId(MerInfoDo.getMerId());
			OnlineUserMapDO.setUsrName(MerInfoDo.getContactMobile());
			int x = OnlineUserMapDOMapper.insertSelective(OnlineUserMapDO);
			if (x <= 0) {
				return false;
			}
		}
		return true;
	}	
}
