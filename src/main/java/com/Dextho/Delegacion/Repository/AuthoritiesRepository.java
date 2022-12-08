package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

}
