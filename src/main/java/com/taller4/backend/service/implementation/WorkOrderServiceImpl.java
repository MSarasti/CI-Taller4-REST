package com.taller4.backend.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller4.backend.dao.implementation.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	
	@Autowired
	private WorkOrderDaoImpl woDao;
	
	public WorkOrderServiceImpl(WorkOrderDaoImpl woDao) {
		this.woDao = woDao;
	}

	@Override
	public Workorder saveWorkOrder(Workorder w) {
		woDao.save(w);
		return w;
	}

	@Override
	public Workorder searchWorkOrder(Integer wId) {
		return woDao.findById(wId);
	}

	@Override
	public Workorder updateWorkOrder(Integer wId, Workorder w) {
		Workorder toChange = woDao.findById(wId);
		
		toChange.setStartdate(w.getStartdate());
		toChange.setEnddate(w.getEnddate());
		toChange.setDuedate(w.getDuedate());
		toChange.setModifieddate(w.getModifieddate());
		toChange.setProduct(w.getProduct());
		toChange.setWorkorderroutings(w.getWorkorderroutings());
		
		woDao.update(toChange);
		return w;
	}

	@Override
	public void deleteWorkOrder(Integer wId) {
		woDao.delete(woDao.findById(wId));
	}

	public Iterable<Workorder> findAll() {
		return woDao.findAll();
	}
	
	public Iterable<Workorder> findByStartDate(Timestamp startdate) {
		return woDao.findByStartDate(startdate);
	}
	
	public Iterable<Workorder> findByEndDate(Timestamp enddate) {
		return woDao.findByEndDate(enddate);
	}

	public Workorder findById(Integer id) {
		return woDao.findById(id);
	}
}
