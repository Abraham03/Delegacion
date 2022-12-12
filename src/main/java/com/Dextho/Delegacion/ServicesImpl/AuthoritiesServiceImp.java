package com.Dextho.Delegacion.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.B_Authorities;
import com.Dextho.Delegacion.Repository.AuthoritiesRepository;
import com.Dextho.Delegacion.Service.AuthoritiesService;

@Service
public class AuthoritiesServiceImp implements AuthoritiesService {

	@Autowired
	AuthoritiesRepository authoritiesRepository;
	
	@Override
	public List<B_Authorities> getAllAuthorities() {
		return (List<B_Authorities>) authoritiesRepository.findAll();
	}

	@Override
	public Optional<B_Authorities> getAuthoritiesById(Long id) {
		return authoritiesRepository.findById(id);
	}

	@Override
	public B_Authorities saveAuthorities(B_Authorities a) {
		return authoritiesRepository.save(a);
	}

	@Override
	public void updateAuthorities(B_Authorities a) {
		authoritiesRepository.save(a);
	}

	@Override
	public boolean deleteAuthoritiesById(Long id) {
		try {
			Optional<B_Authorities> a = getAuthoritiesById(id);
			authoritiesRepository.delete(a.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
