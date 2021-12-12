package com.taller4.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.taller4.model.prod.Product;

public interface ProductDao {
	void save(Product product);
	void update(Product product);
	void delete(Product product);
	
	List<Product> findAll();
	Product findById(Integer id);
	Product findByProductNumber(String productnumber);	
	List<Product> findByStyle(String style);
	List<Object[]> findByDateRange(LocalDate sellstartdate, LocalDate sellenddate);
}
