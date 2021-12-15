package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.sales.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/salesorderdetail")
public class SalesorderdetailRestController {
	
	private SalesorderdetailService sodService;

	@Autowired
	public SalesorderdetailRestController(SalesorderdetailService sodService) {
		this.sodService = sodService;
	}
	
	@PostMapping
	public Salesorderdetail add(@RequestBody Salesorderdetail salesorderdetail) {
		return sodService.saveSalesOrderDetail(salesorderdetail, salesorderdetail.getpId(), salesorderdetail.getsoId());
	}
	
	@PutMapping
	public void update(@RequestBody Salesorderdetail salesorderdetail) {
		sodService.updateSalesOrderDetail(salesorderdetail.getSalesOrderDetailId(), salesorderdetail);
	}
	
	@GetMapping
	public Iterable<Salesorderdetail> getAll() {
		return sodService.findAll();
	}
	
	@GetMapping("/{id}")
	public Salesorderdetail getById(@PathVariable("id") Integer id) {
		return sodService.findById(id);
	}
	
	@GetMapping("/get/{productid}")
	public Iterable<Salesorderdetail> getByProductId(@PathVariable("productid") Integer productid) {
		return sodService.findByProductId(productid);
	}
	
	@GetMapping("/get/SOP")
	public Iterable<Object[]> getByMoreThanOneSOP() {
		return sodService.findOrderDetailByProductWithMoreThanOneSOP();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		sodService.deleteSalesOrderDetail(id);
	}
}
