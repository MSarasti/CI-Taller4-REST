package com.taller4;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.taller4.backend.dao.implementation.*;
import com.taller4.backend.model.person.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;
import com.taller4.backend.repository.*;
import com.taller4.backend.service.implementation.*;

@SpringBootApplication
@EntityScan(basePackages = {"com.taller4.backend.security","com.taller4.backend.model"})
@ComponentScan(basePackages = {"com.taller4.backend.security","com.taller4.backend.repository","com.taller4.backend.dao.implementation","com.taller4.backend.service.implementation","com.taller4.backend.controller"})
public class Taller4MsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Taller4MsApplication.class, args);
		/*ConfigurableApplicationContext c = SpringApplication.run(Taller3MsApplication.class, args);
		UserServiceImpl userService = c.getBean(UserServiceImpl.class);
		ProductServiceImpl pService = c.getBean(ProductServiceImpl.class);
		UserApp user1 = new UserApp();
		user1.setUsername("Simba1");
		user1.setPassword("{noop}Simba1234");
		user1.setType(UserType.ADMIN);
		
		UserApp user2 = new UserApp();
		user2.setUsername("Ginger1");
		user2.setPassword("{noop}Ginger1234");
		user2.setType(UserType.OPERATOR);
		
		userService.save(user1);
		userService.save(user2);
		
		Productcategory pc = new Productcategory();
		pc.setName("Food");
		
		Productsubcategory psc = new Productsubcategory();
		psc.setName("Meat");
		psc.setProductcategory(pc);
		
		Unitmeasure um1 = new Unitmeasure();
		um1.setName("kg");
		
		Unitmeasure um2 = new Unitmeasure();
		um2.setName("pound");
		
		pService.saveProductCategory(pc);
		pService.saveProductSubcategory(psc);
		pService.saveUnitmeasure(um1);
		pService.saveUnitmeasure(um2);*/
	}
	
	@Bean
	CommandLineRunner runner(UserRepository ur, ProductCategoryRepository pcr, ProductSubcategoryRepository pscr, UnitmeasureRepository umr, ProductDaoImpl pDao, SpecialOfferDaoImpl soDao, SpecialOfferProductDaoImpl sopDao, SalesOrderDetailDaoImpl sodDao) {
		return args -> {
			UserApp user1 = new UserApp();
			user1.setUsername("Simba1");
			user1.setPassword("{noop}Simba1234");
			user1.setType(UserType.ADMIN);
			
			UserApp user2 = new UserApp();
			user2.setUsername("Ginger1");
			user2.setPassword("{noop}Ginger1234");
			user2.setType(UserType.OPERATOR);
			
			ur.save(user1);
			ur.save(user2);
			
			Productcategory pc = new Productcategory();
			pc.setName("Food");
			
			Productsubcategory psc = new Productsubcategory();
			psc.setName("Meat");
			pc.addProductsubcategory(psc);
			psc.setProductcategory(pc);
			
			Unitmeasure um1 = new Unitmeasure();
			um1.setName("kg");
			
			Unitmeasure um2 = new Unitmeasure();
			um2.setName("pound");
			
			pcr.save(pc);
			pscr.save(psc);
			umr.save(um1);
			umr.save(um2);
			
			Product p1 = new Product();
			p1.setName("Porkchop");
			p1.setDaystomanufacture(360);
			p1.setProductnumber("1");
			p1.setSellstartdate(LocalDate.now());
			p1.setSellenddate(LocalDate.now().plusWeeks(4));
			p1.setProductsubcategory(psc);
			p1.setUnitmeasure1(um1);
			p1.setUnitmeasure2(um2);
			
			Product p2 = new Product();
			p2.setName("Beef");
			p2.setDaystomanufacture(360);
			p2.setProductnumber("2");
			p2.setSellstartdate(LocalDate.now());
			p2.setSellenddate(LocalDate.now().plusWeeks(3));
			p2.setProductsubcategory(psc);
			p2.setUnitmeasure1(um1);
			p2.setUnitmeasure2(um2);
			
			pDao.save(p1);
			pDao.save(p2);
			
			Specialoffer so = new Specialoffer();
			so.setCategory("Cat 1");
			so.setModifieddate(LocalDate.now());
			System.out.println("Special offer ID = "+so.getSpecialofferid());
			
			Specialofferproduct sop = new Specialofferproduct();
			SpecialofferproductPK sopPK = new SpecialofferproductPK();
			sopPK.setProductid(p1.getProductid());
			sopPK.setSpecialofferid(so.getSpecialofferid());
			sop.setId(sopPK);
			sop.setModifieddate(LocalDate.now());
			sop.setSpecialoffer(so);
			so.addSpecialofferproduct(sop);
			
			Salesorderdetail sod = new Salesorderdetail();
			sod.setUnitprice(BigDecimal.valueOf(0.5));
			sod.setUnitpricediscount(BigDecimal.ZERO);
			sod.setSpecialofferproduct(sop);
			sop.addSalesorderdetail(sod);
			
			soDao.save(so);
			sopDao.save(sop);
			sodDao.save(sod);
		};
	}
}
