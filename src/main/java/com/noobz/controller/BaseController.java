package com.noobz.controller;

import javax.servlet.http.HttpServletRequest;

import com.noobz.domain.User;
import com.noobz.util.TaleUtils;

public abstract class BaseController {
	
	public static String THEME = "themes/default";
	
	public static String REACT_THEME = "themes/react";
	
	public static String VUE_THEME = "themes/vue";
	
	public String render(String viewName) {
		return THEME + "/" + viewName;
	}
	
	/**
	 * react
	 * @param viewName
	 * @return String
	 */
	public String render2(String viewName) {
		return REACT_THEME + "/" + viewName;
	}
	
	/**
	 * vue 
	 * @param viewName
	 * @return String
	 */
	public String render3(String viewName) {
		return VUE_THEME + '/' + viewName;
	}
	public BaseController title(HttpServletRequest request, String title) {
		request.setAttribute("title", title);
		return this;
	}
	
	public BaseController keywords(HttpServletRequest request, String keywords) {
		request.setAttribute("keywords", keywords);
		return this;
	}
	
	public String render_404() {
		return "comm/error_404";
	}
	
	public User user(HttpServletRequest request) {
		return TaleUtils.getLoginUser(request);
	}
	
}
