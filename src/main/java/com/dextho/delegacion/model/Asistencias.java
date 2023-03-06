package com.dextho.delegacion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asistencias")
@AllArgsConstructor
@NoArgsConstructor
public class Asistencias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "idCiudadano", nullable = false)
	private String idCiudadano;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;
	
}
