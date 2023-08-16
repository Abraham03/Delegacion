package com.dextho.delegacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class AccesoDenegadoController {

    @GetMapping("/acceso-denegado")
	String accesoDenegado(){
		return "acceso-denegado";
	}
    
}