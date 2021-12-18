package com.taller4.backend.dao.interfaces;

import java.time.*;
import java.util.List;

import com.taller4.backend.model.prod.*;

public interface WorkOrderRoutingDao {
	void save(Workorderrouting workorderrouting);
	void update(Workorderrouting workorderrouting);
	void delete(Workorderrouting workorderrouting);
	
	List<Workorderrouting> findAll();
	Workorderrouting findById(WorkorderroutingPK id);
	List<Workorderrouting> findByStartDate(LocalDate startdate);	
	List<Workorderrouting> findByEndDate(LocalDate enddate);
	Workorderrouting findById(Integer wId, Integer pId, Integer op);
}
