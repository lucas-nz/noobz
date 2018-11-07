package com.zhousz.zerg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhousz.zerg.dao.SysUserMapper;
import com.zhousz.zerg.domain.SysUser;
import com.zhousz.zerg.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper sysUserMapper;
	
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
	
	

}
