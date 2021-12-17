package com.taller4.backend.restcontroller;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/workorder")
public class WorkOrderRestController {
	
	private WorkOrderService woService;

	@Autowired
	public WorkOrderRestController(WorkOrderService woService) {
		this.woService = woService;
	}
	
	@PostMapping
	public Workorder add(@RequestBody Workorder workorder) {
		return woService.saveWorkOrder(workorder);
	}
	
	@PutMapping
	public void update(@RequestBody Workorder workorder) {
		woService.updateWorkOrder(workorder.getWorkorderid(), workorder);
	}
	
	@GetMapping
	public Iterable<Workorder> getAll() {
		return woService.findAll();
	}
	
	@GetMapping("/{id}")
	public Workorder getById(@PathVariable("id") Integer id) {
		return woService.findById(id);
	}
	
	@GetMapping("/get/startdate")
	public Iterable<Workorder> getByStartDate(@RequestParam("startdate") Timestamp startdate) {
		return woService.findByStartDate(startdate);
	}
	
	@GetMapping("/get/enddate")
	public Iterable<Workorder> getByEndDate(@RequestParam("enddate") Timestamp enddate) {
		return woService.findByEndDate(enddate);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		woService.deleteWorkOrder(id);
	}
}
