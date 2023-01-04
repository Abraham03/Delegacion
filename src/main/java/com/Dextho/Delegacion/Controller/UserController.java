package com.Dextho.Delegacion.Controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<?> getAllUsers(){
		//return userImp.getAllUsuarios();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<User> usuarios = userImp.getAllUsuarios();
		if (!usuarios.isEmpty()) {
			map.put("Status", 1);
			map.put("Data", usuarios);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		map.clear();
		map.put("Status", 0);
		map.put("Message", "Users Not found ");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Optional<User> usuarios = userImp.getUsuariosById(id);
		if (!usuarios.isEmpty()) {
			map.put("Status", 1);
			map.put("Data", usuarios);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		map.clear();
		map.put("Status", 0);
		map.put("Message", "ID Not found "+id);
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/guardar")
	public User saveUser(@RequestBody User user) {
		return userImp.saveUsuarios(user);
	}
	
}
