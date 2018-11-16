package com.noobz.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noobz.constant.WebConst;
import com.noobz.domain.User;
import com.noobz.exception.TipException;
import com.noobz.model.RestResponse;
import com.noobz.service.UserService;

@Controller
@RequestMapping("/admin")
@SuppressWarnings("rawtypes")
public class AuthContorller {

	@Resource
	private UserService userService;
	
	@GetMapping(value = "/login")
	public String login() {
		return "admin/login";
	}
	
	
	@PostMapping(value = "/login")
	@ResponseBody
	public RestResponse doLogin(@RequestParam String username,
								@RequestParam String password,
								@RequestParam(required=false) String remember_me,
								HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = userService.login(username, password);
			request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
		} catch (Exception e) {
			String msg = "登录失败.";
			if (e instanceof TipException) {
				msg = e.getMessage();
			}
			return RestResponse.fail(msg);
		}
		return RestResponse.ok();
	}
	
	
	
	
	
}
