package com.dextho.delegacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.Ciudadanos;

@Repository
public interface CiudadanosRepository extends CrudRepository<Ciudadanos, Long> {

}
