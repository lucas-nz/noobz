package com.noobz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noobz.dao.UserMapper;
import com.noobz.domain.User;
import com.noobz.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUsers() {
		return userMapper.selectByExample(null);
	}
	
	
}
