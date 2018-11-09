package com.zhousz.zerg.dao.mysql;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUsersDao {

	List<Map<String, Object>> getAllUsers();
}
