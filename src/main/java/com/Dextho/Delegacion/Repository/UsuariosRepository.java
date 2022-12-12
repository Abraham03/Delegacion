package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.A_Usuarios;

@Repository
public interface UsuariosRepository extends CrudRepository<A_Usuarios, Long> {

}
