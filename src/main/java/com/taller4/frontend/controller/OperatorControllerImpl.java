package com.taller4.frontend.controller;

import java.time.*;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;
import com.taller4.backend.model.validation.*;
import com.taller4.backend.service.implementation.*;
import com.taller4.frontend.businessdelegate.*;

@Controller
public class OperatorControllerImpl {
	private BusinessDelegate bDelegate;
	
	@Autowired
	public OperatorControllerImpl(BusinessDelegate bDelegate) {
		this.bDelegate = bDelegate;
	}

	@GetMapping("/operator/")
	public String indexOperator(Model model) {
		return "operator/index";
	}
	
	@GetMapping("/specialoffer")
    public String indexSpecialOffer(Model model) {
		model.addAttribute("specialoffers", bDelegate.soFindAll());
        return "operator/specialoffers";
    }
	
	@GetMapping("/specialofferproduct")
    public String indexSpecialOfferProduct(Model model) {
		model.addAttribute("specialofferproducts", bDelegate.sopFindAll());
        return "operator/specialofferproducts";
    }
	
	@GetMapping("/salesorderdetail")
    public String indexSOD(Model model) {
		model.addAttribute("salesorderdetails",  bDelegate.sodFindAll());
        return "operator/salesorderdetails";
    }
	
	@GetMapping("/specialoffer/add")
	public String addSOGet(Model model) {
		model.addAttribute("specialoffer", new Specialoffer());
		return "operator/addSO";
	}
	
