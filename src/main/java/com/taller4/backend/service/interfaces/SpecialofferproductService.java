package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.sales.*;

public interface SpecialofferproductService {
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId);
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId);
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp);
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId);
	Specialofferproduct findById(SpecialofferproductPK id);
	Iterable<Specialofferproduct> findAll();
}
