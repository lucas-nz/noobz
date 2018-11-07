package com.zhousz.zerg.domain;

import java.io.Serializable;

public class SysUser implements Serializable {

	/**   
	 * @Fields serialVersionUID : 序列化标记   
	 */   
	private static final long serialVersionUID = 1L;
	
	private String operId;
	
	private String empName;

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
	
}
