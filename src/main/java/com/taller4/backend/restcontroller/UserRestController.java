package com.taller4.backend.restcontroller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.person.*;
import com.taller4.backend.service.interfaces.*;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public void add(@RequestBody UserApp user) {
		userService.save(user);
	}
	
	@PutMapping
	public void update(@RequestBody UserApp user) {
		userService.update(user.getId(), user);
	}
	
	@GetMapping
	public Iterable<UserApp> getAll() {
		return userService.findAll();
	}
	
	@GetMapping
	public Iterable<UserApp> getAllAdmins() {
		return userService.findAllAdmins();
	}
	
	@GetMapping
	public Iterable<UserApp> getAllOperators() {
		return userService.findAllOperators();
	}
	
	@GetMapping("/{id}")
	public UserApp getById(@PathVariable("id") long id) {
		Optional<UserApp> oUser = userService.findById(id);
		if(oUser.isPresent())
			return oUser.get();
		else
			return null;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {
		userService.delete(getById(id));
	}
}
