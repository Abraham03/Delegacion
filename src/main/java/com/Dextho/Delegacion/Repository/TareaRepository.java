package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Tareas;

@Repository
public interface TareaRepository extends CrudRepository<Tareas, Long> {

	
	
}
