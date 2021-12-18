package com.taller4.backend.service.interfaces;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.taller4.backend.model.prod.*;

public interface WorkOrderRoutingService {
	public Workorderrouting saveWorkOrderRouting(Workorderrouting w);
	public Workorderrouting searchWorkOrderRouting(WorkorderroutingPK wId);
	public Workorderrouting updateWorkOrderRouting(WorkorderroutingPK wId, Workorderrouting w);
	public void deleteWorkOrderRouting(WorkorderroutingPK wId);
	public Workorderrouting findById(WorkorderroutingPK wId);
	public Iterable<Workorderrouting> findAll();
	public Iterable<Workorderrouting> findByStartDate(LocalDate startdate);
	public Iterable<Workorderrouting> findByEndDate(LocalDate enddate);
	Workorderrouting searchWorkOrderRouting(Integer wId, Integer pId, Integer op);
}
