package com.dextho.delegacion.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import com.dextho.delegacion.model.EstatusCiudadanos;
import com.dextho.delegacion.servicesImpl.EstatusCiudadanoServiceImp;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/estatus")
public class EstatusCiudadanoController {

    @Autowired
    public EstatusCiudadanoServiceImp estatusCiudadanoServiceImp;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllEstatusCiudadanos() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<EstatusCiudadanos> listaEstatus = estatusCiudadanoServiceImp.getAllEstatusCiudadanos();
        map.put("Status", 1);
        map.put("data", listaEstatus);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> saveEstatus(@RequestBody EstatusCiudadanos estatusCiudadanos) {
        Map<String, Object> map = new LinkedHashMap<>();
        EstatusCiudadanos guardarEstatus = estatusCiudadanoServiceImp.saveEstatusCiudadanos(estatusCiudadanos);
        if (!guardarEstatus.equals(null)) {
            map.put("status", 1);
            map.put("data", guardarEstatus);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Datos no Guardados");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateEstatus(@PathVariable("id") Long id,
            @RequestBody EstatusCiudadanos estatusCiudadanos) {
        Map<String, Object> map = new LinkedHashMap<>();
        EstatusCiudadanos actualizarEstatusCiudadanos = estatusCiudadanoServiceImp.updateEstatus(estatusCiudadanos, id);
        if (!actualizarEstatusCiudadanos.equals(null)) {
            map.put("status", 1);
            map.put("data", actualizarEstatusCiudadanos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Datos no actualizados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
