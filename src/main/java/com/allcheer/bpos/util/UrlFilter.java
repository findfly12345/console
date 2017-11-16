package com.allcheer.bpos.util;


import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allcheer.bpos.entity.vo.UserVo;


public class UrlFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(UrlFilter.class);
	private FilterConfig config = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		HttpServletRequest hr = (HttpServletRequest) request;
		HttpSession session = hr.getSession();
		UserVo user = (UserVo) session.getAttribute(SystemConstant.USER_SESSION_KEY);
		
		String servletPath = hr.getServletPath();
		String requestURI = hr.getRequestURI();
		if (requestURI.contains("resources")) {
			String ch = URLDecoder.decode(requestURI, "UTF-8");
			if (requestURI.equals(ch)) {
				chain.doFilter(request, response);
			} else {
				ch = ch.substring(hr.getContextPath().length());
				config.getServletContext().getRequestDispatcher(ch).forward(request, response);
			}
		} else if (servletPath.equals("/login") || servletPath.equals("/")) {
			chain.doFilter(request, response);
		} else if (user != null && user.getName()!=null) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}
	
	@Override
	public void destroy() {
	}
}
