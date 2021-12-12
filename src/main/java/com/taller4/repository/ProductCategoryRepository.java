package com.taller4.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller4.model.prod.Productcategory;

public interface ProductCategoryRepository extends CrudRepository<Productcategory, Integer> {
	
}
