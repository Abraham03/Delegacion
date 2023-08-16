package com.dextho.delegacion.dto;

import java.time.LocalDate;

public class CargosDTO {
    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String nombre;
    private String nombreUsuario;
    private int grupo;
    private Long ciudadano_id;

    public CargosDTO() {
    }

    public CargosDTO(Long id, String descripcion, LocalDate fecha, String nombre, String nombreUsuario, int grupo,
            Long ciudadano_id) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.grupo = grupo;
        this.ciudadano_id = ciudadano_id;
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

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getGrupo() {
        return this.grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public Long getCiudadano_id() {
        return this.ciudadano_id;
    }

    public void setCiudadano_id(Long ciudadano_id) {
        this.ciudadano_id = ciudadano_id;
    }

}
