package com.dextho.delegacion.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.model.Ciudadanos;
import com.dextho.delegacion.repository.CiudadanosRepository;


@Service
public class CiudadanoServiceImpl{

	@Autowired
	CiudadanosRepository ciudadanosRepository;

	
	public List<Ciudadanos> getAllCiudadanos() {
		return (List<Ciudadanos>) ciudadanosRepository.findAll();
	}

	public Optional<Ciudadanos> getCiudadanoById(Long id) {
		return ciudadanosRepository.findById(id);
	}

	public Ciudadanos saveCiudadano(Ciudadanos c) {
		return ciudadanosRepository.save(c);
	}

	public boolean deleteCiudadanoById(Long id) {
		try {
			Optional<Ciudadanos> c = getCiudadanoById(id);
			ciudadanosRepository.delete(c.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
