package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/productcategory")
public class ProductCategoryRestController {
	
	private ProductService prodService;

	@Autowired
	public ProductCategoryRestController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@PostMapping
	public Productcategory add(@RequestBody Productcategory productcategory) {
		return prodService.saveProductCategory(productcategory);
	}
	
	@PutMapping
	public void update(@RequestBody Productcategory productcategory) {
		prodService.updateProductCategory(productcategory.getProductcategoryid(), productcategory);
	}
	
	@GetMapping
	public Iterable<Productcategory> getAll() {
		return prodService.findAllCategories();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		prodService.deleteProductCategory(id);
	}
}
