package com.Dextho.Delegacion.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.Ciudadanos;
import com.Dextho.Delegacion.Repository.CiudadanosRepository;
import com.Dextho.Delegacion.Service.CiudadanoService;


@Service
public class CiudadanoServiceImpl implements CiudadanoService{

	@Autowired
	CiudadanosRepository ciudadanosRepository;

	
	@Override
	public List<Ciudadanos> getAllCiudadanos() {
		return (List<Ciudadanos>) ciudadanosRepository.findAll();
	}

	@Override
	public Optional<Ciudadanos> getCiudadanoById(Long id) {
		return ciudadanosRepository.findById(id);
	}

	@Override
	public Ciudadanos saveCiudadano(Ciudadanos c) {
		return ciudadanosRepository.save(c);
	}

	@Override
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
