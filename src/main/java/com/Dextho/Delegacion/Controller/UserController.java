package com.Dextho.Delegacion.Controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dextho.Delegacion.DTO.UserDTO;
import com.Dextho.Delegacion.Model.User;
import com.Dextho.Delegacion.ServicesImpl.UsuariosServiceImp;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Dextho/usuarios")
public class UserController {

	@Autowired
	private UsuariosServiceImp userImp;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllUsers() {
		Map<String, Object> map = Map.ofEntries(
				Map.entry("Status", 1),
				Map.entry("Data", userImp.getAllUsuarios().stream().map(UserDTO::new).collect(Collectors.toList())));
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PostMapping(value = "/guardar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
		Map<String, Object> map = new LinkedHashMap<>();
		User savedUser = userImp.saveUsuarios(userDTO);
		if (savedUser != null) {
			map.put("datos", savedUser);
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} else {

			map.put("status", 0);
			map.put("message", "Usuario ya existe");
			return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("actualizar/{id}")
	public ResponseEntity<?> updateUsuario(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
		Map<String, Object> map = new LinkedHashMap<>();
		User existingUser = userImp.updateUsuarios(userDTO, id);
		if (existingUser != null) {
			map.put("datos", existingUser);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Nombre del usuario o Rol ya existe");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @GetMapping("/buscar/{id}")
	 * public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id){
	 * Map<String, Object> map = new LinkedHashMap<String, Object>();
	 * Optional<User> usuarios = userImp.getUsuariosById(id);
	 * if (!usuarios.isEmpty()) {
	 * map.put("Status", 1);
	 * map.put("Data", usuarios);
	 * return new ResponseEntity<>(map, HttpStatus.OK);
	 * }
	 * map.clear();
	 * map.put("Status", 0);
	 * map.put("Message", "ID Not found "+id);
	 * return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	 * 
	 * }
	 */

	@PutMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteUsuarioById(@PathVariable("id") Long id) {
		Map<String, Object> map = new LinkedHashMap<>();

		User usuario = userImp.getUsuariosById(id).get();
		if (usuario != null) {
			usuario.setEnabled(false);
			userImp.saveUsuarios(usuario);
			map.put("status", 1);
			map.put("message", "Usuario Eliminado");
			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		map.put("status", 0);
		map.put("message", "Usuario " + id + " no encontrado");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

	}

}
