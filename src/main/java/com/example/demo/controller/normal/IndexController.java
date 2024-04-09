package com.example.demo.controller.normal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/home")
	public String index() {
		System.out.println("this is the index page");
		return "index";
	}
	
	@RequestMapping("/registration")
	public String reg() {
		return "RegistrationPage";
	}
}
