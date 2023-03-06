package com.dextho.delegacion.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tareas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "prioridad", nullable = false)
	private String prioridad;

	@Column(name = "estatus", nullable = false)
	private String estatus;	
	
	@Column(name = "fecha_Creado", nullable = false)
	private LocalDate fecha_Creado;

	@Column(name = "activo", nullable = false)
	private Boolean activo;
	
	public Tareas() {
		super();
	}

	public Tareas(String nombre, String descripcion, String prioridad, String estatus, LocalDate fecha_Creado,
			Boolean activo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.estatus = estatus;
		this.fecha_Creado = fecha_Creado;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public LocalDate getFecha_Creado() {
		return fecha_Creado;
	}

	public void setFecha_Creado(LocalDate fecha_Creado) {
		this.fecha_Creado = fecha_Creado;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}


}
