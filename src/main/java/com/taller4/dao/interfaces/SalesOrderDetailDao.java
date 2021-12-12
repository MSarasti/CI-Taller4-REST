package com.taller4.dao.interfaces;

import java.util.List;

import com.taller4.model.sales.*;

public interface SalesOrderDetailDao {
	void save(Salesorderdetail salesorderdetail);
	void update(Salesorderdetail salesorderdetail);
	void delete(Salesorderdetail salesorderdetail);
	
	List<Salesorderdetail> findAll();
	Salesorderdetail findById(Integer id);
	List<Salesorderdetail> findByProductId(Integer pId);
	List<Object[]> findOrderDetailByProductWithMoreThanOneSOP();
}
