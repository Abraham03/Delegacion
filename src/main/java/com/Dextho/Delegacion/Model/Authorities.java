package com.Dextho.Delegacion.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "authority", nullable = false)
	private String authority;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
	private Usuarios usuarios;

	
	
	public Authorities() {
	}

	public Authorities(String username, String authority, Usuarios usuarios) {
		super();
		this.username = username;
		this.authority = authority;
		this.usuarios = usuarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}


	

	
	
}
