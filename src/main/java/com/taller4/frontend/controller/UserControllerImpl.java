package com.taller4.frontend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taller4.backend.model.person.*;
import com.taller4.backend.model.person.UserApp.addValidator;
import com.taller4.backend.model.person.UserApp.updateValidator;
import com.taller4.backend.service.implementation.UserServiceImpl;
import com.taller4.frontend.businessdelegate.*;

@Controller
public class UserControllerImpl {
	/*@Autowired
	private BusinessDelegate bDelegate;*/
	@Autowired
	private BusinessDelegateURL bDelegate;
	
	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", bDelegate.userFindAll());
		return "users/index";
	}

	@GetMapping("/index")
	public String index() {
		return "/index";
	}

	@GetMapping("/login")
	public String loginPrincipal() {
		return "/login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/access-denied";
	}

	@GetMapping("/users/add")
	public String addUser(Model model) {
		model.addAttribute("user", new UserApp());
		model.addAttribute("types", bDelegate.userFindAllTypes());
		return "users/add-user";
	}
	
	@PostMapping("/users/add")
	public String saveUser(@Validated(addValidator.class) @ModelAttribute("user") UserApp user, BindingResult br, Model model, 
			@RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(br.hasErrors()) {
				model.addAttribute("types", bDelegate.userFindAllTypes());
				return "users/add-user";
			}
			bDelegate.userSave(user);
		}
		return "redirect:/users/";
	}
	
	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		bDelegate.userDelete(id);
		model.addAttribute("users", bDelegate.userFindAll());
		return "users/index";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", bDelegate.userFindById(id));
		model.addAttribute("types", bDelegate.userFindAllTypes());
		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, @Validated(updateValidator.class) @ModelAttribute("user") UserApp user, BindingResult br, Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(br.hasErrors()) {
				model.addAttribute("types", bDelegate.userFindAllTypes());
				return "users/update-user";
			}
			String pass = user.getPassword();
			String re = user.getRepeatPassword();
			if((pass.length()>=8 && pass.equals(re))) {
				bDelegate.userUpdate(user);
			}else {
				user.setPassword(bDelegate.userFindById(id).getPassword());
				bDelegate.userUpdate(user);
			}
			model.addAttribute("users", bDelegate.userFindAll());
		}
		return "redirect:/users/";
	}
}
