package com.Dextho.Delegacion.Model;

import java.time.LocalDateTime;


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
	
	@Column(name = "fecha_Modificado", nullable = false)
	private LocalDateTime fecha_Modificado;
	
	@Column(name = "id_Usuario_Modificado")
	private Long IdUsuarioModificado;

	public Tareas() {
		super();
	}

	public Tareas(String nombre, String descripcion, String prioridad, LocalDateTime fecha_Modificado,
			Long idUsuarioModificado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prioridad = prioridad;
		this.fecha_Modificado = fecha_Modificado;
		IdUsuarioModificado = idUsuarioModificado;
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

	public LocalDateTime getFecha_Modificado() {
		return fecha_Modificado;
	}

	public void setFecha_Modificado(LocalDateTime fecha_Modificado) {
		this.fecha_Modificado = fecha_Modificado;
	}

	public Long getIdUsuarioModificado() {
		return IdUsuarioModificado;
	}

	public void setIdUsuarioModificado(Long idUsuarioModificado) {
		IdUsuarioModificado = idUsuarioModificado;
	}

}
