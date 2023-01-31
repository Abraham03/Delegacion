package com.Dextho.Delegacion.Config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.Role;
import com.Dextho.Delegacion.Model.User;
import com.Dextho.Delegacion.Repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User usuarios = userRepository.findByUsername(username);
		if (usuarios == null || !usuarios.isEnabled()) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities = getAuthorities(usuarios.getRoles());
		return new org.springframework.security.core.userdetails.User(usuarios.getUsername(), usuarios.getPassword(),
				authorities);
	}

	private List<GrantedAuthority> getAuthorities(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));

		}
		return authorities;
	}

}
