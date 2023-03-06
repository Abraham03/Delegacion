package com.dextho.delegacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.Tareas;

@Repository
public interface TareaRepository extends CrudRepository<Tareas, Long> {

	
}
