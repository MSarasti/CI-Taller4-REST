package com.taller4.backend.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.taller4.backend.model.prod.*;

public interface WorkOrderRoutingDao {
	void save(Workorderrouting workorderrouting);
	void update(Workorderrouting workorderrouting);
	void delete(Workorderrouting workorderrouting);
	
	List<Workorderrouting> findAll();
	Workorderrouting findById(WorkorderroutingPK id);
	List<Workorderrouting> findByStartDate(Timestamp startdate);	
	List<Workorderrouting> findByEndDate(Timestamp enddate);
}
