package com.Dextho.Delegacion.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	ArrayList<Tareas> getAllTareas() {
		return tareaService.getAllTareas();
	}
	
	@GetMapping("/buscar/{id}")
	Optional<Tareas> getTareasById(@PathVariable("id") Long id) {
		return tareaService.getTareaById(id);

	}
	
	@PostMapping("/guardar")
	Tareas saveTarea(@RequestBody Tareas t) {
		return tareaService.saveTarea(t);
	}
	
	@PutMapping("/actualizar")
	public Tareas updateTarea(@RequestBody Tareas t) {
		tareaService.updateTarea(t);
		return t;

	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminar/{id}")
	public String deleteTareaById(@PathVariable("id") long id) {
		if (tareaService.deleteTareaById(id)) {
			return "Se ha eliminado";
		} else {
			return "No se ha eliminado";
		}
		
	}
}
