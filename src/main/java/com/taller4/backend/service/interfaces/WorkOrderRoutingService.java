package com.taller4.backend.service.interfaces;

import java.sql.Timestamp;

import com.taller4.backend.model.prod.*;

public interface WorkOrderRoutingService {
	public Workorderrouting saveWorkOrderRouting(Workorderrouting w);
	public Workorderrouting searchWorkOrderRouting(WorkorderroutingPK wId);
	public Workorderrouting updateWorkOrderRouting(WorkorderroutingPK wId, Workorderrouting w);
	public void deleteWorkOrderRouting(WorkorderroutingPK wId);
	public Workorderrouting findById(WorkorderroutingPK wId);
	public Iterable<Workorderrouting> findAll();
	public Iterable<Workorderrouting> findByStartDate(Timestamp startdate);
	public Iterable<Workorderrouting> findByEndDate(Timestamp enddate);
}
