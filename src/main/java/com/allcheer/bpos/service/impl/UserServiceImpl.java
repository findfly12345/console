package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.*;
import com.allcheer.bpos.entity.TblAiLoginUsrDoExample.Criteria;
import com.allcheer.bpos.form.UserManageForm;
import com.allcheer.bpos.mapper.TblAiLoginUsrDoMapper;
import com.allcheer.bpos.mapper.TblBTSSysRoleDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysUsrDOMapper;
import com.allcheer.bpos.mapper.TblBTSSysUsrRoleDOMapper;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.util.DateUtil;
import com.allcheer.bpos.util.Pagination;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by APPLE on 15/12/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    TblBTSSysUsrDOMapper tblBTSSysUsrDOMapper;

    @Autowired
    TblBTSSysUsrRoleDOMapper tblBTSSysUsrRoleDOMapper;

    @Autowired
    TblBTSSysRoleDOMapper tblBTSSysRoleDOMapper;

    @Autowired
    TblAiLoginUsrDoMapper tblAiLoginUsrDoMapper;
    
    @Autowired
    MerchInfoService merchInfoService;
    

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Override
    public UserBO get(String username) {
        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        tblBTSSysUsrDOExample.createCriteria().andUsrNameEqualTo(username);
        List<TblBTSSysUsrDO> tblBTSSysUsrDOList = new ArrayList<>();
        try {
            tblBTSSysUsrDOList  = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return convertUsrDOToUserBO(tblBTSSysUsrDOList.get(0));
    }

    @Override
    public UserBO getById(String usrId) {
        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        tblBTSSysUsrDOExample.createCriteria().andUsrIdEqualTo(usrId);
        List<TblBTSSysUsrDO> tblBTSSysUsrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);
        return convertUsrDOToUserBO(tblBTSSysUsrDOList.get(0));
    }

    @Override
    public Map updatePwd(UserBO userBO) {
        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        tblBTSSysUsrDOExample.createCriteria().andUsrIdEqualTo(userBO.getUsrId()).andUsrNameEqualTo(userBO.getUsrName()).andUsrPwdEqualTo(userBO.getUsrPwd());
        List<TblBTSSysUsrDO> tblBTSSysUsrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);
        Map resultMap = new HashMap();
        if(tblBTSSysUsrDOList == null || tblBTSSysUsrDOList.size() == 0) {
            resultMap.put("statusCode", 300);
            resultMap.put("message", "旧密码错误!");
            return resultMap;
        }
        TblBTSSysUsrDO tblBTSSysUsrDO = new TblBTSSysUsrDO();
        tblBTSSysUsrDO.setUsrId(userBO.getUsrId());
        tblBTSSysUsrDO.setUsrPwd(userBO.getPass());
        tblBTSSysUsrDOMapper.updateByPrimaryKeySelective(tblBTSSysUsrDO);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");
        return resultMap;
    }

    @Override
    public Map<String, Object> addNewUsr(UserBO userBO){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        TblBTSSysUsrDO refSysUsrDO = new TblBTSSysUsrDO();
        refSysUsrDO.setUsrPwd(userBO.getUsrPwd());
        refSysUsrDO.setUsrName(userBO.getUsrName());
        refSysUsrDO.setUsrId(userBO.getUsrId());
        refSysUsrDO.setUsrDisableTag(userBO.getUsrDisableTag());
        
		if(StringUtils.isNotBlank(userBO.getMerId())){		
			userBO.setUsrType(Constant.GENERAL_USER);
		} else if(StringUtils.isNotBlank(userBO.getAgentId())){		
			userBO.setUsrType(Constant.AGENT_USER);
		} else if(StringUtils.isNotBlank(userBO.getInstId())){		
			userBO.setUsrType(Constant.INST_USER);
		} else{
			userBO.setUsrType("0");
		}

        if(userBO.getUsrType() != null && !userBO.getUsrType().trim().equals("")){
            refSysUsrDO.setUsrType(userBO.getUsrType());
            TblBTSSysRoleDOExample tblBTSSysRoleDOExample = new TblBTSSysRoleDOExample();
            tblBTSSysRoleDOExample.createCriteria().andRoleNameEqualTo(userBO.getUsrType());
            List<TblBTSSysRoleDO> roleDOList = tblBTSSysRoleDOMapper.selectByExample(tblBTSSysRoleDOExample);
            if(roleDOList!= null && roleDOList.size()!=0){
                try {
                    addAcctAuthority(roleDOList.get(0).getRoleId(), refSysUsrDO.getUsrId());
                }catch (DataIntegrityViolationException ex){
                    logger.info("角色已分配:" + ex.getMessage());
                    resultMap.put("statusCode", 300);
                    resultMap.put("message", "角色已分配!");
                } catch(Exception ex){
                    resultMap.put("statusCode", 300);
                    resultMap.put("message", "账号类型失败!");
                    return resultMap;
                }
            }
        }
        refSysUsrDO.setUsrRemark(userBO.getUsrRemark());
        refSysUsrDO.setUsrCreateDate(DateUtil.currentTimestamp());
        refSysUsrDO.setUsrUpdateDate(DateUtil.currentTimestamp());
        refSysUsrDO.setUsrCreateBy(userBO.getUsrCreateBy());
        refSysUsrDO.setUsrUpdateBy(userBO.getUsrUpdateBy());

        int rt = tblBTSSysUsrDOMapper.insert(refSysUsrDO);
        if (rt == 0){ 
            resultMap.put("statusCode", 300);
            resultMap.put("message", "添加商户操作失败!");
            return resultMap;
        }
        
        if (StringUtils.isNotBlank(userBO.getMerId()) || StringUtils.isNotBlank(userBO.getAgentId()) || 
            StringUtils.isNotBlank(userBO.getInstId())) {
        	logger.info("进行商户关联绑定");
			// 添加角色关联
			TblAiLoginUsrDo tblAiLoginUsrDo = new TblAiLoginUsrDo();
			tblAiLoginUsrDo.setMerId(userBO.getMerId());
			tblAiLoginUsrDo.setAgentId(userBO.getAgentId());
			tblAiLoginUsrDo.setInstId(userBO.getInstId());
			tblAiLoginUsrDo.setLoginUsr(userBO.getUsrName());
			
			if(StringUtils.isNotBlank(userBO.getMerId())){		
			   tblAiLoginUsrDo.setLoginUsrType(Constant.GENERAL_USER);
			}
			if(StringUtils.isNotBlank(userBO.getAgentId())){		
				   tblAiLoginUsrDo.setLoginUsrType(Constant.AGENT_USER);
			}			
			if(StringUtils.isNotBlank(userBO.getInstId())){		
				   tblAiLoginUsrDo.setLoginUsrType(Constant.INST_USER);
			}				
			int insertResult = 0;
			insertResult = tblAiLoginUsrDoMapper.insert(tblAiLoginUsrDo);

			if (insertResult > 0) {
	            resultMap.put("statusCode", 200);
	            resultMap.put("message", "添加商户完成");
			} else{
	            resultMap.put("statusCode", 300);
	            resultMap.put("message", "添加商户失败");				
			}
        }else {
            resultMap.put("statusCode", 200);
            resultMap.put("message", "添加商户操作成功");
            return resultMap;
        }
        return resultMap;
    }

    @Override
    public Pagination<UserBO> getAllUsr(UserBO userBO){
        List<UserBO> userList = new ArrayList<>();
        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        tblBTSSysUsrDOExample.createCriteria();
        int count = tblBTSSysUsrDOMapper.countByExample(tblBTSSysUsrDOExample);
        Pagination pagination = new Pagination(count, userBO.getPageCurrent(), userBO.getPageSize());
        PageHelper.startPage(userBO.getPageCurrent(), userBO.getPageSize());
        List<TblBTSSysUsrDO> usrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);
        for (TblBTSSysUsrDO usrDO : usrDOList){
            UserBO user = new UserBO();
            user.setUsrId(usrDO.getUsrId());
            user.setUsrName(usrDO.getUsrName());
            user.setUsrPwd(usrDO.getUsrPwd());
            user.setUsrRemark(usrDO.getUsrRemark());
            if(usrDO.getUsrDisableTag().trim().equals("1")){
                user.setUsrDisableTag("启用");

            }else{
                user.setUsrDisableTag("禁用");
            }
            user.setUsrEmail(usrDO.getUsrEmail());
            String merNumber = getMerNumberByUserId(usrDO.getUsrName());
            user.setMerNumber(merNumber);
            userList.add(user);
        }
        pagination.addResult(userList);
        return pagination;

    }

    
    
    
    
    
    
    @Override
    public Pagination<UserBO> getTheUsr(UserBO userBO){

    	if(StringUtils.isNotBlank(userBO.getMerNumber())){
    		return getTheUsrByMerNumber(userBO);
    	}
    	
        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        TblBTSSysUsrDOExample.Criteria cri = tblBTSSysUsrDOExample.createCriteria();
        if (!(userBO.getUsrName() == null || userBO.getUsrName().trim().equals(""))) {
            cri.andUsrNameLike("%"+userBO.getUsrName()+"%");
        }
        if (userBO.getUsrRemark()!=null && !userBO.getUsrRemark().trim().equals("")){
            cri.andUsrRemarkLike("%"+userBO.getUsrRemark()+"%");
        }
        if(userBO.getUsrDisableTag()!=null && !userBO.getUsrDisableTag().trim().equals("")){
            cri.andUsrDisableTagEqualTo(userBO.getUsrDisableTag());
        }

        int count = tblBTSSysUsrDOMapper.countByExample(tblBTSSysUsrDOExample);
        Pagination pagination = new Pagination(count, userBO.getPageCurrent(), userBO.getPageSize());
        PageHelper.startPage(userBO.getPageCurrent(), userBO.getPageSize());
        List<TblBTSSysUsrDO> usrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);

        List<UserBO> userBOList = new ArrayList<>();
        for(TblBTSSysUsrDO usrDO:usrDOList ) {
            UserBO user = new UserBO();
            user.setUsrId(usrDO.getUsrId());
            user.setUsrName(usrDO.getUsrName());
            user.setUsrPwd(usrDO.getUsrPwd());
            user.setUsrRemark(usrDO.getUsrRemark());
            if(usrDO.getUsrDisableTag().trim().equals("1")){
                user.setUsrDisableTag("启用");

            }else{
                user.setUsrDisableTag("禁用");
            }
 
            String merNum = getMerNumberByUserId(usrDO.getUsrName());
            user.setMerNumber(merNum);
            user.setUsrEmail(usrDO.getUsrEmail());
            userBOList.add(user);
        }
        pagination.addResult(userBOList);

        return pagination;

    }


    /**
     * 根据商户号获取账户信息
     * @param userBO
     * @return
     */
    public Pagination<UserBO> getTheUsrByMerNumber(UserBO userBO){
	    	
        logger.info("查询商户号是:" + userBO.getMerNumber());    	
        TblAiLoginUsrDoExample AiLoginUsrDoExample = new TblAiLoginUsrDoExample();
        
        Map<String, String> merTypes = merchInfoService.InstAgentMer(userBO.getMerNumber());
        if(merTypes.containsKey("INST")){
        	AiLoginUsrDoExample.createCriteria().andInstIdEqualTo(userBO.getMerNumber());
        } else if (merTypes.containsKey("AGENT")) {
        	AiLoginUsrDoExample.createCriteria().andAgentIdEqualTo(userBO.getMerNumber());
        } else {
        	AiLoginUsrDoExample.createCriteria().andMerIdEqualTo(userBO.getMerNumber());
        }
      
        String loginUser = "";
        List<TblAiLoginUsrDo> AiLoginUsrDoList = tblAiLoginUsrDoMapper.selectByExample(AiLoginUsrDoExample);
        
        if(AiLoginUsrDoList != null && AiLoginUsrDoList.size() > 0){
        	loginUser = AiLoginUsrDoList.get(0).getLoginUsr();
        }

        TblBTSSysUsrDOExample tblBTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        TblBTSSysUsrDOExample.Criteria cri = tblBTSSysUsrDOExample.createCriteria();
        if (StringUtils.isNotBlank(loginUser)) {
            cri.andUsrNameEqualTo(loginUser);
        }
    
        int count = tblBTSSysUsrDOMapper.countByExample(tblBTSSysUsrDOExample);
        Pagination pagination = new Pagination(count, userBO.getPageCurrent(), userBO.getPageSize());
        PageHelper.startPage(userBO.getPageCurrent(), userBO.getPageSize());
        List<TblBTSSysUsrDO> usrDOList = tblBTSSysUsrDOMapper.selectByExample(tblBTSSysUsrDOExample);

        List<UserBO> userBOList = new ArrayList<>();
        for(TblBTSSysUsrDO usrDO:usrDOList ) {
            UserBO user = new UserBO();
            user.setUsrId(usrDO.getUsrId());
            user.setUsrName(usrDO.getUsrName());
            user.setUsrPwd(usrDO.getUsrPwd());
            user.setUsrRemark(usrDO.getUsrRemark());
            if(usrDO.getUsrDisableTag().trim().equals("1")){
                user.setUsrDisableTag("启用");

            }else{
                user.setUsrDisableTag("禁用");
            }
            String merNum = getMerNumberByUserId(usrDO.getUsrName());
            user.setMerNumber(merNum);            
            user.setUsrEmail(usrDO.getUsrEmail());
            userBOList.add(user);
        }
        pagination.addResult(userBOList);

        return pagination;

    }
    

    private UserBO convertUsrDOToUserBO(TblBTSSysUsrDO tblBTSSysUsrDO) {
        UserBO userBO = new UserBO();
        userBO.setUsrId(tblBTSSysUsrDO.getUsrId());
        userBO.setUsrName(tblBTSSysUsrDO.getUsrName());
        userBO.setUsrPwd(tblBTSSysUsrDO.getUsrPwd());
        userBO.setUsrDisableTag(tblBTSSysUsrDO.getUsrDisableTag());
        userBO.setUsrType(tblBTSSysUsrDO.getUsrType());
        
        return userBO;
    }

    @Override
    public Map setAcctEnable(String usrId) throws Exception{
        Map resultMap = new HashMap();
        TblBTSSysUsrDOExample usrDOExample = new TblBTSSysUsrDOExample();
        usrDOExample.createCriteria().andUsrIdEqualTo(usrId);
        TblBTSSysUsrDO usrDO = new TblBTSSysUsrDO();
        usrDO.setUsrDisableTag("1");
        tblBTSSysUsrDOMapper.updateByExampleSelective(usrDO,usrDOExample);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;

    }

    @Override
    public Map setAcctDisable(String usrId) throws Exception{
        Map resultMap = new HashMap();
        TblBTSSysUsrDOExample usrDOExample = new TblBTSSysUsrDOExample();
        usrDOExample.createCriteria().andUsrIdEqualTo(usrId);
        TblBTSSysUsrDO usrDO = new TblBTSSysUsrDO();
        usrDO.setUsrDisableTag("0");
        tblBTSSysUsrDOMapper.updateByExampleSelective(usrDO,usrDOExample);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;

    }

    @Override
    public Map cancelAcctAuthority(String id,String uid) throws Exception{

        Map resultMap = new HashMap();
        TblBTSSysUsrRoleDOKey usrRoleDOKey = new TblBTSSysUsrRoleDOKey();
        usrRoleDOKey.setUsrId(uid);
        usrRoleDOKey.setRoleId(id);
        tblBTSSysUsrRoleDOMapper.deleteByPrimaryKey(usrRoleDOKey);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;
    }

    @Override
    public Map addAcctAuthority(String id,String uid){
        Map resultMap = new HashMap();

        TblBTSSysRoleDOExample roleDOExample = new TblBTSSysRoleDOExample();
        TblBTSSysRoleDO roleDO = tblBTSSysRoleDOMapper.selectByPrimaryKey(id);

        TblBTSSysUsrRoleDO usrRoleDO = new TblBTSSysUsrRoleDO();
        usrRoleDO.setUsrId(uid);
        usrRoleDO.setRoleId(id);
        usrRoleDO.setUsrRoleRemark(roleDO.getRoleName());
        tblBTSSysUsrRoleDOMapper.insert(usrRoleDO);
        resultMap.put("statusCode", 200);
        resultMap.put("message", "操作成功!");

        return resultMap;
    }

    @Override
    public  String getRoleId(String roleName){

    	String returnStr = "";
    	
        TblBTSSysRoleDO roleDO = tblBTSSysRoleDOMapper.selectByRoleName(roleName);
        
        if (roleDO == null) {
           return returnStr;
        }
        
        returnStr = roleDO.getRoleId();

        return returnStr;
    }  
    
    /**
     * 根据登陆账号获取对应的商户号
     * @param userId
     * @return
     */
    public String getMerNumberByUserId (String userId) {
    	
    	String merNumber = "";

    	TblAiLoginUsrDo AiLoginUsrDo = tblAiLoginUsrDoMapper.selectByPrimaryKey(userId);

    	if(AiLoginUsrDo != null) {
    		
    		if(AiLoginUsrDo.getLoginUsrType().equals("1")){
    			merNumber = AiLoginUsrDo.getAgentId();
    		}
    		if(AiLoginUsrDo.getLoginUsrType().equals("3")){
    			merNumber = AiLoginUsrDo.getMerId();
    		}
    		if(AiLoginUsrDo.getLoginUsrType().equals("2")){
    			merNumber = AiLoginUsrDo.getInstId();
    		}    		
    	}
    	return merNumber;    	
    }
    
 
    /**
     * 检查关联商户是否已经绑定过
     * @param userId
     * @param  merType: 1
     * @return
     */
    @Override
    public String checkIfMerIsAlreadyBinded (String merNumber, String merCatatory) {
    	
    	String bindLoginUser = "";
    	logger.info("检查关联商户是否已经绑定过");
    	TblAiLoginUsrDoExample AiLoginUsrDoExample = new TblAiLoginUsrDoExample();
    	Criteria cri = AiLoginUsrDoExample.createCriteria();    	
    	if(merCatatory.equals(Constant.AGENT_USER)){
    		cri.andAgentIdEqualTo(merNumber);
    	}
    	if(merCatatory.equals(Constant.INST_USER)){
    		cri.andInstIdEqualTo(merNumber);
    	}    	
    	if(merCatatory.equals(Constant.GENERAL_USER)){
    		cri.andMerIdEqualTo(merNumber);
    	}     	
    	List<TblAiLoginUsrDo> AiLoginUsrList = tblAiLoginUsrDoMapper.selectByExample(AiLoginUsrDoExample);

    	if(AiLoginUsrList != null && AiLoginUsrList.size() > 0) {
    		bindLoginUser = AiLoginUsrList.get(0).getLoginUsr();
    	}
    	return bindLoginUser;    	
    }
    
    /**
     * 更新绑定信息
     * @param userManageForm
     * @return
     */
    @Override
    public Boolean goBindMer (UserManageForm userManageForm) {
    	
    	logger.info("绑定商户用户");
    	TblAiLoginUsrDoExample AiLoginUsrDoExample = new TblAiLoginUsrDoExample();
    	AiLoginUsrDoExample.createCriteria().andLoginUsrEqualTo(userManageForm.getBindUser());
    	
    	logger.info("确认是否存在");
    	TblAiLoginUsrDo AiLoginUsrDo = new TblAiLoginUsrDo();
        TblBTSSysUsrDO BTSSysUsrDo = new TblBTSSysUsrDO();
    	AiLoginUsrDo = tblAiLoginUsrDoMapper.selectByPrimaryKey(userManageForm.getBindUser());
    	if(AiLoginUsrDo == null) {
    		TblAiLoginUsrDo AiLoginUsrDoInsert = new TblAiLoginUsrDo();
    		AiLoginUsrDoInsert.setLoginUsr(userManageForm.getBindUser());
    		AiLoginUsrDoInsert.setAgentId(userManageForm.getAgentId());
    		AiLoginUsrDoInsert.setInstId(userManageForm.getInstId());
    		AiLoginUsrDoInsert.setMerId(userManageForm.getMerId());
        	if(StringUtils.isNotBlank(userManageForm.getAgentId())){
        		AiLoginUsrDoInsert.setLoginUsrType(Constant.AGENT_USER);
        		BTSSysUsrDo.setUsrType(Constant.AGENT_USER);
        	} else if(StringUtils.isNotBlank(userManageForm.getInstId())){
        		AiLoginUsrDoInsert.setLoginUsrType(Constant.INST_USER);
        		BTSSysUsrDo.setUsrType(Constant.INST_USER);
        	} else if(StringUtils.isNotBlank(userManageForm.getMerId())){
        		AiLoginUsrDoInsert.setLoginUsrType(Constant.GENERAL_USER);
        		BTSSysUsrDo.setUsrType(Constant.GENERAL_USER);
        	} else {
        		AiLoginUsrDoInsert.setLoginUsrType("0");
        		BTSSysUsrDo.setUsrType("0");       		
        	}
        	int cb = tblAiLoginUsrDoMapper.insert(AiLoginUsrDoInsert);
        	if(cb > 0){
        		logger.info("绑定成功");
        		return true;
        	}
    	}else{
    		TblAiLoginUsrDo AiLoginUsrDoUpdate = new TblAiLoginUsrDo();    		
    		AiLoginUsrDoUpdate.setLoginUsr(userManageForm.getBindUser());
    		AiLoginUsrDoUpdate.setAgentId(userManageForm.getAgentId());
    		AiLoginUsrDoUpdate.setInstId(userManageForm.getInstId());
    		AiLoginUsrDoUpdate.setMerId(userManageForm.getMerId());
        	if(StringUtils.isNotBlank(userManageForm.getAgentId())){
        		AiLoginUsrDoUpdate.setLoginUsrType(Constant.AGENT_USER);
        		BTSSysUsrDo.setUsrType(Constant.AGENT_USER);
        	} else if(StringUtils.isNotBlank(userManageForm.getInstId())){
        		AiLoginUsrDoUpdate.setLoginUsrType(Constant.INST_USER);
        		BTSSysUsrDo.setUsrType(Constant.INST_USER);
        	} else if(StringUtils.isNotBlank(userManageForm.getMerId())){
        		AiLoginUsrDoUpdate.setLoginUsrType(Constant.GENERAL_USER);
        		BTSSysUsrDo.setUsrType(Constant.GENERAL_USER);
        	} else {
        		AiLoginUsrDoUpdate.setLoginUsrType("0");
        		BTSSysUsrDo.setUsrType("0");       		
        	}
        	int cd = tblAiLoginUsrDoMapper.updateByExampleSelective(AiLoginUsrDoUpdate, AiLoginUsrDoExample);
        	if(cd > 0){
        		logger.info("更新绑定成功");
        		return true;
        	}
        	
    	}
    	
    	logger.info("更新用户权限类型"); 
        TblBTSSysUsrDOExample BTSSysUsrDOExample = new TblBTSSysUsrDOExample();
        BTSSysUsrDOExample.createCriteria().andUsrNameEqualTo(userManageForm.getBindUser());
        List<TblBTSSysUsrDO> BTSSysUsrDOList = tblBTSSysUsrDOMapper.selectByExample(BTSSysUsrDOExample);
        if(BTSSysUsrDOList != null){        	
        	int count = tblBTSSysUsrDOMapper.updateByExample(BTSSysUsrDo, BTSSysUsrDOExample);
        	if (count > 0 ){
        		logger.info("更新用户权限");
        	}
        }

    	return false;
    }
    
}
