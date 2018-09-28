package com.zhousz.zerg.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhousz.zerg.properties.CommonProperties;

@RestController
public class IndexController {
	
	@Autowired
	private CommonProperties commonProperties;
	
	@RequestMapping(value = "/getCommon")
	public String getCommon() {
		return commonProperties.getBlogName() + "-" + commonProperties.getGithub();
	}
		
}
