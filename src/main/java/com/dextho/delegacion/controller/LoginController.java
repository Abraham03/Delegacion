package com.dextho.delegacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
