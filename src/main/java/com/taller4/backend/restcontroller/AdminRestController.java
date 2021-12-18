package com.taller4.backend.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	@GetMapping
	public void getInfo() {
		
	}
}
