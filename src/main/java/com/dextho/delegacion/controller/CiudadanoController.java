package com.dextho.delegacion.controller;

import com.dextho.delegacion.dto.CiudadanosDTO;
import com.dextho.delegacion.model.Ciudadanos;
import com.dextho.delegacion.servicesImpl.CiudadanoServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/Dextho/ciudadano")
public class CiudadanoController {

    @Autowired
    private CiudadanoServiceImpl ciudadanoServiceImpl;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllCiudadanos() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Ciudadanos> listaCiudadanos = ciudadanoServiceImpl.getAllCiudadanos();
        map.put("Status", 1);
        map.put("data", listaCiudadanos);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/filtro/{estatus}")
    public ResponseEntity<?> getCiudadanosByEstatus(@PathVariable("estatus") String estatus) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Ciudadanos> listaCiudadanos = ciudadanoServiceImpl.getAllByEstatus(estatus);

        if (!listaCiudadanos.isEmpty()) {
            map.put("Status", 1);
            map.put("data", listaCiudadanos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("data", "Datos no encontrados");
            // Nota: Devolver OK con lista vacía o mensaje es mejor para manejarlo en el front
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<?> getCiudadanosActivos() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Ciudadanos> listaCiudadanos = ciudadanoServiceImpl.getAllActivos();
        map.put("Status", 1);
        map.put("data", listaCiudadanos);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/ByGrupo/{grupo}")
    public ResponseEntity<?> getAllCiudadanosByGrupo(@PathVariable("grupo") int grupo) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Ciudadanos> listaCiudadanos = ciudadanoServiceImpl.getAllByGrupo(grupo);
        if (!listaCiudadanos.isEmpty()) {
            map.put("Status", 1);
            map.put("data", listaCiudadanos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("data", "Datos no encontrados");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> saveCiudadanos(@RequestBody CiudadanosDTO ciudadanosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Ciudadanos ciudadanosGuardar = ciudadanoServiceImpl.saveCiudadano(ciudadanosDTO);
        if (ciudadanosGuardar != null) {
            map.put("data", ciudadanosGuardar);
            return new ResponseEntity<>(map, HttpStatus.CREATED);

        } else {
            map.put("status", 0);
            map.put("message", "Datos no Guardados");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> updateCiudadano(@PathVariable("id") Long id, @RequestBody CiudadanosDTO ciudadanosDTO) {
        Map<String, Object> map = new LinkedHashMap<>();
        Ciudadanos ciudadanos = ciudadanoServiceImpl.updateCiudadanos(ciudadanosDTO, id);
        if (ciudadanos != null) {
            map.put("status", 1);
            map.put("data", ciudadanos);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Datos no actualizados");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/example")
    public ResponseEntity<?> getExample(HttpSession session) {
        Map<String, Object> map = new LinkedHashMap<>();
        // Acceder a los atributos de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String value = ((Object) authentication.getName()).toString();

        if (!value.isEmpty()) {
            // Hacer algo con el val
            map.put("Status", 1);
            map.put("data", value);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("Status", 0);
            map.put("data", "Nada encontrado");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
