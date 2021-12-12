package com.taller4.backend.dao.implementation;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.dao.interfaces.*;
import com.taller4.backend.model.sales.*;

@Repository
@Scope("singleton")
public class SpecialOfferDaoImpl implements SpecialOfferDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Specialoffer specialoffer) {
		entityManager.persist(specialoffer);
	}

	@Override
	@Transactional
	public void update(Specialoffer specialoffer) {
		entityManager.merge(specialoffer);
	}

	@Override
	@Transactional
	public void delete(Specialoffer specialoffer) {
		entityManager.remove(specialoffer);
	}

	@Override
	@Transactional
	public List<Specialoffer> findAll() {
		String jpql = "Select s from Specialoffer s";
		return entityManager.createQuery(jpql, Specialoffer.class).getResultList();
	}

	@Override
	@Transactional
	public Specialoffer findById(Integer id) {
		return entityManager.find(Specialoffer.class, id);
	}

	@Override
	@Transactional
	public List<Specialoffer> findByStartDate(Timestamp startdate) {
		String jpql = "Select s from Specialoffer s WHERE s.startdate =: startdate";
		return entityManager.createQuery(jpql, Specialoffer.class).setParameter("startdate", startdate).getResultList();
	}

	@Override
	@Transactional
	public List<Specialoffer> findByEndDate(Timestamp enddate) {
		String jpql = "Select s from Specialoffer s WHERE s.enddate =: enddate";
		return entityManager.createQuery(jpql, Specialoffer.class).setParameter("enddate", enddate).getResultList();
	}

}
