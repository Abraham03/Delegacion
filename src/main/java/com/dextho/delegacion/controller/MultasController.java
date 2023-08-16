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

import com.dextho.delegacion.servicesImpl.MultasServiceImp;
import com.dextho.delegacion.dto.MultasUsuariosDTO;
import com.dextho.delegacion.model.Multas;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/multas")
public class MultasController {

    @Autowired
    public MultasServiceImp multasServiceImp;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllMultas() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Multas> listaMultas = multasServiceImp.getAllMultas();
        if (!listaMultas.isEmpty()) {
            map.put("Status", 1);
            map.put("data", listaMultas);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("Status", 0);
            map.put("message", "Datps no encontrados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> saveMultas(@RequestBody MultasUsuariosDTO multasUsuariosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Multas multaGuardar = multasServiceImp.saveMulta(multasUsuariosDTO);
        if (multaGuardar != null) {
            map.put("Status", 1);
            map.put("data", multaGuardar);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("Status", 0);
            map.put("message", "Datos no guardados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<?> updateMulta(@PathVariable("id") Long id,
            @RequestBody MultasUsuariosDTO multasUsuariosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Multas multaExistente = multasServiceImp.updateMulta(multasUsuariosDTO, id);
        if (multaExistente != null) {
            map.put("status", multaExistente);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Nombre del usuario o Rol ya existe");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

}
