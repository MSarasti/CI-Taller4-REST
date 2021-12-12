package com.taller4.backend.service.implementation;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller4.backend.dao.implementation.*;
import com.taller4.backend.dao.interfaces.SpecialOfferProductDao;
import com.taller4.backend.model.prod.*;
import com.taller4.backend.model.sales.*;
import com.taller4.backend.repository.*;
import com.taller4.backend.service.interfaces.SpecialofferproductService;

@Service
public class SpecialOfferProductServiceImpl implements SpecialofferproductService {
	@Autowired
	public ProductDaoImpl pDao;
	@Autowired
	public SpecialOfferDaoImpl soDao;
	@Autowired
	public SpecialOfferProductDao sopDao;
	
	public SpecialOfferProductServiceImpl() {
	}

	@Override
	public Specialofferproduct saveSpecialOfferProduct(Specialofferproduct sp, Integer pId, Integer soId) {
		Product p = pDao.findById(pId);
		Specialoffer so = soDao.findById(soId);
		sp.setSpecialoffer(so);
		sp.getId().setProductid(p.getProductid());
		sp.getId().setSpecialofferid(soId);
		sopDao.save(sp);
		
		return sp;
	}

	@Override
	public Specialofferproduct searchSpecialOfferProduct(SpecialofferproductPK spId) {
		return sopDao.findById(spId);
	}

	@Override
	public Specialofferproduct updateSpecialOfferProduct(SpecialofferproductPK spId, Specialofferproduct sp) {
		Specialofferproduct toChange = sopDao.findById(spId);
		toChange.setModifieddate(sp.getModifieddate());
		toChange.setRowguid(sp.getRowguid());
		toChange.setSpecialoffer(sp.getSpecialoffer());
		sopDao.update(toChange);
		return sp;
	}

	@Override
	public void deleteSpecialOfferProduct(SpecialofferproductPK spId) {
		sopDao.delete(sopDao.findById(spId));
	}

	public Iterable<Specialofferproduct> findAll(){
		return sopDao.findAll();
	}

	public Specialofferproduct findById(SpecialofferproductPK id) {
		return sopDao.findById(id);
	}
}
