package com.taller4.frontend.businessdelegate;

import java.sql.Timestamp;
import java.time.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taller4.backend.model.person.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;
import com.taller4.backend.restcontroller.*;

@Component
public class BusinessDelegate {
	//User Rest Controller
	private UserRestController userRest;
	
	//Admin Rest Controllers
	private ProductCategoryRestController pCatRest;
	private ProductSubcategoryRestController pSubRest;
	private UnitMeasureRestController umRest;
	private ProductRestController prodRest;

	//Operator Rest Controllers
	private SpecialofferRestController soRest;
	private SpecialofferproductRestController sopRest;
	private SalesorderdetailRestController sodRest;
	
	@Autowired
	public BusinessDelegate(UserRestController userRest, ProductCategoryRestController pCatRest,
			ProductSubcategoryRestController pSubRest, UnitMeasureRestController umRest, ProductRestController prodRest,
			SpecialofferRestController soRest, SpecialofferproductRestController sopRest,
			SalesorderdetailRestController sodRest) {
		this.userRest = userRest;
		this.pCatRest = pCatRest;
		this.pSubRest = pSubRest;
		this.umRest = umRest;
		this.prodRest = prodRest;
		this.soRest = soRest;
		this.sopRest = sopRest;
		this.sodRest = sodRest;
	}
	
	//User Methods
	public Iterable<UserApp> userFindAll() {
		return userRest.getAll();
	}
	
	public UserApp userFindById(long id) {
		return userRest.getById(id);
	}
	
	public void userSave(UserApp user) {
		userRest.add(user);
	}
	
	public void userUpdate(UserApp user) {
		userRest.update(user);
	}
	
	public void userDelete(long id) {
		userRest.delete(id);
	}
	
	//Admin Methods
	public Iterable<Productcategory> pCatFindAll() {
		return pCatRest.getAll();
	}
	
	public Productcategory pCatSave(Productcategory pCat) {
		return pCatRest.add(pCat);
	}
	
	public Iterable<Productsubcategory> pSubFindAll() {
		return pSubRest.getAll();
	}
	
	public Productsubcategory pSUbSave(Productsubcategory pSub) {
		return pSubRest.add(pSub);
	}
	
	public Iterable<Unitmeasure> umFindAll() {
		return umRest.getAll();
	}
	
	public Unitmeasure umSave(Unitmeasure um) {
		return umRest.add(um);
	}
	
	//Product
	public Iterable<Product> prodFindAll() {
		return prodRest.getAll();
	}
	
	public Product prodFindById(Integer id) {
		return prodRest.getById(id);
	}
	
	public Product prodFindByProductNumber(String productnumber) {
		return prodRest.getByProductNumber(productnumber);
	}
	
	public Iterable<Product> prodFindByStyle(String style) {
		return prodRest.getByStyle(style);
	}
	
	public Iterable<Object[]> prodFindDateRange(LocalDate sellstartdate, LocalDate sellenddate) {
		return prodRest.getByDateRange(sellstartdate, sellenddate);
	}
	
	public Product prodSave(Product product) {
		return prodRest.add(product);
	}
	
	public void prodUpdate(Product product) {
		prodRest.update(product);
	}
	
	public void prodDelete(Integer id) {
		prodRest.delete(id);
	}
	
	//Operator Methods
	//Specialoffer
	public Iterable<Specialoffer> soFindAll() {
		return soRest.getAll();
	}
	
	public Specialoffer soFindById(Integer id) {
		return soRest.getById(id);
	}
	
	public Iterable<Specialoffer> soFindStartDate(Timestamp startdate) {
		return soRest.getByStartDate(startdate);
	}
	
	public Iterable<Specialoffer> soFindEndDate(Timestamp enddate) {
		return soRest.getByEndDate(enddate);
	}
	
	public Specialoffer soSave(Specialoffer specialoffer) {
		return soRest.add(specialoffer);
	}
	
	public void soUpdate(Specialoffer specialoffer) {
		soRest.update(specialoffer);
	}
	
	public void soDelete(Integer id) {
		soRest.delete(id);
	}
	
	//Specialofferproduct
	public Iterable<Specialofferproduct> sopFindAll() {
		return sopRest.getAll();
	}
	
	public Specialofferproduct sopFindById(SpecialofferproductPK id) {
		return sopRest.getById(id);
	}
	
	public Specialofferproduct sopSave(Specialofferproduct specialofferproduct) {
		return sopRest.add(specialofferproduct);
	}
	
	public void sopUpdate(Specialofferproduct specialofferproduct) {
		sopRest.update(specialofferproduct);
	}
	
	public void sopDelete(SpecialofferproductPK id) {
		sopRest.delete(id);
	}
	
	//Salesorderdetail
	public Iterable<Salesorderdetail> sodFindAll() {
		return sodRest.getAll();
	}
	
	public Salesorderdetail sodFindById(Integer id) {
		return sodRest.getById(id);
	}
	
	public Iterable<Salesorderdetail> sodFindByProductId(Integer pId) {
		return sodRest.getByProductId(pId);
	}
	
	public Iterable<Object[]> sodFindByMoreThanOneSOP() {
		return sodRest.getByMoreThanOneSOP();
	}
	
	public Salesorderdetail sodSave(Salesorderdetail salesorderdetail) {
		return sodRest.add(salesorderdetail);
	}
	
	public void sodUpdate(Salesorderdetail salesorderdetail) {
		sodRest.update(salesorderdetail);
	}
	
	public void sodDelete(Integer id) {
		sodRest.delete(id);
	}
}
