package com.dextho.delegacion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
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
	public BCryptPasswordEncoder passwordEncoder() {
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
					auth.requestMatchers("/Dextho/**").hasRole("ADMIN");
					auth.anyRequest().authenticated();
					
				})
				.httpBasic()
				.and()
				.formLogin(form -> {
					form.loginPage("/login");
					form.permitAll();
					form.defaultSuccessUrl("/Dextho/home");

				})
				.exceptionHandling(exceptionHandling ->{
					exceptionHandling.accessDeniedPage("/access-denied");
					exceptionHandling.authenticationEntryPoint(authenticationEntryPoint());
				})
				.logout(logout -> logout.permitAll())
				.build();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint(){
		return (request, response, exception) ->{
			response.sendRedirect("/login");
		};
	}

}
