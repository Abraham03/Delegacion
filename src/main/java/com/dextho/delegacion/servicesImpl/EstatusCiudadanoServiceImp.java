package com.dextho.delegacion.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.model.EstatusCiudadanos;
import com.dextho.delegacion.repository.EstatusCiudadanoRepository;

@Service
public class EstatusCiudadanoServiceImp {

    @Autowired
    private EstatusCiudadanoRepository estatusCiudadanoRepository;

    public List<EstatusCiudadanos> getAllEstatusCiudadanos() {
        return (List<EstatusCiudadanos>) estatusCiudadanoRepository.findAll();
    }

    public EstatusCiudadanos saveEstatusCiudadanos(EstatusCiudadanos estatusCiudadanos) {
        EstatusCiudadanos guardar = estatusCiudadanoRepository.save(estatusCiudadanos);
        if (!guardar.equals(null)) {
            return guardar;
        }
        return null;
    }

    public EstatusCiudadanos updateEstatus(EstatusCiudadanos estatusCiudadanos, Long id) {
        Optional<EstatusCiudadanos> estatusExistente = estatusCiudadanoRepository.findById(id);
        if (estatusExistente.isPresent()) {
            EstatusCiudadanos nuevoEstatus = estatusExistente.get();
            nuevoEstatus.setNombre(estatusCiudadanos.getNombre());
            nuevoEstatus.setDescripcion(estatusCiudadanos.getDescripcion());
            nuevoEstatus.setFecha_emitida(estatusCiudadanos.getFecha_emitida());
            return estatusCiudadanoRepository.save(nuevoEstatus);
        }
        return null;
    }

    public Optional<EstatusCiudadanos> findEstatusCiudadanosById(Long id) {
        return estatusCiudadanoRepository.findById(id);
    }

}
