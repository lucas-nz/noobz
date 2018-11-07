package com.zhousz.zerg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.zhousz.zerg.domain.SysUser;

@Component
@Mapper
public interface SysUserMapper {

	@Select("select * from sys_user")
	List<SysUser> querySysUsers();
	
}
