package com.taller4.backend.dao.implementation;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.dao.interfaces.*;
import com.taller4.backend.model.prod.*;

@Repository
@Scope("singleton")
public class WorkOrderDaoImpl implements WorkOrderDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Workorder workorder) {
		entityManager.persist(workorder);
	}

	@Override
	@Transactional
	public void update(Workorder workorder) {
		entityManager.merge(workorder);
	}

	@Override
	@Transactional
	public void delete(Workorder workorder) {
		entityManager.remove(workorder);
	}

	@Override
	@Transactional
	public List<Workorder> findAll() {
		String jpql = "Select w from Workorder w";
		return entityManager.createQuery(jpql, Workorder.class).getResultList();
	}

	@Override
	@Transactional
	public Workorder findById(Integer id) {
		return entityManager.find(Workorder.class, id);
	}

	@Override
	@Transactional
	public List<Workorder> findByStartDate(Timestamp startdate) {
		String jpql = "Select w from Workorder w WHERE w.startdate =: startdate";
		return entityManager.createQuery(jpql, Workorder.class).setParameter("startdate", startdate).getResultList();
	}

	@Override
	@Transactional
	public List<Workorder> findByEndDate(Timestamp enddate) {
		String jpql = "Select w from Workorder w WHERE w.enddate =: enddate";
		return entityManager.createQuery(jpql, Workorder.class).setParameter("enddate", enddate).getResultList();
	}

}
