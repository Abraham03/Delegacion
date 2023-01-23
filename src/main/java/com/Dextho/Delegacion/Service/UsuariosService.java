package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.User;

public interface UsuariosService {

	List<User> getAllUsuarios();
	Optional<User> getUsuariosById(Long id);
	User saveUsuarios(User u);
	void updateUsuarios(User u);
	boolean deleteUsuariosById(Long id);
	User FindByUsername(String username);
	void addRoleToUser(Long user_id, Long roles_id);
}
