package com.Dextho.Delegacion.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/")
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
