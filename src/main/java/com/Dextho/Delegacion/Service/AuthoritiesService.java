package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;

import com.Dextho.Delegacion.Model.B_Authorities;

public interface AuthoritiesService {

	List<B_Authorities> getAllAuthorities();
	Optional<B_Authorities> getAuthoritiesById(Long id);
	B_Authorities saveAuthorities(B_Authorities a);
	void updateAuthorities(B_Authorities a);
	boolean deleteAuthoritiesById(Long id);
}
