package com.Dextho.Delegacion.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Dextho.Delegacion.Model.Tareas;
import com.Dextho.Delegacion.Repository.TareaRepository;

@Service
public class TareaServiceImpl {

	@Autowired
	private TareaRepository tareaRepository;

	public List<Tareas> getAllTareas() {
		return (List<Tareas>) tareaRepository.findAll();

	}

	public Tareas saveTarea(Tareas tareas) {
		return tareaRepository.save(tareas);

	}

	public Optional<Tareas> getTareaById(Long id) {
		return tareaRepository.findById(id);

	}



}
