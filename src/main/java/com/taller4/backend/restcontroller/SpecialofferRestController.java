package com.taller4.backend.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.sales.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/specialoffer")
public class SpecialofferRestController {
	
	private SpecialofferService soService;

	@Autowired
	public SpecialofferRestController(SpecialofferService soService) {
		this.soService = soService;
	}
	
	@PostMapping
	public Specialoffer add(@RequestBody Specialoffer specialoffer) {
		return soService.saveSpecialOffer(specialoffer);
	}
	
	@PutMapping
	public void update(@RequestBody Specialoffer specialoffer) {
		soService.updateSpecialOffer(specialoffer.getSpecialofferid(), specialoffer);
	}
	
	@GetMapping
	public Iterable<Specialoffer> getAll() {
		return soService.findAll();
	}
	
	@GetMapping("/{id}")
	public Specialoffer getById(@PathVariable("id") Integer id) {
		return soService.findById(id);
	}
	
	@GetMapping("/get/{startdate}")
	public Iterable<Specialoffer> getByStartDate(@PathVariable("startdate") Timestamp startdate) {
		return soService.findByStartDate(startdate);
	}
	
	@GetMapping("/get/{enddate}")
	public Iterable<Specialoffer> getByEndDate(@PathVariable("enddate") Timestamp enddate) {
		return soService.findByEndDate(enddate);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		soService.deleteSpecialOffer(id);
	}
}
