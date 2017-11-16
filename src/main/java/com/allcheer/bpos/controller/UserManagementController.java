package com.allcheer.bpos.controller;

import com.allcheer.bpos.constant.Constant;
import com.allcheer.bpos.domain.RoleBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.form.AccountOauthForm;
import com.allcheer.bpos.form.UserForm;
import com.allcheer.bpos.form.UserManageForm;
import com.allcheer.bpos.mapper.SeqMapper;
import com.allcheer.bpos.service.MerchInfoService;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.RoleService;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.Bean2Map;
import com.allcheer.bpos.util.Pagination;
import com.allcheer.bpos.util.StringUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fireWorks on 2016/2/26.
 */
@Controller
public class UserManagementController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SeqMapper seqMapper;

	@Autowired
	MerchInfoService merchInfoService;

	private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@RequestMapping(value = "/userManagement/goToChangepwdPage", method = RequestMethod.GET)
	public String goToChangepwdPage(@ModelAttribute("userForm") UserForm userForm) {
		Subject currentUser = SecurityUtils.getSubject();
		MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
		userForm.setUserid(shiroUser.getId());
		userForm.setUsername(shiroUser.getLoginName());

		return "/changepwd";
	}

	@ResponseBody
	@RequestMapping(value = "/userManagement/changepwd", method = RequestMethod.POST)
	public Map changepwd(@ModelAttribute("userForm") UserForm userForm) {
		String userid = userForm.getUserid();
		String username = userForm.getUsername();
		String oldpassword = userForm.getOldpassword();
		String newpassword = userForm.getPass();
		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
		oldpassword = customCredentialsMatcher.encrypt(oldpassword);

		newpassword = customCredentialsMatcher.encrypt(newpassword);

		UserBO userBO = new UserBO();
		userBO.setPass(newpassword);
		userBO.setUsrPwd(oldpassword);
		userBO.setUsrId(userid);
		userBO.setUsrName(username);

		return userService.updatePwd(userBO);

	}

	/**
	 * 
	 * @param userForm
	 * @return
	 */
	@RequestMapping(value = "/userManagement/addnewpage", method = RequestMethod.GET)
	public String goAddNewPage(@ModelAttribute("userForm") UserForm userForm) {

		return "addnewuser";
	}

	/**
	 * 添加新的商户
	 * 
	 * @param userForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userManagement/addnew", method = RequestMethod.POST)
	public Map<String, Object> addNewUser(@ModelAttribute("userManageForm") UserManageForm userManageForm) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		String userName = userManageForm.getUsername();
		String userPass = userManageForm.getPass();
		String instId = userManageForm.getInstId();
		String agentId = userManageForm.getAgentId();
		String merId = userManageForm.getMerId();

		int i = 0;
		String merCatagory = "";
		String checkMer = "";
		Map<String, String> ifMerCorrect = new HashMap<String, String>();
		if (StringUtils.isNotBlank(instId)) {
			i = i + 1;
			merCatagory = Constant.INST_USER;
			checkMer = instId;
			ifMerCorrect = merchInfoService.InstAgentMer(instId);
			if (!ifMerCorrect.containsKey("INST")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联机构不存在");
				return resultMap;
			}
		}
		if (StringUtils.isNotBlank(agentId)) {
			i = i + 1;
			merCatagory = Constant.AGENT_USER;
			checkMer = agentId;
			ifMerCorrect = merchInfoService.InstAgentMer(agentId);
			if (!ifMerCorrect.containsKey("AGENT")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联代理商不存在");
				return resultMap;
			}
		}
		if (StringUtils.isNotBlank(merId)) {
			i = i + 1;
			merCatagory = Constant.GENERAL_USER;
			checkMer = merId;
			ifMerCorrect = merchInfoService.InstAgentMer(merId);
			if (!ifMerCorrect.containsKey("MER")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联商户不存在");
				return resultMap;
			}
		}
		if (i > 1) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "只能关联一个商户");
			return resultMap;
		}

		UserBO userBO = new UserBO();
		userBO.setUsrId(String.valueOf(seqMapper.getTblBTSSysUsrIdSeq()));
		userBO.setUsrName(userName);

		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();
		userPass = customCredentialsMatcher.encrypt(userPass);
		userBO.setUsrPwd(userPass);
		userBO.setUsrDisableTag("1");
		userBO.setUsrType("0");

		Subject currentUser = SecurityUtils.getSubject();
		MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) currentUser.getPrincipal();
		userBO.setUsrCreateBy(shiroUser.getLoginName());
		userBO.setUsrUpdateBy(shiroUser.getLoginName());

		userBO.setAgentId(agentId);
		userBO.setInstId(instId);
		userBO.setMerId(merId);
		if (i > 0) {
			userBO.setBindMer(true);
			String bindLoginUser = userService.checkIfMerIsAlreadyBinded(checkMer, merCatagory);
			if (StringUtils.isNotBlank(bindLoginUser)) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联商户已经绑定在" + bindLoginUser);
				return resultMap;
			}
		}
		resultMap = userService.addNewUsr(userBO);

		return resultMap;

	}

	/**
	 * 查询所有商户
	 * 
	 * @param userManageForm
	 * @return
	 */
	@RequestMapping(value = "/userManagement/query_all_user", method = RequestMethod.GET)
	public String queryAllUser(@ModelAttribute("userManageForm") UserManageForm userManageForm) {
		UserBO userBO = new UserBO();
		String pageCurrent = userManageForm.getPageCurrent();
		String pageSize = userManageForm.getPageSize();

		userBO.setPageCurrent(Integer.valueOf(pageCurrent));
		userBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<UserBO> userBOPagination = userService.getAllUsr(userBO);
		userManageForm.setPagination(userBOPagination);

		return "usermanalist";

	}

	@RequestMapping(value = "/userManagement/query_a_user", method = RequestMethod.POST)
	public String queryTheUser(@ModelAttribute("userManageForm") UserManageForm userManageForm) {
		UserBO userBO = new UserBO();
		if (!(userManageForm.getUsername() == null || userManageForm.getUsername().trim().equals(""))) {
			userBO.setUsrName(userManageForm.getUsername());
		}
		if (userManageForm.getUsrRemark() != null && !userManageForm.getUsrRemark().trim().equals("")) {
			userBO.setUsrRemark(userManageForm.getUsrRemark());
		}
		if (userManageForm.getUsrDisableTag() != null && !userManageForm.getUsrDisableTag().trim().equals("")) {
			userBO.setUsrDisableTag(userManageForm.getUsrDisableTag());
		}
		if (userManageForm.getMerNumber() != null && !userManageForm.getMerNumber().trim().equals("")) {
			userBO.setMerNumber(userManageForm.getMerNumber());
		}
		String pageCurrent = userManageForm.getPageCurrent();
		String pageSize = userManageForm.getPageSize();

		userBO.setPageCurrent(Integer.valueOf(pageCurrent));
		userBO.setPageSize(Integer.valueOf(pageSize));

		Pagination<UserBO> userBOPagination = userService.getTheUsr(userBO);
		userManageForm.setPagination(userBOPagination);

		return "usermanalist";

	}

	@RequestMapping(value = "/userManagement/edit", method = RequestMethod.GET)
	public String modifyAcctDetails(HttpServletRequest request,
			@ModelAttribute("accountOauthForm") AccountOauthForm accountOauthForm) {

		String id = request.getParameter("id");
		UserBO userBO = userService.getById(id);

		accountOauthForm.setUsrId(id);
		accountOauthForm.setUsrName(userBO.getUsrName());
		if (userBO.getUsrDisableTag().trim().equals("1")) {
			accountOauthForm.setUsrDisableTag("启用");

		} else {
			accountOauthForm.setUsrDisableTag("禁用");
		}
		Pagination<RoleBO> roleBOPagination = roleService.getRoleList(id);
		accountOauthForm.setPagination(roleBOPagination);

		return "modifyacctoauth";
	}

	@ResponseBody
	@RequestMapping(value = "/userManagement/acctenable", method = RequestMethod.POST)
	public Map acctEnable(@ModelAttribute("accountOauthForm") AccountOauthForm accountOauthForm,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		Map resultMap = new HashMap();
		try {
			resultMap = userService.setAcctEnable(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "操作失败!");

		}
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/userManagement/acctdisable", method = RequestMethod.POST)
	public Map acctDisable(@ModelAttribute("accountOauthForm") AccountOauthForm accountOauthForm,
			HttpServletRequest request) {
		String id = request.getParameter("id");
		Map resultMap = new HashMap();
		try {
			resultMap = userService.setAcctDisable(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "操作失败!");

		}
		return resultMap;

	}

	@ResponseBody
	@RequestMapping(value = "/userManagement/addauthority", method = RequestMethod.POST)
	public Map addAuthorityForUsr(@ModelAttribute("accountOauthForm") AccountOauthForm accountOauthForm,
			HttpServletRequest request) {
		String roleId = request.getParameter("id");
		String userId = request.getParameter("uid");
		Map resultMap = new HashMap();
		try {
			resultMap = userService.addAcctAuthority(roleId, userId);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "操作失败!");

		}
		return resultMap;

	}

	@ResponseBody
	@RequestMapping(value = "/userManagement/cancelauthority", method = RequestMethod.POST)
	public Map cancelAuthorityForUsr(@ModelAttribute("accountOauthForm") AccountOauthForm accountOauthForm,
			HttpServletRequest request) {
		String roleId = request.getParameter("id");
		String userId = request.getParameter("uid");
		Map resultMap = new HashMap();
		try {
			resultMap = userService.cancelAcctAuthority(roleId, userId);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			resultMap.put("statusCode", 300);
			resultMap.put("message", "操作失败!");

		}
		return resultMap;

	}

	/**
	 * 绑定商户和用户
	 * 
	 * @param userForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userManagement/goBindWithMer", method = RequestMethod.GET)
	public ModelAndView ShowBindUserAndMer(HttpServletRequest request,
			@ModelAttribute("userManageForm") UserManageForm userManageForm, ModelAndView model) {

		String userId = request.getParameter("id");
		String merNumber = request.getParameter("merNumber");
		Map<String, String> ifMerCorrect = new HashMap<String, String>();

		logger.info("判断商户类型:" + merNumber);
		ifMerCorrect = merchInfoService.InstAgentMer(merNumber);
		if (ifMerCorrect.containsKey("INST")) {
			userManageForm.setInstId(merNumber);
		}
		if (ifMerCorrect.containsKey("AGENT")) {
			userManageForm.setAgentId(merNumber);
		}
		if (ifMerCorrect.containsKey("MER")) {
			userManageForm.setMerId(merNumber);
		}
		userManageForm.setBindUser(userId);
		model.getModel().put("userManageForm", userManageForm);
		model.setViewName("/userManagement/bind_user_mer");
		return model;
	}

	/**
	 * 绑定商户和用户
	 * 
	 * @param userForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userManagement/goBind", method = RequestMethod.POST)
	public Map<String, Object> bindUserAndMer(HttpServletRequest request,
			@ModelAttribute("userManageForm") UserManageForm userManageForm, ModelAndView model) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String instId = userManageForm.getInstId();
		String agentId = userManageForm.getAgentId();
		String merId = userManageForm.getMerId();

		logger.info("只允许绑定一个是商户");
		int i = 0;
		String merCatagory = "";
		String checkMer = "";
		Map<String, String> ifMerCorrect = new HashMap<String, String>();
		if (StringUtils.isNotBlank(instId)) {
			i = i + 1;
			merCatagory = Constant.INST_USER;
			checkMer = instId;
			ifMerCorrect = merchInfoService.InstAgentMer(instId);
			if (!ifMerCorrect.containsKey("INST")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联机构不存在");
				return resultMap;
			}
		}
		if (StringUtils.isNotBlank(agentId)) {
			i = i + 1;
			merCatagory = Constant.AGENT_USER;
			checkMer = agentId;
			ifMerCorrect = merchInfoService.InstAgentMer(agentId);
			if (!ifMerCorrect.containsKey("AGENT")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联代理商不存在");
				return resultMap;
			}
		}
		if (StringUtils.isNotBlank(merId)) {
			i = i + 1;
			merCatagory = Constant.GENERAL_USER;
			checkMer = merId;
			ifMerCorrect = merchInfoService.InstAgentMer(merId);
			if (!ifMerCorrect.containsKey("MER")) {
				resultMap.put("statusCode", 300);
				resultMap.put("message", "关联商户不存在");
				return resultMap;
			}
		}
		if (i > 1) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "只能关联一个商户");
			logger.error("只允许绑定一个是商户");
			return resultMap;
		}

		Boolean bindResult = userService.goBindMer(userManageForm);

		if (bindResult) {
			resultMap.put("statusCode", 200);
			resultMap.put("message", "绑定更新成功");
		} else {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "绑定更新失败");
		}
		return resultMap;
	}

}
