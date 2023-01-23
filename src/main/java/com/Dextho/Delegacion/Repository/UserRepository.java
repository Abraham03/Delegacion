package com.Dextho.Delegacion.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Dextho.Delegacion.Model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u INNER JOIN u.roles r WHERE u.username = :username AND u.enabled=true")
	User findByUsername(@Param("username") String username);

}
