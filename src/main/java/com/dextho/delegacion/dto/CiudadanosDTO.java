package com.dextho.delegacion.dto;

import java.time.LocalDate;

public class CiudadanosDTO {
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private LocalDate fecha_Nacimiento;
    private LocalDate fecha_Ingreso;
    private int grupo;
    private String vive_pueblo;
    private String representante;
    private Long id_status;

    public CiudadanosDTO() {
    }

    public CiudadanosDTO(String nombre, String apellido_p, String apellido_m, LocalDate fecha_Nacimiento,
            LocalDate fecha_Ingreso, int grupo, String vive_pueblo, String representante, Long id_status) {
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.fecha_Nacimiento = fecha_Nacimiento;
        this.fecha_Ingreso = fecha_Ingreso;
        this.grupo = grupo;
        this.vive_pueblo = vive_pueblo;
        this.representante = representante;
        this.id_status = id_status;
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

    public Long getId_status() {
        return this.id_status;
    }

    public void setId_status(Long id_status) {
        this.id_status = id_status;
    }

}
