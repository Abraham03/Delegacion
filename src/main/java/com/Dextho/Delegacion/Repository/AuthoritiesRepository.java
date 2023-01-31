package com.Dextho.Delegacion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.Role;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Role, Long> {

    @Query("SELECT u FROM Role u WHERE u.name = :name")
    Role findByName(@Param("name") String name);

}
