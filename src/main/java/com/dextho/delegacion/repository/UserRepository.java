package com.dextho.delegacion.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dextho.delegacion.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u INNER JOIN u.roles r WHERE u.username = :username AND u.enabled=true")
	User findByUsername(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO user_roles (user_id, roles_id) VALUES (:userId, :roleId)", nativeQuery = true)
	void addRoleToUser(@Param("userId") Long userId, @Param("roleId") Long roleId);


}
