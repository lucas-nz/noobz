package com.noobz.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noobz.domain.User;
import com.noobz.service.UserService;

@Controller
public class IndexController extends BaseController {

	@Resource
	private UserService userService;
	
	/**
	 * @return: String      
	 * @Comment: 首页 
	 */
	@GetMapping("/")
	public String index(){
		return render("index");
	}
	
	@ResponseBody
	@GetMapping("/user")
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	
	
}
