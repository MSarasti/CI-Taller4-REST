package com.taller4.backend.service.interfaces;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.taller4.backend.model.prod.*;

public interface WorkOrderService {
	public Workorder saveWorkOrder(Workorder w);
	public Workorder searchWorkOrder(Integer wId);
	public Workorder updateWorkOrder(Integer wId, Workorder w);
	public void deleteWorkOrder(Integer wId);
	public Workorder findById(Integer wId);
	public Iterable<Workorder> findAll();
	public Iterable<Workorder> findByStartDate(LocalDate startdate);
	public Iterable<Workorder> findByEndDate(LocalDate enddate);
}
