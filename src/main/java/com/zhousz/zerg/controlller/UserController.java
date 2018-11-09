package com.zhousz.zerg.controlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.zhousz.zerg.domain.SysUser;
import com.zhousz.zerg.service.SysUserService;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/list")
	public String listUsers(Model model){
		List<SysUser> list = sysUserService.querySysUsers();
		model.addAttribute("userList", list);
		return "sysuser";
	}
		
}
