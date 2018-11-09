package com.zhousz.zerg.controlller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhousz.zerg.annotation.Log;
import com.zhousz.zerg.domain.SysUser;
import com.zhousz.zerg.properties.CommonProperties;
import com.zhousz.zerg.service.SysUserService;

@RestController
public class IndexController {
	
	@Autowired
	private CommonProperties commonProperties;
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "/getCommon")
	public String getCommon() {
		return commonProperties.getBlogName() + "-" + commonProperties.getGithub();
	}
	
	
	@RequestMapping(value = "/listSysUser")
	public List<SysUser> getSysuserList(){
		return sysUserService.querySysUsers();
	}
	
	@RequestMapping(value = "/getAllUsers")
	public List<Map<String, Object>> getAllUsers(){
		return sysUserService.getAllUsers();
	}
	
	
	@Log(" aop 记录日志")
	@GetMapping("one")
	public void methodOne(String name){}
	
	
	
		
}
