package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.noobz.dao.UserMapper;
import com.noobz.domain.User;
import com.noobz.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUsers() {
		return userMapper.selectByExample(null);
	}
	
	
}
