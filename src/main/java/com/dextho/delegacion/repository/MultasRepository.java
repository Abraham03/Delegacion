package com.dextho.delegacion.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.Multas;

@Repository
public interface MultasRepository extends CrudRepository<Multas, Long> {

    @Query("SELECT NEW com.dextho.delegacion.dto.MultasUsuariosDTO(d.id,d.descripcion,d.fecha_emitida, d.fecha_limite, d.fecha_pagada,d.monto, d.pagado,"
            + "CONCAT(c.nombre, ' ',c.apellido_p, ' ',c.apellido_m),c.grupo,d.activo, d.ciudadanos.id)  "
            + "FROM Multas d INNER JOIN Ciudadanos c ON c.id = d.ciudadanos.id")

    Iterable<Multas> findAll();
}
