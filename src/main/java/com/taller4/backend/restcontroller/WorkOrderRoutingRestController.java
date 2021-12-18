package com.taller4.backend.restcontroller;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/api/workorderrouting")
public class WorkOrderRoutingRestController {
	
	private WorkOrderRoutingService worService;

	@Autowired
	public WorkOrderRoutingRestController(WorkOrderRoutingService worService) {
		this.worService = worService;
	}
	
	@PostMapping
	public Workorderrouting add(@RequestBody Workorderrouting workorderrouting) {
		return worService.saveWorkOrderRouting(workorderrouting);
	}
	
	@PutMapping
	public void update(@RequestBody Workorderrouting workorderrouting) {
		worService.updateWorkOrderRouting(workorderrouting.getId(), workorderrouting);
	}
	
	@GetMapping
	public Iterable<Workorderrouting> getAll() {
		return worService.findAll();
	}
	
	@GetMapping("/{id}")
	public Workorderrouting getById(@PathVariable("id") WorkorderroutingPK id) {
		return worService.findById(id);
	}
	
	@GetMapping("/get/startdate")
	public Iterable<Workorderrouting> getByStartDate(@RequestParam("startdate") LocalDate startdate) {
		return worService.findByStartDate(startdate);
	}
	
	@GetMapping("/get/enddate")
	public Iterable<Workorderrouting> getByEndDate(@RequestParam("enddate") LocalDate enddate) {
		return worService.findByEndDate(enddate);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") WorkorderroutingPK id) {
		worService.deleteWorkOrderRouting(id);
	}
}
