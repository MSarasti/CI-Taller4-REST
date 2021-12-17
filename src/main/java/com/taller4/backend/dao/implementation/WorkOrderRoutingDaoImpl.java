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
public class WorkOrderRoutingDaoImpl implements WorkOrderRoutingDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Workorderrouting workorderrouting) {
		entityManager.persist(workorderrouting);
	}

	@Override
	@Transactional
	public void update(Workorderrouting workorderrouting) {
		entityManager.merge(workorderrouting);
	}

	@Override
	@Transactional
	public void delete(Workorderrouting workorderrouting) {
		entityManager.remove(workorderrouting);
	}

	@Override
	@Transactional
	public List<Workorderrouting> findAll() {
		String jpql = "Select w from Workorderrouting w";
		return entityManager.createQuery(jpql, Workorderrouting.class).getResultList();
	}

	@Override
	@Transactional
	public Workorderrouting findById(WorkorderroutingPK id) {
		return entityManager.find(Workorderrouting.class, id);
	}

	@Override
	@Transactional
	public List<Workorderrouting> findByStartDate(Timestamp startdate) {
		String jpql = "Select w from Workorderrouting w WHERE w.actualstartdate =: actualstartdate";
		return entityManager.createQuery(jpql, Workorderrouting.class).setParameter("actualstartdate", startdate).getResultList();
	}

	@Override
	@Transactional
	public List<Workorderrouting> findByEndDate(Timestamp enddate) {
		String jpql = "Select w from Workorderrouting w WHERE w.actualenddate =: actualenddate";
		return entityManager.createQuery(jpql, Workorderrouting.class).setParameter("actualenddate", enddate).getResultList();
	}

}
