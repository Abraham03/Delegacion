package com.Dextho.Delegacion.ServicesImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.User;
import com.Dextho.Delegacion.Repository.UserRepository;
import com.Dextho.Delegacion.Service.UsuariosService;

@Service
public class UsuariosServiceImp implements UsuariosService {

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository usuariosRepository;

	

	public List<User> getAllUsuarios() {
		return (List<User>) usuariosRepository.findAll();
	}

	public Optional<User> getUsuariosById(Long id) {
		return usuariosRepository.findById(id);
	}

	public User saveUsuarios(User u) {
		//return usuariosRepository.save(u);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuariosRepository.save(u);
	}

	public void updateUsuarios(User u) {
		usuariosRepository.save(u);
	}

	public boolean deleteUsuariosById(Long id) {
		try {
			Optional<User> u = getUsuariosById(id);
			usuariosRepository.delete(u.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public User FindByUsername(String username) {
		
		return usuariosRepository.findByUsername(username);
	}

	public void addRoleToUser(Long userId, Long roleId){
		
	}	

}
