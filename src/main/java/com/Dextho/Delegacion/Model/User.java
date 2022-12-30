package com.Dextho.Delegacion.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column (name = "enabled", nullable = false)
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Tareas> tareas;
	//@ManyToOne(fetch = FetchType.EAGER)
	//private List<Role> roles;

	@ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
				name = "user_roles",
				joinColumns = { @JoinColumn(name = "user_id") },
				inverseJoinColumns = { @JoinColumn(name = "roles_id") }
			)
	private List<Role> roles;
	
	public User() {
		super();
	}

	public User(String username, String password, boolean enabled, List<Tareas> tareas, List<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.tareas = tareas;
		this.roles = roles;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Tareas> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tareas> tareas) {
		this.tareas = tareas;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}




	
}
