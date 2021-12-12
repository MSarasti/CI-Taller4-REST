package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.sales.*;

public interface SalesorderdetailService {
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) throws Exception;
	public Salesorderdetail searchSalesOrderDetail(Integer sdId);
	public Salesorderdetail updateSalesOrderDetail(Integer sdId, Salesorderdetail sd) throws Exception;
	public void deleteSalesOrderDetail(Integer sdId);
}
