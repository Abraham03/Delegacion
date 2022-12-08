package com.Dextho.Delegacion.ServicesImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Dextho.Delegacion.Model.Tareas;
import com.Dextho.Delegacion.Repository.TareaRepository;
import com.Dextho.Delegacion.Service.TareaService;
@Service
public class TareaServiceImpl implements TareaService{

	@Autowired
	TareaRepository tareaRepository;
	
	@Override
	public ArrayList<Tareas> getAllTareas() {
		return (ArrayList<Tareas>) tareaRepository.findAll();
	}

	@Override
	public Optional<Tareas> getTareaById(Long id) {
		return tareaRepository.findById(id);
	}

	@Override
	public Tareas saveTarea(Tareas u) {
		return tareaRepository.save(u);
	}
	
	@Override
	public void updateTarea(Tareas t) {
	/*	
	 * Solo se modificara Nombre, Descripcion y Prioridad
	 * Tareas tarea = tareaRepository.findById(id).get();
		System.out.println(tarea.toString());
		tarea.setNombre(t.getNombre());
		tarea.setDescripcion(t.getDescripcion());
		tarea.setPrioridad(t.getPrioridad());
		tarea.setFecha_Modificado(t.getFecha_Modificado());
		tarea.setIdUsuarioModificado(t.getIdUsuarioModificado());*/
		tareaRepository.save(t);
	}

	@Override
	public boolean deleteTareaById(Long id) {
		try {
			Optional<Tareas> u = getTareaById(id);
			tareaRepository.delete(u.get());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
