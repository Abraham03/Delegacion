package com.Dextho.Delegacion.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailService;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.cors().and()
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers(HttpMethod.GET, "/js/**").permitAll();
					auth.requestMatchers("/styles/css/main.css","/login").permitAll();
					auth.requestMatchers("/Dextho/**").hasAnyAuthority("ADMIN","USER");
					auth.anyRequest().authenticated();
					
				})
				.httpBasic()
				.and()
				.formLogin(form -> {
					form.loginPage("/login");
					form.permitAll();
					form.defaultSuccessUrl("/Dextho/home");

				})
				.logout(logout -> logout.permitAll())
				.build();
	}

}
