package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.B_Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<B_Authorities, Long> {

}
