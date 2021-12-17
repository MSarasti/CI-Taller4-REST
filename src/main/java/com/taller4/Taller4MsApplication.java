package com.taller4;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
		/*SpringApplication.run(Taller4MsApplication.class, args);*/
		ConfigurableApplicationContext c = SpringApplication.run(Taller4MsApplication.class, args);
		UserServiceImpl userService = c.getBean(UserServiceImpl.class);
		ProductServiceImpl pService = c.getBean(ProductServiceImpl.class);
		SpecialOfferServiceImpl soService = c.getBean(SpecialOfferServiceImpl.class);
		SpecialOfferProductServiceImpl sopService = c.getBean(SpecialOfferProductServiceImpl.class);
		SalesOrderDetailServiceImpl sodService = c.getBean(SalesOrderDetailServiceImpl.class);
		WorkOrderServiceImpl woService = c.getBean(WorkOrderServiceImpl.class);
		WorkOrderRoutingServiceImpl worService = c.getBean(WorkOrderRoutingServiceImpl.class);
		
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
		pService.saveUnitmeasure(um2);
		
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
		
		Workorder wo1 = new Workorder();
		wo1.setStartdate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wo1.setEnddate(Timestamp.valueOf(LocalDate.now().plusWeeks(2).atStartOfDay()));
		wo1.setModifieddate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wo1.setDuedate(Timestamp.valueOf(LocalDate.now().plusWeeks(1).atStartOfDay()));
		wo1.setProduct(p1);
		wo1.setOrderqty(2);
		
		Workorder wo2 = new Workorder();
		wo2.setStartdate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wo2.setEnddate(Timestamp.valueOf(LocalDate.now().plusWeeks(3).atStartOfDay()));
		wo2.setModifieddate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wo2.setDuedate(Timestamp.valueOf(LocalDate.now().plusWeeks(2).atStartOfDay()));
		wo2.setProduct(p2);
		wo2.setOrderqty(3);
		
		Workorderrouting wor1 = new Workorderrouting();
		WorkorderroutingPK worPK = new WorkorderroutingPK();
		worPK.setProductid(p1.getProductid());
		worPK.setWorkorderid(wo1.getWorkorderid());
		worPK.setOperationsequence(1);
		wor1.setId(worPK);
		wor1.setScheduledstartdate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wor1.setActualstartdate(Timestamp.valueOf(LocalDate.now().plusDays(2).atStartOfDay()));
		wor1.setScheduledenddate(Timestamp.valueOf(LocalDate.now().plusWeeks(2).atStartOfDay()));
		wor1.setActualenddate(Timestamp.valueOf(LocalDate.now().plusWeeks(3).atStartOfDay()));
		wor1.setModifieddate(Timestamp.valueOf(LocalDate.now().atStartOfDay()));
		wor1.setWorkorder(wo1);
		
		pService.saveProduct(p1, pc.getProductcategoryid(), psc.getProductsubcategoryid(), um1.getUnitmeasurecode(), um2.getUnitmeasurecode());
		pService.saveProduct(p2, pc.getProductcategoryid(), psc.getProductsubcategoryid(), um1.getUnitmeasurecode(), um2.getUnitmeasurecode());
		
		woService.saveWorkOrder(wo1);
		woService.saveWorkOrder(wo2);
		
		worService.saveWorkOrderRouting(wor1);
		
		Specialoffer so = new Specialoffer();
		//so.setSpecialofferid(1);
		so.setCategory("Cat 1");
		so.setModifieddate(LocalDate.now());
		//System.out.println("Special offer ID = "+so.getSpecialofferid());
		
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
		
		soService.saveSpecialOffer(so);
		sopService.saveSpecialOfferProduct(sop, p1.getProductid(), so.getSpecialofferid());
		sodService.saveSalesOrderDetail(sod, sopPK);
	}
	/*
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
	}*/

}
