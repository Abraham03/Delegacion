package com.Dextho.Delegacion.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.Authorities;
import com.Dextho.Delegacion.Repository.AuthoritiesRepository;
import com.Dextho.Delegacion.Service.AuthoritiesService;

@Service
public class AuthoritiesServiceImp implements AuthoritiesService {

	@Autowired
	AuthoritiesRepository authoritiesRepository;
	
	@Override
	public List<Authorities> getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Authorities> getAuthoritiesById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Authorities saveAuthorities(Authorities a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAuthorities(Authorities a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteAuthoritiesById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
