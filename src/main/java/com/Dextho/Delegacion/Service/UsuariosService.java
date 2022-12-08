package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.Usuarios;

public interface UsuariosService {

	List<Usuarios> getAllUsuarios();
	Optional<Usuarios> getUsuariosById(Long id);
	Usuarios saveUsuarios(Usuarios u);
	void updateUsuarios(Usuarios u);
	boolean deleteUsuariosById(Long id);
	
}
