package com.dextho.delegacion.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.model.Role;
import com.dextho.delegacion.repository.AuthoritiesRepository;

@Service
public class AuthoritiesServiceImp {

	@Autowired
	AuthoritiesRepository authoritiesRepository;
	

	public List<Role> getAllAuthorities() {
		return (List<Role>) authoritiesRepository.findAll();
	}


	public Optional<Role> getAuthoritiesById(Long id) {
		return authoritiesRepository.findById(id);
	}


	public Role saveAuthorities(Role a) {
		return authoritiesRepository.save(a);
	}


	public void updateAuthorities(Role a) {
		authoritiesRepository.save(a);
	}


	public boolean deleteAuthoritiesById(Long id) {
		try {
			Optional<Role> a = getAuthoritiesById(id);
			authoritiesRepository.delete(a.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
