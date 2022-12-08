package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Ciudadanos;

@Repository
public interface CiudadanosRepository extends CrudRepository<Ciudadanos, Long> {

}
