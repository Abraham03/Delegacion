package com.Dextho.Delegacion.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.UserRoles;


@Repository
public interface UserRolesRepository extends CrudRepository<UserRoles, Long> {

	
}
