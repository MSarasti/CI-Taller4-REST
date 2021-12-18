package com.taller4.backend.service.implementation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.dao.implementation.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	@Autowired
	private ProductDaoImpl pDao;
	@Autowired
	private WorkOrderDaoImpl woDao;
	
	public WorkOrderServiceImpl(WorkOrderDaoImpl woDao) {
		this.woDao = woDao;
	}

	@Override
	@Transactional
	public Workorder saveWorkOrder(Workorder w) {
		//w.setProduct(pDao.findById(w.getPId()));
		woDao.save(w);
		return w;
	}

	@Override
	@Transactional
	public Workorder searchWorkOrder(Integer wId) {
		return woDao.findById(wId);
	}

	@Override
	@Transactional
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
	@Transactional
	public void deleteWorkOrder(Integer wId) {
		woDao.delete(woDao.findById(wId));
	}

	@Override
	@Transactional
	public Iterable<Workorder> findAll() {
		return woDao.findAll();
	}
	
	@Override
	@Transactional
	public Iterable<Workorder> findByStartDate(LocalDate startdate) {
		return woDao.findByStartDate(startdate);
	}
	
	@Override
	@Transactional
	public Iterable<Workorder> findByEndDate(LocalDate enddate) {
		return woDao.findByEndDate(enddate);
	}

	@Override
	@Transactional
	public Workorder findById(Integer id) {
		return woDao.findById(id);
	}
}
