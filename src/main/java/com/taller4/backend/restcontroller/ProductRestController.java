package com.taller4.backend.restcontroller;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/api/product")
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
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable("id") Integer id) {
		return prodService.findById(id);
	}
	
	@GetMapping("/get/productnumber")
	public Product getByProductNumber(@RequestParam("productnumber") String productnumber) {
		return prodService.findByProductNumber(productnumber);
	}
	
	@GetMapping("/get/style")
	public Iterable<Product> getByStyle(@RequestParam("style") String style) {
		return prodService.findByStyle(style);
	}
	
	@GetMapping("/sellstartdate/to/sellenddate")
	public Iterable<?> getByDateRange(@RequestParam("sellstartdate") LocalDate sellstartdate, @RequestParam("sellenddate") LocalDate sellenddate) {
		return prodService.findByDateRange(sellstartdate, sellenddate);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		prodService.deleteProduct(id);
	}
}
