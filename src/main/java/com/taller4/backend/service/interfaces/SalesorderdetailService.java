package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.sales.*;

public interface SalesorderdetailService {
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId);
	public Salesorderdetail searchSalesOrderDetail(Integer sdId);
	public Salesorderdetail updateSalesOrderDetail(Integer sdId, Salesorderdetail sd);
	public void deleteSalesOrderDetail(Integer sdId);
	public Salesorderdetail findById(Integer id);
	public Iterable<Salesorderdetail> findAll();
	public Iterable<Salesorderdetail> findByProductId(Integer pId);
	public Iterable<?> findOrderDetailByProductWithMoreThanOneSOP();
}
