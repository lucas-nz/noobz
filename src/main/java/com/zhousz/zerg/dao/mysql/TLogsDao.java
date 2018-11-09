package com.zhousz.zerg.dao.mysql;

import org.apache.ibatis.annotations.Mapper;

import com.zhousz.zerg.domain.TLogs;

@Mapper
public interface TLogsDao {

	void save(TLogs log);
}	
