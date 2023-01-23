package com.Dextho.Delegacion.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho")

public class HomeController{

	@GetMapping("/home")
	String home() {
		return "home";
	}

	@GetMapping("/tarea")
	String tarea() {
		return "tarea";
	}
	
	@GetMapping("/usuario")
	String usuarios() {
		return "usuarios";
	}
}
