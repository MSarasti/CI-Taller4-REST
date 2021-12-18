package com.taller4.backend.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.sales.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/api/specialofferproduct")
public class SpecialofferproductRestController {
	
	private SpecialofferproductService sopService;

	@Autowired
	public SpecialofferproductRestController(SpecialofferproductService sopService) {
		this.sopService = sopService;
	}
	
	@PostMapping
	public Specialofferproduct add(@RequestBody Specialofferproduct specialofferproduct) {
		return sopService.saveSpecialOfferProduct(specialofferproduct, specialofferproduct.getpId(), specialofferproduct.getsId());
	}
	
	@PutMapping
	public void update(@RequestBody Specialofferproduct specialofferproduct) {
		sopService.updateSpecialOfferProduct(specialofferproduct.getId(), specialofferproduct);
	}
	
	@GetMapping
	public Iterable<Specialofferproduct> getAll() {
		return sopService.findAll();
	}
	
	@GetMapping("/{id}")
	public Specialofferproduct getById(@PathVariable("id") SpecialofferproductPK id) {
		return sopService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") SpecialofferproductPK id) {
		sopService.deleteSpecialOfferProduct(id);
	}
}
