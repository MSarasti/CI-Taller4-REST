package com.taller4.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taller4.model.sales.Specialoffer;
import com.taller4.model.sales.Specialofferproduct;
import com.taller4.model.sales.SpecialofferproductPK;

public interface SpecialofferproductRepository extends CrudRepository<Specialofferproduct, SpecialofferproductPK> {
	Optional<Specialofferproduct> findBySpecialoffer(Specialoffer specialoffer);
}
