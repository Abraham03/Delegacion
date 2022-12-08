package com.Dextho.Delegacion.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("/login")
	String login() {
		return "login";
	}
	
}
