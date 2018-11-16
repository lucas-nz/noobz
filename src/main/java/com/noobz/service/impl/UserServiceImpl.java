package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.noobz.dao.UserMapper;
import com.noobz.domain.User;
import com.noobz.domain.UserExample;
import com.noobz.domain.UserExample.Criteria;
import com.noobz.exception.TipException;
import com.noobz.service.UserService;
import com.noobz.util.TaleUtils;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUsers() {
		return userMapper.selectByExample(null);
	}
	
	@Override
	public User login(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new TipException("用户名和密码不能为空.");
		}
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		long count = userMapper.countByExample(example);
		if (count < 1) {
			throw new TipException("不存在该用户.");
		}
		String pwd = TaleUtils.MD5encode(username + password);
		criteria.andPasswordEqualTo(pwd);
		List<User> userList = userMapper.selectByExample(example);
		if (userList.size() != 1) {
			throw new TipException("用户名或密码错误.");
		}
		return userList.get(0);
	}
}
