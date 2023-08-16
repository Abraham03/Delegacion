package com.dextho.delegacion.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.Cargos;

@Repository
public interface CargosRepository extends CrudRepository<Cargos, Long> {

    @Query("SELECT NEW com.dextho.delegacion.dto.CargosDTO(d.id,d.descripcion,d.fecha, d.nombre,"
            + "CONCAT(c.nombre, ' ',c.apellido_p, ' ',c.apellido_m),c.grupo, d.ciudadanos.id)  FROM Cargos d"
            + " INNER JOIN Ciudadanos c ON c.id = d.ciudadanos.id")

    Iterable<Cargos> findAll();
}
