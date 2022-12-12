package com.Dextho.Delegacion.ServicesImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.A_Usuarios;
import com.Dextho.Delegacion.Repository.UsuariosRepository;
import com.Dextho.Delegacion.Service.UsuariosService;

@Service
public class UsuariosServiceImp implements UsuariosService {

	private UsuariosRepository usuariosRepository;

	public List<A_Usuarios> getAllUsuarios() {
		return (List<A_Usuarios>) usuariosRepository.findAll();
	}

	public Optional<A_Usuarios> getUsuariosById(Long id) {
		return usuariosRepository.findById(id);
	}

	public A_Usuarios saveUsuarios(A_Usuarios u) {
		return usuariosRepository.save(u);
	}

	public void updateUsuarios(A_Usuarios u) {
		usuariosRepository.save(u);
		
	}

	public boolean deleteUsuariosById(Long id) {
		try {
			Optional<A_Usuarios> u = getUsuariosById(id);
			usuariosRepository.delete(u.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
