package com.Dextho.Delegacion.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudadanos")
public class Ciudadanos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	
	public Ciudadanos() {
		
	}
	public Ciudadanos(long id, String nombre, String apellido_p, String apellido_m, LocalDate fecha_Nacimiento,
			LocalDate fecha_Ingreso, int grupo, int idCargo, String vive_pueblo, String representante, int idStatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido_p = apellido_p;
		this.apellido_m = apellido_m;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.fecha_Ingreso = fecha_Ingreso;
		this.grupo = grupo;
		this.idCargo = idCargo;
		this.vive_pueblo = vive_pueblo;
		this.representante = representante;
		this.idStatus = idStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_p() {
		return apellido_p;
	}
	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}
	public String getApellido_m() {
		return apellido_m;
	}
	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}
	public LocalDate getFecha_Nacimiento() {
		return fecha_Nacimiento;
	}
	public void setFecha_Nacimiento(LocalDate fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}
	public LocalDate getFecha_Ingreso() {
		return fecha_Ingreso;
	}
	public void setFecha_Ingreso(LocalDate fecha_Ingreso) {
		this.fecha_Ingreso = fecha_Ingreso;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	public String getVive_pueblo() {
		return vive_pueblo;
	}
	public void setVive_pueblo(String vive_pueblo) {
		this.vive_pueblo = vive_pueblo;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public int getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}
	
	
	
	
	
}
