package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.A_Usuarios;

public interface UsuariosService {

	List<A_Usuarios> getAllUsuarios();
	Optional<A_Usuarios> getUsuariosById(Long id);
	A_Usuarios saveUsuarios(A_Usuarios u);
	void updateUsuarios(A_Usuarios u);
	boolean deleteUsuariosById(Long id);
	
}
