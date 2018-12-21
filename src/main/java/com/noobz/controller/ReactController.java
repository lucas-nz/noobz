package com.noobz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/react")
public class ReactController extends BaseController{

	public String index() {
		return render2("header");
	}
}
