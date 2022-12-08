package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Usuarios;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Long> {

}
