package com.Dextho.Delegacion.Service;

import java.util.ArrayList;
import java.util.Optional;

import com.Dextho.Delegacion.Model.Tareas;

public interface TareaService {

	ArrayList<Tareas> getAllTareas();
	Optional<Tareas> getTareaById(Long id);
	Tareas saveTarea(Tareas u);
	void updateTarea(Tareas t);
	boolean deleteTareaById(Long id);
	
}
