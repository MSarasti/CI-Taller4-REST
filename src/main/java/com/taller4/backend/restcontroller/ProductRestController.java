package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	
	private ProductService prodService;

	@Autowired
	public ProductRestController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@PostMapping
	public Product add(@RequestBody Product product) {
		return prodService.saveProduct(product, product.getPcatId(), product.getPsubId(), product.getUm1Id(), product.getUm2Id());
	}
	
	@PutMapping
	public void update(@RequestBody Product product) {
		prodService.updateProduct(product.getProductid(), product);
	}
	
	@GetMapping
	public Iterable<Product> getAll() {
		return prodService.findAllProducts();
	}
}
