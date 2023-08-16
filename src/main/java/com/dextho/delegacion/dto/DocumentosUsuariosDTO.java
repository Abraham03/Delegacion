package com.dextho.delegacion.dto;

import java.time.LocalDate;

public class DocumentosUsuariosDTO {
    private Long id;
    private String tipo_documento;
    private String nombre_documento;
    private String descripcion;
    private LocalDate fecha_emitida;
    private String nombre_delegado;
    private String ruta;
    private String nombreUsuario;
    private int grupo;
    private Long ciudadano_id;

    public DocumentosUsuariosDTO() {
    }

    public DocumentosUsuariosDTO(Long id, String tipo_documento, String nombre_documento, String descripcion,
            LocalDate fecha_emitida, String nombre_delegado, String ruta, String nombreUsuario, int grupo,
            Long ciudadano_id) {
        this.id = id;
        this.tipo_documento = tipo_documento;
        this.nombre_documento = nombre_documento;
        this.descripcion = descripcion;
        this.fecha_emitida = fecha_emitida;
        this.nombre_delegado = nombre_delegado;
        this.ruta = ruta;
        this.nombreUsuario = nombreUsuario;
        this.grupo = grupo;
        this.ciudadano_id = ciudadano_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_emitida() {
        return fecha_emitida;
    }

    public void setFecha_emitida(LocalDate fecha_emitida) {
        this.fecha_emitida = fecha_emitida;
    }

    public String getNombre_delegado() {
        return nombre_delegado;
    }

    public void setNombre_delegado(String nombre_delegado) {
        this.nombre_delegado = nombre_delegado;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public Long getCiudadano_id() {
        return ciudadano_id;
    }

    public void setCiudadano_id(Long ciudadano_id) {
        this.ciudadano_id = ciudadano_id;
    }

}
