package com.allcheer.bpos.service.impl;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.InstBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.ACCOUNTDO;
import com.allcheer.bpos.entity.ACCOUNTDOExample;
import com.allcheer.bpos.entity.TblAiLoginUsrDo;
import com.allcheer.bpos.entity.TblInstKeyInfo;
import com.allcheer.bpos.entity.TblInstTransAuth;
import com.allcheer.bpos.mapper.ACCOUNTDOMapper;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.mapper.TblAiLoginUsrDoMapper;
import com.allcheer.bpos.service.AccountService;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.OpenLoginUserService;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.BposException;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.RandomString;
import com.allcheer.bpos.util.StringUtils;
import com.github.pagehelper.PageHelper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peng.ll on 2017/3/1.
 */

@Service("openLoginUserService")
public class OpenLoginUserServiceImpl implements OpenLoginUserService {

	@Autowired
	SeqMapper seqMapper;

	@Autowired
	UserService userService;

    @Autowired
    private TblAiLoginUsrDoMapper tblAiLoginUsrDoMapper;

    private static final Logger logger = LoggerFactory.getLogger(OpenLoginUserServiceImpl.class);
    
    /**
     * 开通普通商户登陆账户
     */
	@Override
	public Map openNewGeneralMer(String merId) {
		
		String userNum = String.valueOf(seqMapper.getTblBTSSysUsrIdSeq());		
		Map returnMap = new HashMap();
		int addresult = 0;

		String merLoginNum = "";
		merLoginNum = StringUtils.leftPad(userNum, 8, '0');

		try {
			// 添加大众商户登录账户
			UserBO userBO = new UserBO();
			userBO.setUsrId(userNum);
			String userName = "MER_" + merLoginNum;
			userBO.setUsrName(userName);

			CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
			String userPass = customCredentialsMatcher.encrypt("111111");
			userBO.setUsrPwd(userPass);
			userBO.setUsrDisableTag(Constant.USER_ACTIVATED);
			userBO.setUsrType(Constant.GENERAL_USER);
			Subject currentUser = SecurityUtils.getSubject();
			MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
			
			//接口调用
			if(shiroUser == null) {
				userBO.setUsrCreateBy("API_CALLER");
				userBO.setUsrUpdateBy("API_CALLER");				
			}else {
				userBO.setUsrCreateBy(shiroUser.getLoginName());
				userBO.setUsrUpdateBy(shiroUser.getLoginName());				
			}

			Map addMap = userService.addNewUsr(userBO);

			if ("操作成功!".equals(addMap.get("message")))
				addresult = 1;
			else
				addresult = 0;

			// 添加角色关联
			TblAiLoginUsrDo tblAiLoginUsrDo = new TblAiLoginUsrDo();
			tblAiLoginUsrDo.setMerId(merId);
			tblAiLoginUsrDo.setLoginUsr(userName);
			tblAiLoginUsrDo.setLoginUsrType(Constant.GENERAL_USER);
			int insertResult = 0;
			insertResult = tblAiLoginUsrDoMapper.insert(tblAiLoginUsrDo);

			if (insertResult > 0) {
				addresult = 1;
			}
			// 添加大众商户权限
			String roleId = userService.getRoleId("大众商户");			
			userService.addAcctAuthority(roleId, userBO.getUsrId());
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BposException("新增大众商户异常!");
		}

		if (addresult == 1) {
			returnMap.put("statusCode", 200);
			returnMap.put("message", "新增大众商户成功!");
		} else {
			returnMap.put("statusCode", 300);
			returnMap.put("message", "新增大众商户失败!");
		}		
		return returnMap;
	}
	
}
