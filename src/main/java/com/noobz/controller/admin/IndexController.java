package com.noobz.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noobz.controller.BaseController;
import com.noobz.domain.Comment;
import com.noobz.domain.Content;
import com.noobz.domain.Log;
import com.noobz.model.Statistics;
import com.noobz.service.SiteService;
import com.noobz.service.UserService;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController extends BaseController{
	@Resource
	private SiteService siteService;
	
	@Resource
	private UserService userService;
	
	@GetMapping(value = {"/index", ""})
	public String index(HttpServletRequest request) {
		List<Comment> comments = siteService.getRecenComments();
		List<Content> contents = siteService.getRecentContents();
		Statistics statistics = siteService.getStatistics();
		List<Log> logs = new ArrayList<Log>();
		request.setAttribute("comments", comments);
		request.setAttribute("articles", contents);
		request.setAttribute("statistics", statistics);
		request.setAttribute("logs", logs);
		return "admin/index";
	}
	
}
