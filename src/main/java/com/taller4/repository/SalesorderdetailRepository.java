package com.taller4.repository;

import org.springframework.data.repository.CrudRepository;

import com.taller4.model.sales.Salesorderdetail;
import com.taller4.model.sales.SalesorderdetailPK;

public interface SalesorderdetailRepository extends CrudRepository<Salesorderdetail, SalesorderdetailPK> {
	
}
