package com.dextho.delegacion.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosDocumentos")
public class UsuariosDocumentos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_documento", nullable = false)
	private String tipo_documento;

	@Column(name = "nombre_documento", nullable = false)
	private String nombre_documento;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fecha_emitida", nullable = false)
	private LocalDate fecha_emitida;

	@Column(name = "nombre_delegado", nullable = false)
	private String nombre_delegado;

	@Column(name = "ruta", nullable = false)
	private String ruta;

	@ManyToOne
	@JoinColumn(name = "ciudadanos_id")
	@JsonBackReference
	private Ciudadanos ciudadanos;

	public UsuariosDocumentos() {
	}

	public UsuariosDocumentos(String tipo_documento, String nombre_documento, String descripcion,
			LocalDate fecha_emitida, String nombre_delegado, String ruta, Ciudadanos ciudadanos) {
		this.tipo_documento = tipo_documento;
		this.nombre_documento = nombre_documento;
		this.descripcion = descripcion;
		this.fecha_emitida = fecha_emitida;
		this.nombre_delegado = nombre_delegado;
		this.ruta = ruta;
		this.ciudadanos = ciudadanos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_documento() {
		return this.tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNombre_documento() {
		return this.nombre_documento;
	}

	public void setNombre_documento(String nombre_documento) {
		this.nombre_documento = nombre_documento;
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

	public String getNombre_delegado() {
		return this.nombre_delegado;
	}

	public void setNombre_delegado(String nombre_delegado) {
		this.nombre_delegado = nombre_delegado;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Ciudadanos getCiudadanos() {
		return this.ciudadanos;
	}

	public void setCiudadanos(Ciudadanos ciudadanos) {
		this.ciudadanos = ciudadanos;
	}

}
