package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.Authorities;

public interface AuthoritiesService {

	List<Authorities> getAllAuthorities();
	Optional<Authorities> getAuthoritiesById(Long id);
	Authorities saveAuthorities(Authorities a);
	void updateAuthorities(Authorities a);
	boolean deleteAuthoritiesById(Long id);
}
