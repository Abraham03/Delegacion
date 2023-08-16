package com.dextho.delegacion.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "multas")
public class Multas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fecha_emitida", nullable = false)
	private LocalDate fecha_emitida;

	@Column(name = "fecha_limite", nullable = false)
	private LocalDate fecha_limite;

	@Column(name = "fecha_pagada", nullable = true)
	private LocalDate fecha_pagada;

	@Column(name = "monto", nullable = false)
	private BigDecimal monto;

	@Column(name = "pagado", nullable = false)
	private boolean pagado;

	@Column(name = "activo", nullable = true)
	private Boolean activo;

	@ManyToOne
	@JsonBackReference
	private Ciudadanos ciudadanos;

	public Multas() {
	}

	public Multas(String descripcion, LocalDate fecha_emitida, LocalDate fecha_limite, LocalDate fecha_pagada,
			BigDecimal monto, boolean pagado, Boolean activo, Ciudadanos ciudadanos) {
		this.descripcion = descripcion;
		this.fecha_emitida = fecha_emitida;
		this.fecha_limite = fecha_limite;
		this.fecha_pagada = fecha_pagada;
		this.monto = monto;
		this.pagado = pagado;
		this.activo = activo;
		this.ciudadanos = ciudadanos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getFecha_limite() {
		return this.fecha_limite;
	}

	public void setFecha_limite(LocalDate fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

	public LocalDate getFecha_pagada() {
		return this.fecha_pagada;
	}

	public void setFecha_pagada(LocalDate fecha_pagada) {
		this.fecha_pagada = fecha_pagada;
	}

	public BigDecimal getMonto() {
		return this.monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public boolean isPagado() {
		return this.pagado;
	}

	public boolean getPagado() {
		return this.pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Ciudadanos getCiudadanos() {
		return this.ciudadanos;
	}

	public void setCiudadanos(Ciudadanos ciudadanos) {
		this.ciudadanos = ciudadanos;
	}

}
