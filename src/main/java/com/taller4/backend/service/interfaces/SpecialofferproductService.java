package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.sales.*;

public interface SpecialofferproductService {
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) throws Exception;
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId);
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) throws Exception;
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId);
}
