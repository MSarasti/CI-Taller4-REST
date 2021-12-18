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
import com.taller4.frontend.businessdelegate.*;

@Controller
public class AdminControllerImpl {
	@Autowired
	private BusinessDelegate bDelegate;
	/*@Autowired
	private BusinessDelegateURL bDelegate;*/
	
	//@Autowired
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
			}else {
				workorder.setProduct(bDelegate.prodFindById(workorder.getPId()));
				bDelegate.woSave(workorder);
			}
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
				workorderrouting.setWorkorder(bDelegate.woFindById(workorderrouting.getWId()));
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
				workorder.setProduct(bDelegate.prodFindById(workorder.getPId()));
				workorder.setWorkorderid(id);
				bDelegate.woUpdate(workorder);
			}
		}
		return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/edit/{id1}_{id2}_{id3}")
	public String editWorkOrderRoutingGet(@PathVariable("id1") Integer wId, @PathVariable("id2") Integer pId, @PathVariable("id3") Integer op, Model model) {
		WorkorderroutingPK pk = new WorkorderroutingPK();
		pk.setWorkorderid(wId);
		pk.setProductid(pId);
		pk.setOperationsequence(op);
		model.addAttribute("woroute", bDelegate.worFindById(pk));
		model.addAttribute("worders", bDelegate.woFindAll());
		return "admin/editWorkOrderRouting";
	}
	
	@PostMapping("/workorderrouting/edit/{id1}_{id2}_{id3}")
	public String editWorkOrderRoutingPost(@PathVariable("id1") Integer wId, @PathVariable("id2") Integer pId, @PathVariable("id3") Integer op, @ModelAttribute("woroute") Workorderrouting workorderrouting, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if(!action.equals("Cancel")) {
			WorkorderroutingPK pk = new WorkorderroutingPK();
			pk.setWorkorderid(wId);
			pk.setProductid(pId);
			pk.setOperationsequence(op);
			if(bindingResult.hasErrors()) {
				model.addAttribute("woroute", bDelegate.worFindById(pk));
				model.addAttribute("worders", bDelegate.woFindAll());
				return "admin/editWorkOrderRouting";
			}else {
				workorderrouting.setWorkorder(bDelegate.woFindById(workorderrouting.getWId()));
				workorderrouting.setId(pk);
				bDelegate.worUpdate(workorderrouting);
			}
		}
		return "redirect:/workorderrouting";
	}
	
	@GetMapping("/product/del/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		bDelegate.prodDelete(id);
		return "redirect:/product";
	}
	
	@GetMapping("/workorder/del/{id}")
	public String deleteWorkOrder(@PathVariable("id") Integer id, Model model) {
		bDelegate.woDelete(id);
		return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/del/{id1}_{id2}_{id3}")
	public String deleteWorkOrder(@PathVariable("id1") Integer wId, @PathVariable("id2") Integer pId, @PathVariable("id3") Integer op,  Model model) {
		WorkorderroutingPK pk = new WorkorderroutingPK();
		pk.setWorkorderid(wId);
		pk.setProductid(pId);
		pk.setOperationsequence(op);
		bDelegate.worDelete(pk);
		return "redirect:/workorderrouting";
	}
	
	@GetMapping("/product/query/productnumber/{pnum}")
	public String queryProductNumberGet(@PathVariable("pnum") String productnumber, Model model) {
		return "admin/prodBaseQuery";
	}
	
	@PostMapping("/product/query/productnumber/{pnum}")
	public String queryProductNumberPost(@PathVariable("pnum") String productnumber, Model model) {
		if(!productnumber.isBlank()) {
			ArrayList<Product> list = new ArrayList<>();
			list.add(bDelegate.prodFindByProductNumber(productnumber));
			Iterable<Product> iter =  list;
			model.addAttribute("products", iter);
			return "admin/prodBaseQuery";
		}else
			return "redirect:/product";
	}
	
	@GetMapping("/product/query/style/{style}")
	public String queryStyleGet(@PathVariable("style") String style, Model model) {
		return "admin/prodBaseQuery";
	}
	
	@PostMapping("/product/query/style/{style}")
	public String queryStylePost(@PathVariable("style") String style, Model model) {
		if(!style.isBlank()) {
			model.addAttribute("products", bDelegate.prodFindByStyle(style));
			return "admin/prodBaseQuery";
		}else
			return "redirect:/product";
	}
	
	@GetMapping("/product/query/range/{sellstartdate}_{sellenddate}")
	public String queryDateRangeGet(@PathVariable("sellstartdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellstartdate, @PathVariable("sellenddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellenddate, Model model) {
		return "admin/prodDateRangeQuery";
	}
	
	@PostMapping("/product/query/range/{sellstartdate}_{sellenddate}")
	public String queryDateRangePost(@PathVariable("sellstartdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellstartdate, @PathVariable("sellenddate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate sellenddate, Model model, @RequestParam(value = "action", required = true) String action) {
		if(sellstartdate!=null && sellenddate!=null && sellstartdate.isBefore(sellenddate)) {
			model.addAttribute("products", bDelegate.prodFindDateRange(sellstartdate, sellenddate));
			return "admin/prodDateRangeQuery";
		}else
			return "redirect:/product";
	}
	
	@GetMapping("/workorder/query/startdate/{startdate}")
	public String queryWOStartDateGet(@PathVariable("startdate") LocalDate startdate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorder/query/startdate/{startdate}")
	public String queryWOStartDatePost(@PathVariable("startdate") LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("workorders", bDelegate.woFindByStartDate(startdate));
			return "admin/woQuery";
		}else
			return "redirect:/workorder";
	}
	
	@GetMapping("/workorder/query/enddate/{enddate}")
	public String queryWOEndDateGet(@PathVariable("enddate") LocalDate enddate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorder/query/enddate/{enddate}")
	public String queryWOEndDatePost(@PathVariable("enddate") LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("workorders", bDelegate.woFindByEndDate(enddate));
			return "admin/worQuery";
		}else
			return "redirect:/workorder";
	}
	
	@GetMapping("/workorderrouting/query/startdate/{startdate}")
	public String queryWORStartDateGet(@PathVariable("startdate") LocalDate startdate, Model model) {
		return "admin/woQuery";
	}
	
	@PostMapping("/workorderrouting/query/startdate/{startdate}")
	public String queryWORStartDatePost(@PathVariable("startdate") LocalDate startdate, Model model) {
		if(startdate!=null) {
			model.addAttribute("workorderroutings", bDelegate.worFindByStartDate(startdate));
			return "admin/worQuery";
		}else
			return "redirect:/workorderrouting";
	}
	
	@GetMapping("/workorderrouting/query/enddate/{enddate}")
	public String queryWOREndDateGet(@PathVariable("enddate") LocalDate enddate, Model model) {
		return "admin/worQuery";
	}
	
	@PostMapping("/workorderrouting/query/enddate/{enddate}")
	public String queryWOREndDatePost(@PathVariable("enddate") LocalDate enddate, Model model) {
		if(enddate!=null) {
			model.addAttribute("workorderroutings", bDelegate.worFindByEndDate(enddate));
			return "admin/worQuery";
		}else
			return "redirect:/workorderrouting";
	}
}
