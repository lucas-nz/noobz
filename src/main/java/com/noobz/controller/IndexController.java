package com.noobz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noobz.domain.Content;
import com.noobz.domain.User;
import com.noobz.service.ContentService;
import com.noobz.service.UserService;

@Controller
public class IndexController extends BaseController {

	@Resource
	private UserService userService;
	
	@Resource
	private ContentService contentService;
	
	/**
	 * @return: String      
	 * @Comment: 首页 
	 */
	@GetMapping("/")
	public String index(HttpServletRequest request){
		List<Content> list = contentService.findAllContents();
		request.setAttribute("articles", list);
		return render("index");
	}
	
	@ResponseBody
	@GetMapping("/user")
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	
	
}
