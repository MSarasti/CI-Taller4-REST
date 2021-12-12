package com.taller4.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller4.backend.model.prod.*;

public interface UnitmeasureRepository extends CrudRepository<Unitmeasure, Integer> {
	List<Unitmeasure> findByName(String name);
}