	@PostMapping("/specialoffer/add")
	public String addSOPost(@Validated({ addValidation.class }) @ModelAttribute("specialoffer") Specialoffer specialoffer, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialoffer", specialoffer);
				return "operator/addSO";
			}
			bDelegate.soSave(specialoffer);
		}
		return "redirect:/specialoffer/";
	}
	
	@GetMapping("/specialofferproduct/add")
	public String addSOPGet(Model model) {
		model.addAttribute("specialofferproduct", new Specialofferproduct());
		model.addAttribute("offers", bDelegate.soFindAll());
		model.addAttribute("products", bDelegate.prodFindAll());
		return "operator/addSOP";
	}
	
	@PostMapping("/specialofferproduct/add")
	public String addSOPPost(@Validated({ addValidation.class }) @ModelAttribute("specialofferproduct") Specialofferproduct specialofferproduct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialofferproduct", specialofferproduct);
				model.addAttribute("offers", bDelegate.soFindAll());
				model.addAttribute("products", bDelegate.prodFindAll());
				return "operator/addSOP";
			}
			bDelegate.sopSave(specialofferproduct);
		}
		return "redirect:/specialofferproduct/";
	}
	
	@GetMapping("/salesorderdetail/add")
	public String addSODGet(Model model) {
		model.addAttribute("specialofferproduct", new Specialofferproduct());
		model.addAttribute("offers", bDelegate.soFindAll());
		model.addAttribute("products", bDelegate.prodFindAll());
		return "operator/addSOD";
	}
	
	@PostMapping("/salesorderdetail/add")
	public String addSODPost(@Validated({ addValidation.class }) @ModelAttribute("salesorderdetail") Salesorderdetail salesorderdetail, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesorderdetail", salesorderdetail);
				model.addAttribute("offers", bDelegate.soFindAll());
				model.addAttribute("products", bDelegate.prodFindAll());
				return "operator/addSOD";
			}
			bDelegate.sodSave(salesorderdetail);
		}
		return "redirect:/salesorderdetail/";
	}
	
	@GetMapping("/specialoffer/edit/{id}")
	public String editSOGet(@PathVariable("id") Integer id, Model model) {
		Specialoffer so = bDelegate.soFindById(id);
		
		model.addAttribute("specialoffer", so);
		return "operator/editSO";
	}
	
	@PostMapping("/specialoffer/edit/{id}")
	public String editSOPost(@PathVariable("id") Integer id, @Validated(updateValidation.class) @ModelAttribute("specialoffer") Specialoffer specialoffer, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialoffer", bDelegate.soFindById(id));
				return "operator/editSO";
			}
			specialoffer.setSpecialofferid(id);
			bDelegate.soUpdate(specialoffer);
		}
		return "redirect:/specialoffer";
	}
	
	@GetMapping("/specialofferproduct/edit/{id}")
	public String editSOPGet(@PathVariable("id") SpecialofferproductPK id, Model model) {
		Specialofferproduct sop = bDelegate.sopFindById(id);
		
		model.addAttribute("specialofferproduct", sop);
		return "operator/editSOP";
	}
	
	@PostMapping("/specialofferproduct/edit/{id}")
	public String editSOPPost(@PathVariable("id") SpecialofferproductPK id, @Validated(updateValidation.class) @ModelAttribute("specialofferproduct") Specialofferproduct specialofferproduct, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("specialofferproduct", bDelegate.sopFindById(id));
				return "operator/editSOP";
			}
			specialofferproduct.setId(id);
			bDelegate.sopUpdate(specialofferproduct);
		}
		return "redirect:/specialofferproduct";
	}
	
	@GetMapping("/salesorderdetail/edit/{id}")
	public String editSODGet(@PathVariable("id") Integer id, Model model) {
		Salesorderdetail sod = bDelegate.sodFindById(id);
		
		model.addAttribute("salesorderdetail", sod);
		return "operator/editSOD";
	}
	
	@PostMapping("/salesorderdetail/edit/{id}")
	public String editSODPost(@PathVariable("id") Integer id, @Validated(updateValidation.class) @ModelAttribute("salesorderdetail") Salesorderdetail salesorderdetail, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("salesorderdetail", bDelegate.sodFindById(id));
				return "operator/editSOD";
			}
			salesorderdetail.setSalesOrderDetailId(id);
			bDelegate.sodUpdate(salesorderdetail);
		}
		return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/specialoffer/delete/{id}")
	public String deleteSO(@PathVariable("id") Integer id, Model model) {
		bDelegate.soDelete(id);
		return "redirect:/specialoffer";
	}
	
	@GetMapping("/specialofferproduct/delete/{id}")
	public String deleteSOP(@PathVariable("id") SpecialofferproductPK id, Model model) {
		bDelegate.sopDelete(id);
		return "redirect:/specialofferproduct";
	}
	
	@GetMapping("/salesorderdetail/delete/{id}")
	public String deleteSOD(@PathVariable("id") Integer id, Model model) {
		bDelegate.sodDelete(id);
		return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/specialoffer/get/{startdate}")
	public String querySOStartDateGet(@PathVariable("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate, Model model) {
		return "operator/soQuery";
	}
	
	@PostMapping("/specialoffer/get/{startdate}")
	public String querySOStartDatePost(@PathVariable("startdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("specialoffers", bDelegate.soFindStartDate(Timestamp.valueOf(startdate.atStartOfDay())));
			return "operator/soQuery";
		}else
			return "redirect:/specialoffer";
		
	}
	
	@GetMapping("/specialoffer/get/{enddate}")
	public String querySOEndDateGet(@PathVariable("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate, Model model) {
		return "operator/soQuery";
	}
	
	@PostMapping("/specialoffer/get/{enddate}")
	public String querySOEndDatePost(@PathVariable("enddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("specialoffers", bDelegate.soFindEndDate(Timestamp.valueOf(enddate.atStartOfDay())));
			return "operator/soQuery";
		}else
			return "redirect:/specialoffer";
	}
	
	@GetMapping("/salesorderdetail/get/{productid}")
	public String querySODProductIdGet(@PathVariable("productid") Integer productid, Model model) {
		return "operator/sodQuery";
	}
	
	@PostMapping("/salesorderdetail/get/{productid}")
	public String querySODProductIdPost(@PathVariable("productid") Integer productid, Model model) {
		if(productid!=null) {
			model.addAttribute("salesorderdetails", bDelegate.sodFindByProductId(productid));
			return "operator/sodQuery";
		}else
			return "redirect:/salesorderdetail";
	}
	
	@GetMapping("/salesorderdetail/get/SOP")
	public String querySODMoreSOPGet(Model model) {
		return "operator/sodSOPQuery";
	}
	
	@PostMapping("/salesorderdetail/get/SOP")
	public String querySODMoreSOPPost(Model model) {
		model.addAttribute("sales", bDelegate.sodFindByMoreThanOneSOP());
		return "operator/sodSOPQuery";
	}
}
