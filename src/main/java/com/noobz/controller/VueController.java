package com.noobz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vue")
public class VueController extends BaseController{

	@RequestMapping("")
	public String index() {
		return render3("header");
	}
}
