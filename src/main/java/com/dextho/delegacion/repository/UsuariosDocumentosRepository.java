package com.dextho.delegacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.UsuariosDocumentos;

@Repository
public interface UsuariosDocumentosRepository extends CrudRepository<UsuariosDocumentos, Long> {
    @Query("SELECT NEW com.dextho.delegacion.dto.DocumentosUsuariosDTO(d.id,d.tipo_documento,d.nombre_documento, d.descripcion, d.fecha_emitida,d.nombre_delegado, d.ruta,"
            + "CONCAT(c.nombre, ' ',c.apellido_p, ' ',c.apellido_m),c.grupo, d.ciudadanos.id)  FROM UsuariosDocumentos d"
            + " INNER JOIN Ciudadanos c ON c.id = d.ciudadanos.id")
    Iterable<UsuariosDocumentos> findAll();

    @Query("SELECT d from UsuariosDocumentos d where d.id = :id")
    Optional<UsuariosDocumentos> findById(@Param("id") Long id);
}
