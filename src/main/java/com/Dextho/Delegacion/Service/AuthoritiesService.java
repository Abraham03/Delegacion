package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.Role;

public interface AuthoritiesService {

	List<Role> getAllAuthorities();
	Optional<Role> getAuthoritiesById(Long id);
	Role saveAuthorities(Role a);
	void updateAuthorities(Role a);
	boolean deleteAuthoritiesById(Long id);
}
