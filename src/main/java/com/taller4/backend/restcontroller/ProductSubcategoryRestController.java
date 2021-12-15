package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/productsubcategory")
public class ProductSubcategoryRestController {
	
	private ProductService prodService;

	@Autowired
	public ProductSubcategoryRestController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@PostMapping
	public Productsubcategory add(@RequestBody Productsubcategory productsubcategory) {
		return prodService.saveProductSubcategory(productsubcategory);
	}
	
	@PutMapping
	public void update(@RequestBody Productsubcategory productsubcategory) {
		prodService.updateProductSubcategory(productsubcategory.getProductsubcategoryid(), productsubcategory);
	}
	
	@GetMapping
	public Iterable<Productsubcategory> getAll() {
		return prodService.findAllSubcategories();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		prodService.deleteProductSubcategory(id);
	}
}
