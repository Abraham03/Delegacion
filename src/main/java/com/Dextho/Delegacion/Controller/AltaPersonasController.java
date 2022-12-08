package com.Dextho.Delegacion.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Dextho.Delegacion.Model.Ciudadanos;
import com.Dextho.Delegacion.Service.CiudadanoService;

@RestController
@RequestMapping("Dextho/ciudadano")
public class AltaPersonasController {
	@Autowired
	CiudadanoService ciudadanoService;
	
	@GetMapping("/ciudadanos")
	public List<Ciudadanos> getAllCiudadanos() {
		return ciudadanoService.getAllCiudadanos();
	}
	
	@GetMapping("/buscar/{id}")
	public Optional<Ciudadanos> getCiudadanoById(@PathVariable("id") long id) {
		return ciudadanoService.getCiudadanoById(id);
	}
	
	@PostMapping("/guardar")
	public Ciudadanos saveCiudadano(@RequestBody Ciudadanos t) {
		return ciudadanoService.saveCiudadano(t);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String deleteCiudadanoById(@PathVariable("id") long id) {
		if (ciudadanoService.deleteCiudadanoById(id)) {
			return "Se ha eliminado";
		} else {
			return "No se ha eliminado";
		}
		
	}
}
