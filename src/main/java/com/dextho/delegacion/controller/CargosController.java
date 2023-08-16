package com.dextho.delegacion.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dextho.delegacion.dto.CargosDTO;
import com.dextho.delegacion.model.Cargos;
import com.dextho.delegacion.servicesImpl.CargosServiceImp;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/cargos")
public class CargosController {

    @Autowired
    public CargosServiceImp cargosServiceImp;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllCargos() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Cargos> listaCargos = cargosServiceImp.getAllCargos();
        if (!listaCargos.isEmpty()) {
            map.put("Status", 1);
            map.put("data", listaCargos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("Status", 0);
            map.put("message", "Datos no encontrados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> saveCargos(@RequestBody CargosDTO cargosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Cargos cargoGuardar = cargosServiceImp.saveCargo(cargosDTO);
        if (cargoGuardar != null) {
            map.put("Status", 1);
            map.put("data", cargoGuardar);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("Status", 0);
            map.put("message", "Datos no guardados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> updateCargo(@PathVariable("id") Long id,
            @RequestBody CargosDTO cargosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Cargos cargoExistente = cargosServiceImp.updateCargo(cargosDTO, id);
        if (cargoExistente != null) {
            map.put("status", cargoExistente);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Datos actualizados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

    }
}