package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	@RequestMapping({"","/main"})		//mysite03으로 들어올 수도 있고, mysite03/main으로 들어올 수도 있다.
	public String index() {
		return "main/index";
	}
}
