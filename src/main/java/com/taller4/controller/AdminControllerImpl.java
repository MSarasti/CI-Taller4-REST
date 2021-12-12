package com.taller4.controller;

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

import com.taller4.model.prod.*;
import com.taller4.model.validation.*;
import com.taller4.service.implementation.ProductServiceImpl;

@Controller
public class AdminControllerImpl {
	
	ProductServiceImpl pService;
	
	@Autowired
	public AdminControllerImpl(ProductServiceImpl ps) {
		pService = ps;
	}
	
	@GetMapping("/admin/")
	public String indexAdmin(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/product")
    public String products(Model model) {
		model.addAttribute("products", pService.findAllProducts());
        return "admin/products";
    }
	
	@GetMapping("/productcategory/add")
	public String addPCategory(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "admin/addPC";
	}
	
	@PostMapping("/productcategory/add")
	public String savePCategory(@Validated({ addValidation.class }) @ModelAttribute("productcategory") Productcategory productcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productcategory", productcategory);
				return "admin/addPC";
			}
			pService.saveProductCategory(productcategory);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/productsubcategory/add")
	public String addPSubcategory(Model model) {
		model.addAttribute("productsubcategory", new Productsubcategory());
		return "admin/addPS";
	}
	
	@PostMapping("/productsubcategory/add")
	public String savePSubcategory(@Validated({ addValidation.class }) @ModelAttribute("productsubcategory") Productsubcategory productsubcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productsubcategory", productsubcategory);
				return "admin/addPS";
			}
			pService.saveProductSubcategory(productsubcategory);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/unitmeasure/add")
	public String addUnitMeasure(Model model) {
		model.addAttribute("unitmeasure", new Unitmeasure());
		return "admin/addUM";
	}
	
	@PostMapping("/unitmeasure/add")
	public String saveUnitMeasure(@Validated({ addValidation.class }) @ModelAttribute("unitmeasure") Unitmeasure unitmeasure, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("unitmeasure", unitmeasure);
				return "admin/addUM";
			}
			pService.saveUnitmeasure(unitmeasure);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/product/add")
	public String addProduct(Model model) {
		model.addAttribute("prod", new Product());
		model.addAttribute("cats", pService.findAllCategories());
		model.addAttribute("subs", pService.findAllSubcategories());
		model.addAttribute("units", pService.findAllUnits());
		return "admin/addProduct";
	}
	
	@PostMapping("/product/add")
	public String saveProduct(@Validated({ addValidation.class }) @ModelAttribute("prod") Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				System.out.println("Why - "+bindingResult.getAllErrors().toString());
				model.addAttribute("prod", product);
				model.addAttribute("cats", pService.findAllCategories());
				model.addAttribute("subs", pService.findAllSubcategories());
				model.addAttribute("units", pService.findAllUnits());
				return "admin/addProduct";
			}
			pService.saveProduct(product, product.getPcatId(), product.getPsubId(), product.getUm1Id(), product.getUm2Id());
			
		}
		return "redirect:/product";
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model) {
		Product product = pService.findById(id);
		model.addAttribute("prod", product);
		model.addAttribute("cats", pService.findAllCategories());
		model.addAttribute("subs", pService.findAllSubcategories());
		model.addAttribute("units", pService.findAllUnits());
		return "admin/editProduct";
	}
	
	@PostMapping("/product/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, @Validated(updateValidation.class) Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("prod", pService.findById(id));
				model.addAttribute("cats", pService.findAllCategories());
				model.addAttribute("subs", pService.findAllSubcategories());
				model.addAttribute("units", pService.findAllUnits());
				return "admin/editProduct";
			}
			product.setProductid(id);
			pService.updateProduct(id, product);
		}
		return "redirect:/product";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		pService.deleteProduct(id);
		return "redirect:/product";
	}
}
