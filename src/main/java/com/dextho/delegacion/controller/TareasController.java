package com.dextho.delegacion.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dextho.delegacion.model.Tareas;
import com.dextho.delegacion.servicesImpl.TareaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/tareas")
public class TareasController {

    @Autowired
    public TareaServiceImpl tareaService;

    @GetMapping("/lista")
    public ResponseEntity<?> getAllTareas() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Tareas> listaTareas = tareaService.getAllTareas();
        map.put("Status", 1);
        map.put("data", listaTareas);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getTareasById(@PathVariable("id") Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<Tareas> tareasData = tareaService.getTareaById(id);
        if (!tareasData.isEmpty()) {
            map.put("Status", 1);
            map.put("data", tareasData);
            return new ResponseEntity<>(map, HttpStatus.OK);

        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "ID No encontrado: " + id);
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> saveTarea(@RequestBody Tareas tarea) {
        Map<String, Object> map = new LinkedHashMap<>();
        tarea.setFecha_Creado(LocalDate.now());
        tarea.setActivo(true);
        Tareas savedTarea = tareaService.saveTarea(tarea);
        if (savedTarea != null) {
            map.put("datos", savedTarea);
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } else {
            map.put("status", 0);
            map.put("message", "Datos no Guardados");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateTarea(@PathVariable("id") Long id, @RequestBody Tareas tarea) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<Tareas> tareas = tareaService.getTareaById(id);
        if (tareas.isPresent()) {
            Tareas _tarea = tareas.get();
            _tarea.setNombre(tarea.getNombre());
            _tarea.setDescripcion(tarea.getDescripcion());
            _tarea.setPrioridad(tarea.getPrioridad());
            _tarea.setEstatus(tarea.getEstatus());
            _tarea.setActivo(tarea.getActivo());
            tareaService.saveTarea(_tarea);
            map.put("datos", tarea);
            return new ResponseEntity<>(map, HttpStatus.OK);

        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Datos no actualizados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteTareaById(@PathVariable("id") Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Tareas tarea = tareaService.getTareaById(id).get();
            tarea.setActivo(false);
            tareaService.saveTarea(tarea);
            map.put("status", 1);
            map.put("message", "Tarea eliminada");
        } catch (Exception e) {
            map.put("status", 0);
            map.put("message", "Error al eliminar tarea, ID: " + id + " no encontrado");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
