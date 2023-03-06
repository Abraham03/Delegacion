package com.dextho.delegacion.servicesImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.dto.UserDTO;
import com.dextho.delegacion.model.Role;
import com.dextho.delegacion.model.User;
import com.dextho.delegacion.repository.AuthoritiesRepository;
import com.dextho.delegacion.repository.UserRepository;

@Service
public class UsuariosServiceImp {


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository usuariosRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;


	

	public List<User> getAllUsuarios() {
		return (List<User>) usuariosRepository.findAll();
	}

	public Optional<User> getUsuariosById(Long id) {
		return usuariosRepository.findById(id);
	}

	public User saveUsuarios(User user) {
		return usuariosRepository.save(user);
	}

	public User updateUsuarios(UserDTO userDTO, Long id) {
		User user = usuariosRepository.findById(id).orElse(null);
		User existingUserWithSameUsername = usuariosRepository.findByUsername(userDTO.getUsername());

		if (existingUserWithSameUsername != null && !existingUserWithSameUsername.getId().equals(id)) {
			return null;
		}
			List<Role> roles = new ArrayList<>();
			for (Role role : userDTO.getRoles()) {
				Role existingRole = authoritiesRepository.findByName(role.getName());
				if (existingRole != null) {
					if (!roles.contains(existingRole)) {
						roles.add(existingRole);
					}
				}
				
			}
			if (!userDTO.getPassword().isEmpty()) {
				user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
			}
			user.setUsername(userDTO.getUsername());
			user.setEnabled(userDTO.isEnabled());
			user.setRoles(roles);
			usuariosRepository.save(user);
			return user;
	}


	public User FindByUsername(String username) {
		
		return usuariosRepository.findByUsername(username);
	}

	public User saveUsuarios(UserDTO userDTO) {
		User existingUser = usuariosRepository.findByUsername(userDTO.getUsername());
		List<Role> roles = new ArrayList<>();
		if (existingUser != null) {
			return null;
		}else{
			User user = new User();
			user.setUsername(userDTO.getUsername());
			user.setEnabled(userDTO.isEnabled());
			user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
			
			for (Role role : userDTO.getRoles()) {
				Role existingRole = authoritiesRepository.findByName(role.getName());
				roles.add(existingRole);
			}
			user.setRoles(roles);
			usuariosRepository.save(user);
			return user;
		}

	}





}
