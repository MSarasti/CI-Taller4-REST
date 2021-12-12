package com.taller4.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller4.backend.model.prod.Productcategory;

public interface ProductCategoryRepository extends CrudRepository<Productcategory, Integer> {
	
}
