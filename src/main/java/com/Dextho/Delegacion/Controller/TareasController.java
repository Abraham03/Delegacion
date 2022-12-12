package com.Dextho.Delegacion.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dextho.Delegacion.Model.Tareas;
import com.Dextho.Delegacion.Service.TareaService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("Dextho/tarea")
public class TareasController {
	@Autowired
	TareaService tareaService;
	
	@GetMapping("/tareas")
	public ResponseEntity<?> getAllTareas() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Tareas> listaTareas = tareaService.getAllTareas();
		if (!listaTareas.isEmpty()) {
			map.put("Status", 1);
			map.put("data", listaTareas);
			return new ResponseEntity<>(map, HttpStatus.OK);
		}else{
			map.clear();
			map.put("status", 0);
			map.put("message", "Data not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/buscar/{id}")
	Optional<Tareas> getTareasById(@PathVariable("id") Long id) {
		return tareaService.getTareaById(id);

	}
	
	@PostMapping("/guardar")
	Tareas saveTarea(@RequestBody Tareas t) {
		return tareaService.saveTarea(t);
	}
	/*
	@PutMapping("/actualizar")
	public Tareas updateTarea(@RequestBody Tareas t) {
		tareaService.updateTarea(t);
		return t;

	}*/
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminar/{id}")
	public void deleteTareaById(@PathVariable("id") long id) {
		tareaService.deleteTareaById(id);
		
	}
}
