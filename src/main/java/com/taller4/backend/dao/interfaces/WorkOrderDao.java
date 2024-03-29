package com.taller4.backend.dao.interfaces;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.taller4.backend.model.prod.*;

public interface WorkOrderDao {
	void save(Workorder workorder);
	void update(Workorder workorder);
	void delete(Workorder workorder);
	
	List<Workorder> findAll();
	Workorder findById(Integer id);
	List<Workorder> findByStartDate(LocalDate startdate);	
	List<Workorder> findByEndDate(LocalDate enddate);
}
