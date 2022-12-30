package com.Dextho.Delegacion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dextho.Delegacion.Model.User;
import com.Dextho.Delegacion.ServicesImpl.UsuariosServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Dextho/usuarios")
public class UserController {

	@Autowired
	UsuariosServiceImp userImp;
	
	@GetMapping("/todos")
	public List<User> getAllUsers(){
		return userImp.getAllUsuarios();
	}
	
	@PostMapping("/guardar")
	public User saveUser(@RequestBody User user) {
		return userImp.saveUsuarios(user);
	}
	
}
