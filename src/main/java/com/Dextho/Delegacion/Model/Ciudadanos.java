package com.Dextho.Delegacion.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ciudadanos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudadanos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido_p", nullable = false)
	private String apellido_p;
	
	@Column(name = "apellido_m", nullable = false)
	private String apellido_m;
	
	@Column(name = "fecha_Nacimiento", nullable = false)
	private LocalDate fecha_Nacimiento;
	
	@Column(name = "fecha_Ingreso", nullable = false)
	private LocalDate fecha_Ingreso;
	
	@Column(name = "grupo", nullable = false)
	private int grupo;
	
	@Column(name = "idCargo", nullable = false)
	private int idCargo;
	
	@Column(name = "vive_pueblo", nullable = false)
	private String vive_pueblo;
	
	@Column(name = "representante", nullable = false)
	private String representante;
	
	@Column(name = "idStatus", nullable = false)
	private int idStatus;

	
}
