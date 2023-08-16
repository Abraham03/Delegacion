package com.dextho.delegacion.dto;

public class CiudadanosDocumentosDTO {
    private Long id;
    private String nombre;
    private int grupo;

    public CiudadanosDocumentosDTO() {
    }


    public CiudadanosDocumentosDTO(Long id, String nombre, int grupo) {
        this.id = id;
        this.nombre = nombre;
        this.grupo = grupo;
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

    public int getGrupo() {
        return this.grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }


}


