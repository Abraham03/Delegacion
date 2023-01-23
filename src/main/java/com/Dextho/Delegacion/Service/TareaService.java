package com.Dextho.Delegacion.Service;

import java.util.List;
import java.util.Optional;


import com.Dextho.Delegacion.Model.Tareas;

public interface TareaService {

	List<Tareas>  getAllTareas();
	
	Tareas saveTarea(Tareas tareas);
	
	Optional<Tareas> getTareaById(Long id);
	
	void deleteTareaById(Long id);

	
}
