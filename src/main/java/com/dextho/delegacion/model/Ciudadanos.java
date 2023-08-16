package com.dextho.delegacion.model;

import java.time.LocalDate;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudadanos")
public class Ciudadanos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "apellido_p", nullable = false)
	private String apellido_p;

	@Column(name = "apellido_m", nullable = true)
	private String apellido_m;

	@Column(name = "fecha_Nacimiento", nullable = true)
	private LocalDate fecha_Nacimiento;

	@Column(name = "fecha_Ingreso", nullable = true)
	private LocalDate fecha_Ingreso;

	@Column(name = "grupo", nullable = false)
	private int grupo;

	@Column(name = "vive_pueblo", nullable = false)
	private String vive_pueblo;

	@Column(name = "representante", nullable = true)
	private String representante;

	@ManyToOne
	@JoinColumn(name = "idStatus", unique = false, nullable = true, updatable = true)
	private EstatusCiudadanos estatusCiudadanos;

	@OneToMany(mappedBy = "ciudadanos", cascade = CascadeType.ALL)
	@Nullable
	private List<UsuariosDocumentos> usuariosDocumentos;

	@OneToMany(mappedBy = "ciudadanos", cascade = CascadeType.ALL)
	@jakarta.annotation.Nullable
	private List<Multas> multas;

	@OneToMany(mappedBy = "ciudadanos", cascade = CascadeType.ALL)
	@Nullable
	private List<Cargos> cargos;

	public Ciudadanos() {
	}

	public Ciudadanos(String nombre, String apellido_p, String apellido_m, LocalDate fecha_Nacimiento,
			LocalDate fecha_Ingreso, int grupo, String vive_pueblo, String representante,
			EstatusCiudadanos estatusCiudadanos) {
		this.nombre = nombre;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.fecha_Ingreso = fecha_Ingreso;
		this.grupo = grupo;
		this.vive_pueblo = vive_pueblo;
		this.representante = representante;
		this.estatusCiudadanos = estatusCiudadanos;
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

	public String getApellido_p() {
		return this.apellido_p;
	}

	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}

	public String getApellido_m() {
		return this.apellido_m;
	}

	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}

	public LocalDate getFecha_Nacimiento() {
		return this.fecha_Nacimiento;
	}

	public void setFecha_Nacimiento(LocalDate fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}

	public LocalDate getFecha_Ingreso() {
		return this.fecha_Ingreso;
	}

	public void setFecha_Ingreso(LocalDate fecha_Ingreso) {
		this.fecha_Ingreso = fecha_Ingreso;
	}

	public int getGrupo() {
		return this.grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public String getVive_pueblo() {
		return this.vive_pueblo;
	}

	public void setVive_pueblo(String vive_pueblo) {
		this.vive_pueblo = vive_pueblo;
	}

	public String getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public EstatusCiudadanos getEstatusCiudadanos() {
		return this.estatusCiudadanos;
	}

	public void setEstatusCiudadanos(EstatusCiudadanos estatusCiudadanos) {
		this.estatusCiudadanos = estatusCiudadanos;
	}

	public List<UsuariosDocumentos> getUsuariosDocumentos() {
		return this.usuariosDocumentos;
	}

	public void setUsuariosDocumentos(List<UsuariosDocumentos> usuariosDocumentos) {
		this.usuariosDocumentos = usuariosDocumentos;
	}

	public List<Multas> getMultas() {
		return this.multas;
	}

	public void setMultas(List<Multas> multas) {
		this.multas = multas;
	}

	public List<Cargos> getCargos() {
		return this.cargos;
	}

	public void setCargos(List<Cargos> cargos) {
		this.cargos = cargos;
	}

}
