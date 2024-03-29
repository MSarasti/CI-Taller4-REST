package com.taller4.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller4.backend.model.person.*;

public interface UserRepository extends CrudRepository<UserApp, Long> {
	List<UserApp> findByUsername(String username);
	List<UserApp> findByType(UserType type);
}
