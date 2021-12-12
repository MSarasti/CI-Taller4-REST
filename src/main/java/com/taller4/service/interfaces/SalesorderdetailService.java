package com.taller4.service.interfaces;

import com.taller4.model.sales.Salesorderdetail;
import com.taller4.model.sales.SalesorderdetailPK;

public interface SalesorderdetailService {
	public Salesorderdetail saveSalesOrderDetail(Salesorderdetail sd, Integer pId, Integer soId) throws Exception;
	public Salesorderdetail searchSalesOrderDetail(Integer sdId);
	public Salesorderdetail updateSalesOrderDetail(Integer sdId, Salesorderdetail sd) throws Exception;
	public void deleteSalesOrderDetail(Integer sdId);
}
