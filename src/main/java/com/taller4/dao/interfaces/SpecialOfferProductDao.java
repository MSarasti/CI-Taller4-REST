package com.taller4.dao.interfaces;

import java.util.List;

import com.taller4.model.sales.*;

public interface SpecialOfferProductDao {
	void save(Specialofferproduct specialofferproduct);
	void update(Specialofferproduct specialofferproduct);
	void delete(Specialofferproduct specialofferproduct);
	
	List<Specialofferproduct> findAll();
	Specialofferproduct findById(SpecialofferproductPK id);
}
