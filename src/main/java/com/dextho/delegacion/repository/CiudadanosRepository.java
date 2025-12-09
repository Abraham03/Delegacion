package com.dextho.delegacion.repository;

import com.dextho.delegacion.model.Ciudadanos;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadanosRepository extends CrudRepository<Ciudadanos, Long> {

    @Override
    @Query("SELECT c  FROM Ciudadanos c "
            + " INNER JOIN  EstatusCiudadanos e ")
    List<Ciudadanos> findAll();

    @Query("SELECT c  FROM Ciudadanos c "
            + " INNER JOIN  c.estatusCiudadanos e "
            + " WHERE e.nombre = 'Activo'")
    List<Ciudadanos> findAllActivos();

    @Query("SELECT NEW com.dextho.delegacion.dto.CiudadanosDocumentosDTO(c.id,CONCAT(c.nombre, ' ',c.apellido_p, ' ',c.apellido_m), c.grupo) "
            + "FROM Ciudadanos c "
            + "WHERE c.grupo = :grupo")
    Iterable<Ciudadanos> findByGrupo(@Param("grupo") int grupo);
}
