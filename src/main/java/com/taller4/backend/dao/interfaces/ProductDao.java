package com.taller4.backend.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.taller4.backend.model.prod.Product;

public interface ProductDao {
	void save(Product product);
	void update(Product product);
	void delete(Product product);
	
	List<Product> findAll();
	Product findById(Integer id);
	Product findByProductNumber(String productnumber);	
	List<Product> findByStyle(String style);
	List<?> findByDateRange(LocalDate sellstartdate, LocalDate sellenddate);
	List<String> findAllProductnumbers();
}
