package com.dextho.delegacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.EstatusCiudadanos;

@Repository
public interface EstatusCiudadanoRepository extends CrudRepository<EstatusCiudadanos, Long>{
    
}
