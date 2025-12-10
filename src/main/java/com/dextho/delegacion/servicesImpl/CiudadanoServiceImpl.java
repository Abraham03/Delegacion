package com.dextho.delegacion.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.dto.CiudadanosDTO;
import com.dextho.delegacion.model.Ciudadanos;
import com.dextho.delegacion.model.EstatusCiudadanos;
import com.dextho.delegacion.repository.CiudadanosRepository;

@Service
public class CiudadanoServiceImpl {

    @Autowired
    private CiudadanosRepository ciudadanosRepository;

    @Autowired
    private EstatusCiudadanoServiceImp estatusCiudadanoServiceImp;

    public List<Ciudadanos> getAllCiudadanos() {
        List<Ciudadanos> ciudadanos = ciudadanosRepository.findAll();
        return ciudadanos;
    }

    public List<Ciudadanos> getAllActivos() {
        List<Ciudadanos> ciudadanos = ciudadanosRepository.findAllActivos();
        return ciudadanos;
    }

    public List<Ciudadanos> getAllByEstatus(String nombreEstatus) {
        if (nombreEstatus == null || nombreEstatus.equalsIgnoreCase("Todos")) {
            return ciudadanosRepository.findAll();
        }
        return ciudadanosRepository.findByEstatusNombre(nombreEstatus);
    }

    public List<Ciudadanos> getAllByGrupo(int grupo) {
        List<Ciudadanos> ciudadanos = (List<Ciudadanos>) ciudadanosRepository.findByGrupo(grupo);
        return ciudadanos;
    }

    public Optional<Ciudadanos> getCiudadanoById(Long id) {
        return ciudadanosRepository.findById(id);
    }

    public Ciudadanos saveCiudadano(CiudadanosDTO ciudadanosDTO) {
        Long id = ciudadanosDTO.getId_status();
        Optional<EstatusCiudadanos> estatusCiudadanos = estatusCiudadanoServiceImp.findEstatusCiudadanosById(id);
        if (!estatusCiudadanos.isPresent()) {
            return null;
        } else {
            Ciudadanos ciudadanos = new Ciudadanos(ciudadanosDTO.getNombre(),
                    ciudadanosDTO.getApellido_p(),
                    ciudadanosDTO.getApellido_m(),
                    ciudadanosDTO.getFecha_Nacimiento(),
                    ciudadanosDTO.getFecha_Ingreso(),
                    ciudadanosDTO.getGrupo(),
                    ciudadanosDTO.getVive_pueblo(),
                    ciudadanosDTO.getRepresentante(),
                    estatusCiudadanos.get());
            return ciudadanosRepository.save(ciudadanos);
        }

    }

    public Ciudadanos updateCiudadanos(CiudadanosDTO ciudadanosDTO, Long id) {
        Optional<Ciudadanos> ciudadanosExistente = getCiudadanoById(id);
        Optional<EstatusCiudadanos> estatusCiudadanos = estatusCiudadanoServiceImp
                .findEstatusCiudadanosById(ciudadanosDTO.getId_status());
        if (ciudadanosExistente.isPresent()) {
            Ciudadanos ciudadanos = ciudadanosExistente.get();
            ciudadanos.setNombre(ciudadanosDTO.getNombre());
            ciudadanos.setApellido_p(ciudadanosDTO.getApellido_p());
            ciudadanos.setApellido_m(ciudadanosDTO.getApellido_m());
            ciudadanos.setFecha_Nacimiento(ciudadanosDTO.getFecha_Nacimiento());
            ciudadanos.setFecha_Ingreso(ciudadanosDTO.getFecha_Ingreso());
            ciudadanos.setGrupo(ciudadanosDTO.getGrupo());
            ciudadanos.setVive_pueblo(ciudadanosDTO.getVive_pueblo());
            ciudadanos.setRepresentante(ciudadanosDTO.getRepresentante());
            ciudadanos.setEstatusCiudadanos(estatusCiudadanos.get());
            return ciudadanosRepository.save(ciudadanos);
        }
        return null;

    }

    public boolean deleteCiudadanoById(Long id) {
        try {
            Optional<Ciudadanos> c = getCiudadanoById(id);
            ciudadanosRepository.delete(c.get());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
