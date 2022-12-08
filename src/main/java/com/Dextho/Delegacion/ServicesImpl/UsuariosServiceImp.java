package com.Dextho.Delegacion.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.Usuarios;
import com.Dextho.Delegacion.Repository.UsuariosRepository;
import com.Dextho.Delegacion.Service.UsuariosService;

@Service
public class UsuariosServiceImp implements UsuariosService {

	@Autowired
	UsuariosRepository usuariosRepository;
	
	@Override
	public List<Usuarios> getAllUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Usuarios> getUsuariosById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Usuarios saveUsuarios(Usuarios u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUsuarios(Usuarios u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteUsuariosById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
