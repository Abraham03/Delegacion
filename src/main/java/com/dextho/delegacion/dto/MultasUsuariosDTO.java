package com.dextho.delegacion.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MultasUsuariosDTO {
    private Long id;
    private String descripcion;
    private LocalDate fecha_emitida;
    private LocalDate fecha_limite;
    private LocalDate fecha_pagada;
    private BigDecimal monto;
    private Boolean pagado;
    private String nombreUsuario;
    private int grupo;
    private Boolean activo;
    private Long ciudadano_id;

    public MultasUsuariosDTO() {
    }

    public MultasUsuariosDTO(Long id, String descripcion, LocalDate fecha_emitida, LocalDate fecha_limite,
            LocalDate fecha_pagada, BigDecimal monto, Boolean pagado, String nombreUsuario, int grupo, Boolean activo,
            Long ciudadano_id) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha_emitida = fecha_emitida;
        this.fecha_limite = fecha_limite;
        this.fecha_pagada = fecha_pagada;
        this.monto = monto;
        this.pagado = pagado;
        this.nombreUsuario = nombreUsuario;
        this.grupo = grupo;
        this.activo = activo;
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

    public Boolean isPagado() {
        return this.pagado;
    }

    public Boolean getPagado() {
        return this.pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
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

    public Boolean isActivo() {
        return this.activo;
    }

    public Boolean getActivo() {
        return this.activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getCiudadano_id() {
        return this.ciudadano_id;
    }

    public void setCiudadano_id(Long ciudadano_id) {
        this.ciudadano_id = ciudadano_id;
    }

}
