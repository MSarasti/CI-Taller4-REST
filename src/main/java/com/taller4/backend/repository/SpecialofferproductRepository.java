package com.taller4.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taller4.backend.model.sales.*;

public interface SpecialofferproductRepository extends CrudRepository<Specialofferproduct, SpecialofferproductPK> {
	Optional<Specialofferproduct> findBySpecialoffer(Specialoffer specialoffer);
}
