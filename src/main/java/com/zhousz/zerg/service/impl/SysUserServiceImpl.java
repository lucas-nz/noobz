package com.zhousz.zerg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhousz.zerg.dao.mysql.TUsersDao;
import com.zhousz.zerg.dao.oracle.SysUserMapper;
import com.zhousz.zerg.domain.SysUser;
import com.zhousz.zerg.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private TUsersDao tUsersDao;
	
	@Override
	public List<SysUser> querySysUsers() {
		List<SysUser> list = sysUserMapper.querySysUsers();
//		SysUser sysUser = new SysUser();
//		sysUser.setEmpName("zsz");
//		sysUser.setOperId("001");
//		List<SysUser> list = new ArrayList<SysUser>();
//		list.add(sysUser);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllUsers() {
		List<Map<String, Object>> list = tUsersDao.getAllUsers();
		return list;
	}
	
	

}
