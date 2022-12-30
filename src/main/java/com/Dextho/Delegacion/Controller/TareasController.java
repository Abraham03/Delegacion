package com.Dextho.Delegacion.Controller;

import java.time.Clock;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dextho.Delegacion.Model.Tareas;
import com.Dextho.Delegacion.ServicesImpl.TareaServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Dextho/tarea")
public class TareasController {
	@Autowired
	TareaServiceImpl tareaService;
	private Clock clock;

	@GetMapping("/tareas")
	public ResponseEntity<?> getAllTareas() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Tareas> listaTareas = tareaService.getAllTareas();
		if (!listaTareas.isEmpty()) {
			map.put("Status", 1);
			map.put("data", listaTareas);
			return new ResponseEntity<>(map, HttpStatus.OK);
		} else {
			map.clear();
			map.put("status", 0);
			map.put("message", "Data not found");
			return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> getTareasById(@PathVariable("id") Long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Optional<Tareas> tareasData = tareaService.getTareaById(id);
		if (!tareasData.isEmpty()) {
			map.put("Status", 1);
			map.put("data", tareasData);
			return new ResponseEntity<>(map, HttpStatus.OK);

		}
		map.clear();
		map.put("status", 0);
		map.put("message", "ID Not found");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

	}

	@PostMapping("/guardar")
	public ResponseEntity<?> saveTarea(@RequestBody Tareas tarea) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		tareaService.saveTarea(new Tareas(tarea.getNombre(), tarea.getDescripcion(), tarea.getPrioridad(),
				LocalDateTime.now(clock), tarea.getIdUsuarioModificado()));
		map.put("status", 1);
		map.put("message", "Datos actualizados");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> updateTarea(@PathVariable("id") Long id, @RequestBody Tareas tarea) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Optional<Tareas> tareas = tareaService.getTareaById(id);
		if (tareas.isPresent()) {
			tareaService.saveTarea(new Tareas(tarea.getNombre(), tarea.getDescripcion(), tarea.getPrioridad(),
					LocalDateTime.now(clock), tarea.getIdUsuarioModificado()));
			map.put("status", 1);
			map.put("message", "Datos actualizados");
			return new ResponseEntity<>(map, HttpStatus.OK);

		}
		map.clear();
		map.put("status", 0);
		map.put("message", "Datos no actualizados");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);

	}

	// @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteTareaById(@PathVariable("id") Long id) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		tareaService.deleteTareaById(id);
		map.put("status", 1);
		map.put("message", "Tarea eliminada");
		return new ResponseEntity<>(map, HttpStatus.OK);

	}
}
