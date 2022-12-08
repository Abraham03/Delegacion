package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.Ciudadanos;



public interface CiudadanoService {
	
	List<Ciudadanos> getAllCiudadanos();
	Optional<Ciudadanos> getCiudadanoById(Long id);
	Ciudadanos saveCiudadano(Ciudadanos c);
	boolean deleteCiudadanoById(Long id);

}
