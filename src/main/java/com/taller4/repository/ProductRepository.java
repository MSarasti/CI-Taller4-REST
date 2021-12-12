package com.taller4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.taller4.model.prod.*;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	List<Product> findByName(Object name);
}
