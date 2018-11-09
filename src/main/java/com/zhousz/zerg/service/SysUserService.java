package com.zhousz.zerg.service;

import java.util.List;
import java.util.Map;

import com.zhousz.zerg.domain.SysUser;
 
public interface SysUserService {
	
	List<SysUser> querySysUsers();
	
	List<Map<String, Object>> getAllUsers();
}
