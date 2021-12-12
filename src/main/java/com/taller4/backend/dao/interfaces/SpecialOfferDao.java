package com.taller4.backend.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.taller4.backend.model.sales.*;

public interface SpecialOfferDao {
	void save(Specialoffer specialoffer);
	void update(Specialoffer specialoffer);
	void delete(Specialoffer specialoffer);
	
	List<Specialoffer> findAll();
	Specialoffer findById(Integer id);
	List<Specialoffer> findByStartDate(Timestamp startdate);	
	List<Specialoffer> findByEndDate(Timestamp enddate);
}
