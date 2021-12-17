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
public class WorkOrderRoutingServiceImpl implements WorkOrderRoutingService {
	
	@Autowired
	private WorkOrderRoutingDaoImpl wDao;
	
	public WorkOrderRoutingServiceImpl(WorkOrderRoutingDaoImpl wDao) {
		this.wDao = wDao;
	}

	@Override
	public Workorderrouting saveWorkOrderRouting(Workorderrouting w) {
		wDao.save(w);
		return w;
	}

	@Override
	public Workorderrouting searchWorkOrderRouting(WorkorderroutingPK wId) {
		return wDao.findById(wId);
	}

	@Override
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
	public void deleteWorkOrderRouting(WorkorderroutingPK wId) {
		wDao.delete(wDao.findById(wId));
	}

	public Iterable<Workorderrouting> findAll() {
		return wDao.findAll();
	}
	
	public Iterable<Workorderrouting> findByStartDate(Timestamp startdate) {
		return wDao.findByStartDate(startdate);
	}
	
	public Iterable<Workorderrouting> findByEndDate(Timestamp enddate) {
		return wDao.findByEndDate(enddate);
	}

	public Workorderrouting findById(WorkorderroutingPK id) {
		return wDao.findById(id);
	}
}
