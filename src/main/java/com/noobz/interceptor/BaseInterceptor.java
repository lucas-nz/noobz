package com.noobz.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.noobz.util.AdminCommons;
import com.noobz.util.Commons;
import com.noobz.util.IPKit;

@Component
public class BaseInterceptor implements HandlerInterceptor {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(BaseInterceptor.class);
	
	@Resource
	private Commons common;

	@Resource
	private AdminCommons adminCommons;
	/**
	 * <p>Title: preHandle</p>   
	 * <p>Description: </p>   在业务处理之前调用, 预处理, 可以进行编码\ 安全校验\ 权限校验
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		LOGGER.debug("用户访问地址: {}, 来路地址: {}", uri, IPKit.getIpAddrByRequest(request));
		return true;
	}

	/**
	 * <p>Title: postHandle</p>   
	 * <p>Description: </p> 在业务处理之后页面渲染之前.   
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception   
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 将common工具类添加到request中. 供thymeleaf调用.
		request.setAttribute("commons", common);
		request.setAttribute("adminCommons", adminCommons);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}

