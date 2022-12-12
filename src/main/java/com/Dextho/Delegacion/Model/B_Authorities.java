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
@Table(name = "b_authorities")
public class B_Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "authority", nullable = false)
	private String authority;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
	A_Usuarios a_usuarios;

	
	
	public B_Authorities() {
	}

	public B_Authorities(String username, String authority, A_Usuarios a_usuarios) {
		super();
		this.username = username;
		this.authority = authority;
		this.a_usuarios = a_usuarios;
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

	@Override
	public String toString() {
		return "B_Authorities [id=" + id + ", username=" + username + ", authority=" + authority + "]";
	}




	

	
	
}
