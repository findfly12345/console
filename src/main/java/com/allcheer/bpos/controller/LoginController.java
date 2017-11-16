package com.allcheer.bpos.controller;

import com.allcheer.bpos.domain.MenuBO;
import com.allcheer.bpos.domain.UserBO;
import com.allcheer.bpos.entity.vo.UserVo;
import com.allcheer.bpos.form.LoginForm;
import com.allcheer.bpos.service.MenuService;
import com.allcheer.bpos.service.MonitorRealm;
import com.allcheer.bpos.service.MonitorRealm.ShiroUser;
import com.allcheer.bpos.service.UserService;
import com.allcheer.bpos.shiro.CaptchaUsernamePasswordToken;
import com.allcheer.bpos.shiro.CustomCredentialsMatcher;
import com.allcheer.bpos.util.SystemConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	MenuService menuService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView login(@ModelAttribute("loginForm") LoginForm loginForm, HttpSession session,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String username = loginForm.getUsername();
		String host = request.getRemoteHost();
		loginForm.setHost(host);
		Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			resultMap = login(currentUser, loginForm);
		} else {// 重复登录
			ShiroUser shiroUser = (ShiroUser) currentUser.getPrincipal();
			currentUser.logout();
			resultMap = login(currentUser, loginForm);
		}

		logger.debug("statusCode: " + resultMap.get("statusCode"));
		logger.debug("message: " + resultMap.get("message"));

		if ((int) resultMap.get("statusCode") == 200) {
			UserBO userBO = userService.get(username);
			session.setAttribute(SystemConstant.USER_SESSION_KEY, userBO);

			if ("0".equals(userBO.getUsrType())) {
                logger.info("管理控制台用户登录！");
			} else {
				resultMap.put("message", "验证码错误!");
				resultMap.remove("message");
				resultMap.put("message", "账户类型错误!");
				return new ModelAndView("/login", resultMap);
			}

			return new ModelAndView(new RedirectView(request.getContextPath() + "/main"));

		} else {
			return new ModelAndView("/login", resultMap);
		}

	}

	private Map login(Subject currentUser, LoginForm loginForm) {
		Map resultMap = new HashMap<>();
		CustomCredentialsMatcher customCredentialsMatcher = new CustomCredentialsMatcher();

		CaptchaUsernamePasswordToken captchaUsernamePasswordToken = new CaptchaUsernamePasswordToken(
				loginForm.getUsername(), loginForm.getPassword(), true, loginForm.getHost(), loginForm.getCaptcha());
		try {
			currentUser.login(captchaUsernamePasswordToken);

			resultMap.put("statusCode", 200);
			resultMap.put("message", "登录成功!");
		} catch (UnknownAccountException uae) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "用户账户不存在!");
		} catch (IncorrectCredentialsException ice) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "用户密码错误!");
		} catch (LockedAccountException lae) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "账户已被锁定!");
		} catch (AuthenticationException ae) {
			resultMap.put("statusCode", 300);
			resultMap.put("message", "验证码错误!");
		}

		return resultMap;
	}

	@RequiresAuthentication
	@RequestMapping(value = "/logout")
	public String logOut(HttpSession session, HttpServletRequest request) {
		UserVo user = (UserVo) session.getAttribute(SystemConstant.USER_SESSION_KEY);
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
			logger.debug("用户" + user.getName() + "退出登录");
		}
		return "forward:/login";
	}

	@RequiresAuthentication
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String goMain(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) {
		MonitorRealm.ShiroUser shiroUser = (MonitorRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (shiroUser == null) {
			return "redirect:/login";
		}
		String userid = shiroUser.getId();
		List<MenuBO> menuBOList = new ArrayList<>();
		if (shiroUser.getLoginName().equals("admin")) {
			menuBOList = menuService.getAllEnabledMenu();
		} else {
			menuBOList = menuService.getAllEnabledMenuByUserId(userid);
		}
		model.put("menuBOList", menuBOList);
		return "main";
	}
}
