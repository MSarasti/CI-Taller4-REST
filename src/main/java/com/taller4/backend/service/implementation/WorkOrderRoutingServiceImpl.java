package com.taller4.backend.service.implementation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.dao.implementation.*;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.service.interfaces.*;

@Service
public class WorkOrderRoutingServiceImpl implements WorkOrderRoutingService {
	@Autowired
	private WorkOrderRoutingDaoImpl wDao;
	
	public WorkOrderRoutingServiceImpl(WorkOrderRoutingDaoImpl wDao) {
		this.wDao = wDao;
	}

	@Override
	@Transactional
	public Workorderrouting saveWorkOrderRouting(Workorderrouting w) {
		wDao.save(w);
		return w;
	}

	@Override
	@Transactional
	public Workorderrouting searchWorkOrderRouting(WorkorderroutingPK wId) {
		return wDao.findById(wId);
	}
	
	@Override
	@Transactional
	public Workorderrouting searchWorkOrderRouting(Integer wId, Integer pId, Integer op) {
		return wDao.findById(wId, pId, op);
	}

	@Override
	@Transactional
	public Workorderrouting updateWorkOrderRouting(WorkorderroutingPK wId, Workorderrouting w) {
		Workorderrouting toChange = wDao.findById(wId);
		
		toChange.setActualstartdate(w.getActualstartdate());
		toChange.setActualenddate(w.getActualenddate());
		toChange.setModifieddate(w.getModifieddate());
		toChange.setWorkorder(w.getWorkorder());
		toChange.setScheduledstartdate(w.getScheduledstartdate());
		toChange.setScheduledenddate(w.getScheduledenddate());
		
		wDao.update(toChange);
		return w;
	}

	@Override
	@Transactional
	public void deleteWorkOrderRouting(WorkorderroutingPK wId) {
		wDao.delete(wDao.findById(wId));
	}

	@Override
	@Transactional
	public Iterable<Workorderrouting> findAll() {
		return wDao.findAll();
	}
	
	@Override
	@Transactional
	public Iterable<Workorderrouting> findByStartDate(LocalDate startdate) {
		return wDao.findByStartDate(startdate);
	}
	
	@Override
	@Transactional
	public Iterable<Workorderrouting> findByEndDate(LocalDate enddate) {
		return wDao.findByEndDate(enddate);
	}

	@Override
	@Transactional
	public Workorderrouting findById(WorkorderroutingPK id) {
		return wDao.findById(id);
	}
}
