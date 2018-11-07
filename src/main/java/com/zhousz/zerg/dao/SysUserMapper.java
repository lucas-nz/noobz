package com.zhousz.zerg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.zhousz.zerg.domain.SysUser;

@Component
@Mapper
public interface SysUserMapper {

	@Select("select oper_Id, emp_Name from sys_user")
	@Results(id = "sysUser", value = {
	@Result(property = "operId", column = "oper_id", javaType = String.class),
	@Result(property = "empName", column = "emp_name", javaType = String.class)
	})
	List<SysUser> querySysUsers();
	
}
