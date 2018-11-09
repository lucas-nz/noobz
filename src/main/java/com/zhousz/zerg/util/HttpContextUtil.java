package com.zhousz.zerg.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpContextUtil {

	/**
	 * @Title: getHttpServletRequest   
	 * @Description: 获取 HttpServletRequest 
	 * @author: zhousz
	 * @param: @return      
	 * @return: HttpServletRequest      
	 * @throws   
	 * @Comment:
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
}
