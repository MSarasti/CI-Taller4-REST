package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.prod.*;

public interface ProductService {
	public Product saveProduct(Product p, Integer pCatId, Integer pSubId, Integer um1Id, Integer um2Id);
	public Product searchProduct(Integer pId);
	public Product updateProduct(Integer pId, Product p);
	public void deleteProduct(Integer pId);
	public Productcategory saveProductCategory(Productcategory pc);
	public Productcategory searchProductCategory(Integer pcId);
	public Productcategory updateProductCategory(Integer pcId, Productcategory pc);
	public void deleteProductCategory(Integer pcId);
	public Productsubcategory saveProductSubcategory(Productsubcategory psc);
	public Productsubcategory searchProductSubcategory(Integer pscId);
	public Productsubcategory updateProductSubcategory(Integer pscId, Productsubcategory psc);
	public void deleteProductSubcategory(Integer pscId);
	public Unitmeasure saveUnitmeasure(Unitmeasure um);
	public Unitmeasure searchUnitmeasure(Integer umId);
	public void deleteUnitmeasure(Integer umId);
	public Product findById(Integer id);
	public Iterable<Product> findAllProducts();
	public Iterable<Productcategory> findAllCategories();
	public Iterable<Productsubcategory> findAllSubcategories();
	public Iterable<Unitmeasure> findAllUnits();
}
