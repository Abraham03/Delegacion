package com.dextho.delegacion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.UserRoles;


@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {

	
}
