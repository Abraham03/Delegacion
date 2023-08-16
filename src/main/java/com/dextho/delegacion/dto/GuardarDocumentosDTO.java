package com.dextho.delegacion.dto;

import java.time.LocalDate;

public class GuardarDocumentosDTO {

    private String tipo_documento;
    private String descripcion;
    private LocalDate fecha_emitida;
    private String nombre_delegado;
    private Long ciudadanos;
    private String nombre_documento;
    private String ruta;

    public GuardarDocumentosDTO() {
    }

    public GuardarDocumentosDTO(String tipo_documento, String descripcion, LocalDate fecha_emitida,
            String nombre_delegado, Long ciudadanos, String nombre_documento, String ruta) {
        this.tipo_documento = tipo_documento;
        this.descripcion = descripcion;
        this.fecha_emitida = fecha_emitida;
        this.nombre_delegado = nombre_delegado;
        this.ciudadanos = ciudadanos;
        this.nombre_documento = nombre_documento;
        this.ruta = ruta;
    }

    public String getTipo_documento() {
        return this.tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
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

    public Long getCiudadanos() {
        return this.ciudadanos;
    }

    public void setCiudadanos(Long ciudadanos) {
        this.ciudadanos = ciudadanos;
    }

    public String getNombre_documento() {
        return this.nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public String getRuta() {
        return this.ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
