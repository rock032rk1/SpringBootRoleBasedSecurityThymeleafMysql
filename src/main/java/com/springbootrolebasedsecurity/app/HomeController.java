package com.springbootrolebasedsecurity.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/login")
	public String getLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/userPage")
	public String getRegistPage() {

		return "registration";
	}
}
