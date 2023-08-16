package com.dextho.delegacion.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextho.delegacion.dto.MultasUsuariosDTO;
import com.dextho.delegacion.model.Ciudadanos;
import com.dextho.delegacion.model.Multas;
import com.dextho.delegacion.repository.CiudadanosRepository;
import com.dextho.delegacion.repository.MultasRepository;

@Service
public class MultasServiceImp {
    @Autowired
    private MultasRepository multasRepository;

    @Autowired
    private CiudadanosRepository ciudadanosRepository;

    public List<Multas> getAllMultas() {
        return (List<Multas>) multasRepository.findAll();
    }

    public Multas saveMulta(MultasUsuariosDTO multasUsuariosDTO) {
        Optional<Ciudadanos> ciudadanos = ciudadanosRepository.findById(multasUsuariosDTO.getCiudadano_id());
        if (!ciudadanos.isPresent()) {
            return null;
        } else {
            Multas multas = new Multas(multasUsuariosDTO.getDescripcion(),
                    multasUsuariosDTO.getFecha_emitida(),
                    multasUsuariosDTO.getFecha_limite(),
                    multasUsuariosDTO.getFecha_pagada(),
                    multasUsuariosDTO.getMonto(),
                    multasUsuariosDTO.getPagado(),
                    true,
                    ciudadanos.get());

            return multasRepository.save(multas);
        }
    }

    public Multas updateMulta(MultasUsuariosDTO multasUsuariosDTO, Long id) {
        Optional<Multas> multasExistente = multasRepository.findById(id);
        if (multasExistente.isPresent()) {
            Multas multa = multasExistente.get();
            multa.setDescripcion(multasUsuariosDTO.getDescripcion());
            multa.setFecha_emitida(multasUsuariosDTO.getFecha_emitida());
            multa.setFecha_limite(multasUsuariosDTO.getFecha_limite());
            multa.setFecha_pagada(multasUsuariosDTO.getFecha_pagada());
            multa.setMonto(multasUsuariosDTO.getMonto());
            multa.setPagado(multasUsuariosDTO.getPagado());
            multa.setActivo(multasUsuariosDTO.getActivo());
            return multasRepository.save(multa);
        }
        return null;
    }
}