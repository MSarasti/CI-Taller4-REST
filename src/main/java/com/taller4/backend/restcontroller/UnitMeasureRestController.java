package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/api/unitmeasure")
public class UnitMeasureRestController {
	
	private ProductService prodService;

	@Autowired
	public UnitMeasureRestController(ProductService prodService) {
		this.prodService = prodService;
	}
	
	@PostMapping
	public Unitmeasure add(@RequestBody Unitmeasure unitmeasure) {
		return prodService.saveUnitmeasure(unitmeasure);
	}
	
	@GetMapping
	public Iterable<Unitmeasure> getAll() {
		return prodService.findAllUnits();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		prodService.deleteUnitmeasure(id);
	}
}
