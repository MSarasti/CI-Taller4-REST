package com.taller4.frontend.controller;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.validation.*;
import com.taller4.backend.service.implementation.*;
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
	
	@GetMapping("/workorder")
    public String indexWorkOrder(Model model) {
		model.addAttribute("workorders", bDelegate.woFindAll());
        return "admin/workorders";
    }
	
	@GetMapping("/workorderrouting")
    public String indexWorkOrderRouting(Model model) {
		model.addAttribute("workorderroutings", bDelegate.worFindAll());
        return "admin/workorderroutings";
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
	public String addProductPost(@ModelAttribute("prod") Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
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
	
	@GetMapping("/workorder/add")
	public String addWorkOrderGet(Model model) {
		model.addAttribute("worder", new Workorder());
		model.addAttribute("prods", bDelegate.prodFindAll());
		return "admin/addWorkOrder";
	}
	
	@PostMapping("/workorder/add")
	public String addWorkOrderPost(@ModelAttribute("worder") Workorder workorder, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("worder", workorder);
				model.addAttribute("prods", bDelegate.prodFindAll());
				return "admin/addWorkOrder";
			}else 
				bDelegate.woSave(workorder);
		}
		return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/add")
	public String addWorkOrderRoutingGet(Model model) {
		model.addAttribute("woroute", new Workorderrouting());
		model.addAttribute("worders", bDelegate.woFindAll());
		return "admin/addWorkOrderRouting";
	}
	
	@PostMapping("/workorderrouting/add")
	public String addWorkOrderRoutingPost(@ModelAttribute("woroute") Workorderrouting workorderrouting, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("woroute", workorderrouting);
				model.addAttribute("worders", bDelegate.woFindAll());
				return "admin/addWorkOrderRouting";
			}else {
				WorkorderroutingPK worPK = new WorkorderroutingPK();
				worPK.setWorkorderid(workorderrouting.getWorkorder().getWorkorderid());
				worPK.setProductid(workorderrouting.getWorkorder().getProduct().getProductid());
				ArrayList<Workorderrouting> list = new ArrayList<>();
				bDelegate.worFindAll().iterator().forEachRemaining(list::add);
				worPK.setOperationsequence(list.size()+1);
				workorderrouting.setId(worPK);
				bDelegate.worSave(workorderrouting);
			}
		}
		return "redirect:/workorderrouting";
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
	public String editProductPost(@PathVariable("id") Integer id, @Validated(updateValidation.class) @ModelAttribute("prod") Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
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
	
	@GetMapping("/workorder/edit/{id}")
	public String editWorkOrderGet(@PathVariable("id") Integer id, Model model) {
		Workorder workorder = bDelegate.woFindById(id);
		model.addAttribute("worder", workorder);
		model.addAttribute("prods", bDelegate.prodFindAll());
		return "admin/editWorkOrder";
	}
	
	@PostMapping("/workorder/edit/{id}")
	public String editWorkOrderPost(@PathVariable("id") Integer id, @Validated(updateValidation.class) @ModelAttribute("worder") Workorder workorder, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("worder",  bDelegate.woFindById(id));
				model.addAttribute("prods", bDelegate.prodFindAll());
				return "admin/editWorkOrder";
			}else {
				workorder.setWorkorderid(id);
				bDelegate.woUpdate(workorder);
			}
		}
		return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/edit/{id}")
	public String editWorkOrderRoutingGet(@PathVariable("id") WorkorderroutingPK id, Model model) {
		model.addAttribute("woroute", bDelegate.worFindById(id));
		model.addAttribute("worders", bDelegate.woFindAll());
		return "admin/editWorkOrderRouting";
	}
	
	@PostMapping("/workorderrouting/edit/{id}")
	public String editWorkOrderRoutingPost(@PathVariable("id") WorkorderroutingPK id, @ModelAttribute("woroute") Workorderrouting workorderrouting, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("woroute", bDelegate.worFindById(id));
				model.addAttribute("worders", bDelegate.woFindAll());
				return "admin/editWorkOrderRouting";
			}else {
				workorderrouting.setId(id);
				bDelegate.worUpdate(workorderrouting);
			}
		}
		return "redirect:/workorderrouting";
	}
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		bDelegate.prodDelete(id);
		return "redirect:/product";
	}
	
	@GetMapping("/workorder/delete/{id}")
	public String deleteWorkOrder(@PathVariable("id") Integer id, Model model) {
		bDelegate.woDelete(id);
		return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/delete/{id}")
	public String deleteWorkOrder(@PathVariable("id") WorkorderroutingPK id, Model model) {
		bDelegate.worDelete(id);
		return "redirect:/workorderrouting";
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
	
	@GetMapping("/workorder/get/startdate/")
	public String queryWOStartDateGet(@RequestParam("startdate") LocalDate startdate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorder/get/startdate")
	public String queryWOStartDatePost(@RequestParam("startdate") LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("products", bDelegate.woFindByStartDate(Timestamp.valueOf(startdate.atStartOfDay())));
			return "admin/woQuery";
		}else
			return "redirect:/workorder";
	}
	
	@GetMapping("/workorder/get/enddate/")
	public String queryWOEndDateGet(@RequestParam("enddate") LocalDate enddate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorder/get/enddate")
	public String queryWOEndDatePost(@RequestParam("enddate") LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("workorders", bDelegate.woFindByEndDate(Timestamp.valueOf(enddate.atStartOfDay())));
			return "admin/worQuery";
		}else
			return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/get/startdate/")
	public String queryWORStartDateGet(@RequestParam("startdate") LocalDate startdate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorderrouting/get/startdate")
	public String queryWORStartDatePost(@RequestParam("startdate") LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("workorderroutings", bDelegate.worFindByStartDate(Timestamp.valueOf(startdate.atStartOfDay())));
			return "admin/worQuery";
		}else
			return "redirect:/workorderrouting";
	}
	
	@GetMapping("/workorderrouting/get/enddate/")
	public String queryWOREndDateGet(@RequestParam("enddate") LocalDate enddate, Model model) {
		return "admin/worQuery";
	}
	
	@PostMapping("/workorderrouting/get/enddate")
	public String queryWOREndDatePost(@RequestParam("enddate") LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("workorderroutings", bDelegate.worFindByEndDate(Timestamp.valueOf(enddate.atStartOfDay())));
			return "admin/worQuery";
		}else
			return "redirect:/workorderrouting";
	}
}
