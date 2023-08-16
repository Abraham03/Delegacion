package com.dextho.delegacion.servicesImpl;

import com.dextho.delegacion.model.Ciudadanos;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.dto.CargosDTO;
import com.dextho.delegacion.model.Cargos;
import com.dextho.delegacion.repository.CargosRepository;
import com.dextho.delegacion.repository.CiudadanosRepository;

@Service
public class CargosServiceImp {

    @Autowired
    private CargosRepository cargosRepository;

    @Autowired
    private CiudadanosRepository ciudadanosRepository;

    public List<Cargos> getAllCargos() {
        return (List<Cargos>) cargosRepository.findAll();
    }

    public Cargos saveCargo(CargosDTO cargosDTO) {
        Optional<Ciudadanos> ciudadanos = ciudadanosRepository.findById(cargosDTO.getCiudadano_id());
        if (ciudadanos.isPresent()) {
            Cargos cargos = new Cargos(null, cargosDTO.getNombre(),
                    cargosDTO.getFecha(),
                    cargosDTO.getDescripcion(),
                    ciudadanos.get());
            return cargosRepository.save(cargos);
        }
        return null;
    }

    public Cargos updateCargo(CargosDTO cargosDTO, Long id) {
        Optional<Cargos> cargoExistente = cargosRepository.findById(id);
        if (cargoExistente.isPresent()) {
            Cargos cargo = cargoExistente.get();
            cargo.setNombre(cargosDTO.getNombre());
            cargo.setDescripcion(cargosDTO.getDescripcion());
            cargo.setFecha(cargosDTO.getFecha());
            return cargosRepository.save(cargo);
        }
        return null;
    }

}
