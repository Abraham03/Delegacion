package com.Dextho.Delegacion.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Tareas;

@Repository
public interface TareaRepository extends JpaRepository<Tareas, Long> {

	
	
}
