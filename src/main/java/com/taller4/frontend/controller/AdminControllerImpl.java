package com.taller4.frontend.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.validation.*;
import com.taller4.backend.service.implementation.ProductServiceImpl;
import com.taller4.frontend.businessdelegate.BusinessDelegate;

@Controller
public class AdminControllerImpl {
	private BusinessDelegate bDelegate;
	
	@Autowired
	public AdminControllerImpl(BusinessDelegate bDelegate) {
		this.bDelegate = bDelegate;
	}
	
	@GetMapping("/admin/")
	public String indexAdmin(Model model) {
		return "admin/index";
	}
	
	@GetMapping("/product")
    public String products(Model model) {
		model.addAttribute("products", bDelegate.prodFindAll());
        return "admin/products";
    }
	
	@GetMapping("/productcategory/add")
	public String addPCategoryGet(Model model) {
		model.addAttribute("productcategory", new Productcategory());
		return "admin/addPC";
	}
	
	@PostMapping("/productcategory/add")
	public String addPCategoryPost(@Validated({ addValidation.class }) @ModelAttribute("productcategory") Productcategory productcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productcategory", productcategory);
				return "admin/addPC";
			}else
				bDelegate.pCatSave(productcategory);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/productsubcategory/add")
	public String addPSubcategoryGet(Model model) {
		model.addAttribute("productsubcategory", new Productsubcategory());
		return "admin/addPS";
	}
	
	@PostMapping("/productsubcategory/add")
	public String addPSubcategoryPost(@Validated({ addValidation.class }) @ModelAttribute("productsubcategory") Productsubcategory productsubcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("productsubcategory", productsubcategory);
				return "admin/addPS";
			}else
				bDelegate.pSUbSave(productsubcategory);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/unitmeasure/add")
	public String addUnitMeasureGet(Model model) {
		model.addAttribute("unitmeasure", new Unitmeasure());
		return "admin/addUM";
	}
	
	@PostMapping("/unitmeasure/add")
	public String addUnitMeasurePost(@Validated({ addValidation.class }) @ModelAttribute("unitmeasure") Unitmeasure unitmeasure, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("unitmeasure", unitmeasure);
				return "admin/addUM";
			}else
				bDelegate.umSave(unitmeasure);
		}
		return "redirect:/admin/";
	}
	
	@GetMapping("/product/add")
	public String addProductGet(Model model) {
		model.addAttribute("prod", new Product());
		model.addAttribute("cats", bDelegate.pCatFindAll());
		model.addAttribute("subs", bDelegate.pSubFindAll());
		model.addAttribute("units", bDelegate.umFindAll());
		return "admin/addProduct";
	}
	
	@PostMapping("/product/add")
	public String addProductPost(@Validated({ addValidation.class }) @ModelAttribute("prod") Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				//System.out.println("Why - "+bindingResult.getAllErrors().toString());
				model.addAttribute("prod", product);
				model.addAttribute("cats", bDelegate.pCatFindAll());
				model.addAttribute("subs", bDelegate.pSubFindAll());
				model.addAttribute("units", bDelegate.umFindAll());
				return "admin/addProduct";
			}else 
				bDelegate.prodSave(product);
		}
		return "redirect:/product";
	}
	
	@GetMapping("/product/edit/{id}")
	public String editProductGet(@PathVariable("id") Integer id, Model model) {
		Product product = bDelegate.prodFindById(id);
		model.addAttribute("prod", product);
		model.addAttribute("cats", bDelegate.pCatFindAll());
		model.addAttribute("subs", bDelegate.pSubFindAll());
		model.addAttribute("units", bDelegate.umFindAll());
		return "admin/editProduct";
	}
	
	@PostMapping("/product/edit/{id}")
	public String editProductPost(@PathVariable("id") Integer id, @Validated(updateValidation.class) Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("prod",  bDelegate.prodFindById(id));
				model.addAttribute("cats", bDelegate.pCatFindAll());
				model.addAttribute("subs", bDelegate.pSubFindAll());
				model.addAttribute("units", bDelegate.umFindAll());
				return "admin/editProduct";
			}else {
				product.setProductid(id);
				bDelegate.prodUpdate(product);
			}
		}
		return "redirect:/product";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		bDelegate.prodDelete(id);
		return "redirect:/product";
	}
	
	@GetMapping("/product/get/productnumber/")
	public String queryProductNumberGet(@RequestParam("productnumber") String productnumber, Model model) {
		return "admin/prodBaseQuery";
	}
	
	@PostMapping("/product/get/productnumber")
	public String queryProductNumberPost(@RequestParam("productnumber") String productnumber, Model model) {
		if(!productnumber.isBlank()) {
			ArrayList<Product> list = new ArrayList<>();
			list.add(bDelegate.prodFindByProductNumber(productnumber));
			Iterable<Product> iter =  list;
			model.addAttribute("products", iter);
			return "admin/prodBaseQuery";
		}else
			return "redirect:/product";
	}
	
	@GetMapping("/product/get/style/")
	public String queryStyleGet(@RequestParam("style") String style, Model model) {
		return "admin/prodBaseQuery";
	}
	
	@PostMapping("/product/get/style")
	public String queryStylePost(@RequestParam("style") String style, Model model) {
		if(!style.isBlank()) {
			model.addAttribute("products", bDelegate.prodFindByStyle(style));
			return "admin/prodBaseQuery";
		}else
			return "redirect:/product";
	}
	
	@GetMapping("/product/sellstartdate/to/sellenddate")
	public String queryDateRangeGet(@RequestParam("sellstartdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellstartdate, @RequestParam("sellenddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellenddate, Model model) {
		return "admin/prodDateRangeQuery";
	}
	
	@PostMapping("/product/sellstartdate/to/sellenddate")
	public String queryDateRangePost(@RequestParam("sellstartdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellstartdate, @RequestParam("sellenddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellenddate, Model model, @RequestParam(value = "action", required = true) String action) {
		if(sellstartdate!=null && sellenddate!=null && sellstartdate.isBefore(sellenddate)) {
			model.addAttribute("products", bDelegate.prodFindDateRange(sellstartdate, sellenddate));
			return "admin/prodDateRangeQuery";
		}else
			return "redirect:/product";
	}
}
