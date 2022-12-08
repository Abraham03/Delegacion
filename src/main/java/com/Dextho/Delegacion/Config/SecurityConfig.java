package com.Dextho.Delegacion.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	

/*
	@Bean
	EmbeddedDatabase dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	*/

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth ->{
					auth.requestMatchers("/Dextho/tarea/tareas").permitAll();
					auth.anyRequest().authenticated();
				})
				.formLogin(form -> { 
					form.loginPage("/login");
					form.permitAll();
				})
				.logout(logout ->
				logout.permitAll()
				)
				.build();
	}
	
	
}
