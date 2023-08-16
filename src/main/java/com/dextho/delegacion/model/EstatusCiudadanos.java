package com.dextho.delegacion.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "estatusCiudadanos")
public class EstatusCiudadanos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fecha_emitida", nullable = false)
	private LocalDate fecha_emitida;

	@OneToMany(mappedBy = "estatusCiudadanos")
	@JsonBackReference
	private Set<Ciudadanos> ciudadanos;

	public EstatusCiudadanos() {
	}

	public EstatusCiudadanos(String nombre, String descripcion, LocalDate fecha_emitida) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_emitida = fecha_emitida;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha_emitida() {
		return this.fecha_emitida;
	}

	public void setFecha_emitida(LocalDate fecha_emitida) {
		this.fecha_emitida = fecha_emitida;
	}

	public Set<Ciudadanos> getCiudadanos() {
		return this.ciudadanos;
	}

	public void setCiudadanos(Set<Ciudadanos> ciudadanos) {
		this.ciudadanos = ciudadanos;
	}

}
