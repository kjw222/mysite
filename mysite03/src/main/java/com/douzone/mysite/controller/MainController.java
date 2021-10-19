package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@ResponseBody
	@RequestMapping({"","/main"})		//mysite03으로 들어올 수도 있고, mysite03/main으로 들어올 수도 있다.
	public String index() {
		return "mysite03.index() called";
	}
}
