package com.taller4.frontend.businessdelegate;

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
public class BusinessDelegateA {
	public final static String URL = "http://localhost:8081/";
	public final static String USERS_URL = URL + "users/";
	public final static String ADMIN_URL = URL + "admin/";
	public final static String OP_URL = URL + "operator/";
	public final static String PROD_URL = URL + "product/";
	public final static String WO_URL = URL + "workorder/";
	public final static String WOR_URL = URL + "workorderrouting/";
	public final static String SO_URL = URL + "specialoffer/";
	public final static String SOP_URL = URL + "specialofferproduct/";
	public final static String SOD_URL = URL + "salesorderdetail/";

	@Getter
	@Setter
	private RestTemplate restTemplate;
	
	public BusinessDelegateA() {
		this.restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        this.restTemplate.setMessageConverters(messageConverters);
	}
	
	public Iterable<UserApp> userFindAll() {
		return restTemplate.getForObject(USERS_URL, Iterable.class);
	}
	
	public UserApp userSave(UserApp user) {
		return restTemplate.postForObject(USERS_URL, new HttpEntity<UserApp>(user), UserApp.class);
	}
	
	public UserApp userFindById(Integer id) {
		return restTemplate.getForObject(USERS_URL+id, UserApp.class);
	}
	
	public void userUpdate(UserApp user) {
		restTemplate.put(USERS_URL, user, UserApp.class);
	}
	
	public Iterable<Product> productFindAll() {
		return restTemplate.getForObject(PROD_URL, Iterable.class);
	}
	
	public Product productSave(Product product) {
		return restTemplate.postForObject(PROD_URL, new HttpEntity<Product>(product), Product.class);
	}
	
	public Product productFindById(Integer id) {
		return restTemplate.getForObject(PROD_URL+id, Product.class);
	}
	
	public void productUpdate(Product product) {
		restTemplate.put(PROD_URL, product, Product.class);
	}
	
	public Iterable<Specialoffer> specialofferFindAll() {
		return restTemplate.getForObject(SO_URL, Iterable.class);
	}
	
	public Specialoffer specialofferSave(Specialoffer specialoffer) {
		return restTemplate.postForObject(SO_URL, new HttpEntity<Specialoffer>(specialoffer), Specialoffer.class);
	}
	
	public Specialoffer specialofferFindById(Integer id) {
		return restTemplate.getForObject(SO_URL+id, Specialoffer.class);
	}
	
	public void specialofferUpdate(Specialoffer specialoffer) {
		restTemplate.put(SO_URL, specialoffer, Specialoffer.class);
	}
	
	public Iterable<Specialofferproduct> specialofferproductFindAll() {
		return restTemplate.getForObject(SOP_URL, Iterable.class);
	}
	
	public Specialofferproduct specialofferproductSave(Specialofferproduct specialofferproduct) {
		return restTemplate.postForObject(SOP_URL, new HttpEntity<Specialofferproduct>(specialofferproduct), Specialofferproduct.class);
	}
	
	public Specialofferproduct specialofferproductFindById(Integer id) {
		return restTemplate.getForObject(SOP_URL+id, Specialofferproduct.class);
	}
	
	public void specialofferproductUpdate(Specialofferproduct specialofferproduct) {
		restTemplate.put(SOP_URL, specialofferproduct, Specialofferproduct.class);
	}
	
	public Iterable<Salesorderdetail> salesorderdetailFindAll() {
		return restTemplate.getForObject(SOD_URL, Iterable.class);
	}
	
	public Salesorderdetail salesorderdetailSave(Salesorderdetail salesorderdetail) {
		return restTemplate.postForObject(SOD_URL, new HttpEntity<Salesorderdetail>(salesorderdetail), Salesorderdetail.class);
	}
	
	public Salesorderdetail salesorderdetailFindById(Integer id) {
		return restTemplate.getForObject(SOD_URL+id, Salesorderdetail.class);
	}
	
	public void salesorderdetailUpdate(Salesorderdetail salesorderdetail) {
		restTemplate.put(SOD_URL, salesorderdetail, Salesorderdetail.class);
	}
}
