package com.taller4.frontend.businessdelegate;

import java.time.LocalDate;
import java.util.*;

import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.taller4.backend.model.person.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;

import lombok.*;

@Component
public class BusinessDelegateURL {
	public final static String URL = "http://localhost:8081/";
	public final static String USERS_URL = URL + "users/";
	public final static String ADMIN_URL = URL + "admin/";
	public final static String OP_URL = URL + "operator/";
	public final static String PCAT_URL = URL + "productcategory/";
	public final static String PSUB_URL = URL + "productsubcategory/";
	public final static String UM_URL = URL + "unitmeasure/";
	public final static String PROD_URL = URL + "product/";
	public final static String WO_URL = URL + "workorder/";
	public final static String WOR_URL = URL + "workorderrouting/";
	public final static String SO_URL = URL + "specialoffer/";
	public final static String SOP_URL = URL + "specialofferproduct/";
	public final static String SOD_URL = URL + "salesorderdetail/";

	@Getter
	@Setter
	private RestTemplate restTemplate;
	
	public BusinessDelegateURL() {
		this.restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	//User
	public Iterable<UserApp> userFindAll() {
		return restTemplate.getForObject(USERS_URL, Iterable.class);
	}
	
	public UserApp userFindById(Integer id) {
		return restTemplate.getForObject(USERS_URL+id, UserApp.class);
	}
	
	public Iterable<UserType> userFindAllTypes() {
		return restTemplate.getForObject(USERS_URL+"types", Iterable.class);
	}
	
	public UserApp userSave(UserApp user) {
		return restTemplate.postForObject(USERS_URL, new HttpEntity<UserApp>(user), UserApp.class);
	}
	
	public void userUpdate(UserApp user) {
		restTemplate.put(USERS_URL, user, UserApp.class);
	}
	
	public void userDelete(UserApp user) {
		restTemplate.delete(USERS_URL+user.getId());
	}
	
	//Admin Methods
	public Iterable<Productcategory> pCatFindAll() {
		return restTemplate.getForObject(PCAT_URL, Iterable.class);
	}
	
	public Productcategory pCatSave(Productcategory pCat) {
		return restTemplate.postForObject(PCAT_URL, new HttpEntity<Productcategory>(pCat), Productcategory.class);
	}
	
	public Iterable<Productsubcategory> pSubFindAll() {
		return restTemplate.getForObject(PSUB_URL, Iterable.class);
	}
	
	public Productsubcategory pSUbSave(Productsubcategory pSub) {
		return restTemplate.postForObject(PSUB_URL, new HttpEntity<Productsubcategory>(pSub), Productsubcategory.class);
	}
	
	public Iterable<Unitmeasure> umFindAll() {
		return restTemplate.getForObject(UM_URL, Iterable.class);
	}
	
	public Unitmeasure umSave(Unitmeasure um) {
		return restTemplate.postForObject(UM_URL, new HttpEntity<Unitmeasure>(um), Unitmeasure.class);
	}
	
	//Product
	public Iterable<Product> productFindAll() {
		return restTemplate.getForObject(PROD_URL, Iterable.class);
	}
	
	public Product productFindById(Integer id) {
		return restTemplate.getForObject(PROD_URL+id, Product.class);
	}
	
	public Product prodFindByProductNumber(String productnumber) {
		return restTemplate.getForObject(PROD_URL+"get/"+productnumber, Product.class);
	}
	
	public Iterable<Product> prodFindByStyle(String style) {
		return restTemplate.getForObject(PROD_URL+"get/"+style, Iterable.class);
	}
	
	public Iterable<?> prodFindDateRange(LocalDate sellstartdate, LocalDate sellenddate) {
		return restTemplate.getForObject(PROD_URL+sellstartdate+"/to/"+sellenddate, Iterable.class);
	}
	
	public Product productSave(Product product) {
		return restTemplate.postForObject(PROD_URL, new HttpEntity<Product>(product), Product.class);
	}
	
	public void productUpdate(Product product) {
		restTemplate.put(PROD_URL, product, Product.class);
	}
	
	public void productDelete(Product product) {
		restTemplate.delete(PROD_URL+product.getProductid());
	}
	
	//Workorder
	public Iterable<Workorder> woFindAll() {
		return restTemplate.getForObject(WO_URL, Iterable.class);
	}
	
	public Workorder woFindById(Integer id) {
		return restTemplate.getForObject(WO_URL+id, Workorder.class);
	}
	
	public Iterable<Workorder> woFindByStartDate(LocalDate startdate) {
		return restTemplate.getForObject(WO_URL+"get/"+startdate, Iterable.class);
	}
	
	public Iterable<Workorder> woFindByEndDate(LocalDate enddate) {
		return restTemplate.getForObject(WO_URL+"get/"+enddate, Iterable.class);
	}
	
	public Workorder woSave(Workorder workorder) {
		return restTemplate.postForObject(WO_URL, new HttpEntity<Workorder>(workorder), Workorder.class);
	}
	
	public void woUpdate(Workorder workorder) {
		restTemplate.put(WO_URL, workorder, Workorder.class);
	}
	
	public void woDelete(Integer id) {
		restTemplate.delete(WO_URL+id);
	}
	
	//Workorderrouting
	public Iterable<Workorderrouting> worFindAll() {
		return restTemplate.getForObject(WOR_URL, Iterable.class);
	}
	
	public Workorderrouting worFindById(WorkorderroutingPK id) {
		return restTemplate.getForObject(WOR_URL+id, Workorderrouting.class);
	}
	
	public Iterable<Workorderrouting> worFindByStartDate(LocalDate startdate) {
		return restTemplate.getForObject(WOR_URL+"get/"+startdate, Iterable.class);
	}
	
	public Iterable<Workorderrouting> worFindByEndDate(LocalDate enddate) {
		return restTemplate.getForObject(WOR_URL+"get/"+enddate, Iterable.class);
	}
	
	public Workorderrouting worSave(Workorderrouting workorderrouting) {
		return restTemplate.postForObject(WOR_URL, new HttpEntity<Workorderrouting>(workorderrouting), Workorderrouting.class);
	}
	
	public void worUpdate(Workorderrouting workorderrouting) {
		restTemplate.put(WOR_URL, workorderrouting, Workorderrouting.class);
	}
	
	public void worDelete(WorkorderroutingPK id) {
		restTemplate.delete(WOR_URL+id);
	}
	
	//Operator Methods
	//Specialoffer
	public Iterable<Specialoffer> soFindAll() {
		return restTemplate.getForObject(SO_URL, Iterable.class);
	}
	
	public Specialoffer soFindById(Integer id) {
		return restTemplate.getForObject(SO_URL+id, Specialoffer.class);
	}
	
	public Iterable<Specialoffer> soFindStartDate(LocalDate startdate) {
		return restTemplate.getForObject(SO_URL+"get/"+startdate, Iterable.class);
	}
	
	public Iterable<Specialoffer> soFindEndDate(LocalDate enddate) {
		return restTemplate.getForObject(SO_URL+"get/"+enddate, Iterable.class);
	}
	
	public Specialoffer soSave(Specialoffer specialoffer) {
		return restTemplate.postForObject(SO_URL, new HttpEntity<Specialoffer>(specialoffer), Specialoffer.class);
	}
	
	public void soUpdate(Specialoffer specialoffer) {
		restTemplate.put(SO_URL, specialoffer, Specialoffer.class);
	}
	
	public void soDelete(Integer id) {
		restTemplate.delete(SO_URL+id);
	}
	
	//Specialofferproduct
	public Iterable<Specialofferproduct> sopFindAll() {
		return restTemplate.getForObject(SOP_URL, Iterable.class);
	}
	
	public Specialofferproduct sopFindById(SpecialofferproductPK id) {
		return restTemplate.getForObject(SOP_URL+id, Specialofferproduct.class);
	}
	
	public Specialofferproduct sopSave(Specialofferproduct specialofferproduct) {
		return restTemplate.postForObject(SOP_URL, new HttpEntity<Specialofferproduct>(specialofferproduct), Specialofferproduct.class);
	}
	
	public void sopUpdate(Specialofferproduct specialofferproduct) {
		restTemplate.put(SOP_URL, specialofferproduct, Specialofferproduct.class);
	}
	
	public void sopDelete(SpecialofferproductPK id) {
		restTemplate.delete(SOP_URL+id);
	}
	
	//Salesorderdetail
	public Iterable<Salesorderdetail> sodFindAll() {
		return restTemplate.getForObject(SOD_URL, Iterable.class);
	}
	
	public Salesorderdetail sodFindById(Integer id) {
		return restTemplate.getForObject(SOD_URL+id, Salesorderdetail.class);
	}
	
	public Iterable<Salesorderdetail> sodFindByProductId(Integer pId) {
		return restTemplate.getForObject(SOD_URL+"get/"+pId, Iterable.class);
	}
	
	public Iterable<?> sodFindByMoreThanOneSOP() {
		return restTemplate.getForObject(SOD_URL+"get/SOP", Iterable.class);
	}
	
	public Salesorderdetail sodSave(Salesorderdetail salesorderdetail) {
		return restTemplate.postForObject(SOD_URL, new HttpEntity<Salesorderdetail>(salesorderdetail), Salesorderdetail.class);
	}
	
	public void sodUpdate(Salesorderdetail salesorderdetail) {
		restTemplate.put(SOD_URL, salesorderdetail, Salesorderdetail.class);
	}
	
	public void sodDelete(Integer id) {
		restTemplate.delete(SOD_URL+id);
	}
}
