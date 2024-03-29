package com.taller4.backend.dao.implementation;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.dao.interfaces.ProductDao;
import com.taller4.backend.model.prod.*;

@Repository
@Scope("singleton")
public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void save(Product product) {
		entityManager.persist(product);
	}

	@Override
	@Transactional
	public void update(Product product) {
		entityManager.merge(product);
	}

	@Override
	@Transactional
	public void delete(Product product) {
		entityManager.remove(product);
	}

	@Override
	@Transactional
	public List<Product> findAll() {
		String jpql = "Select p from Product p";
		return entityManager.createQuery(jpql, Product.class).getResultList();
	}
	
	@Override
	@Transactional
	public List<String> findAllProductnumbers() {
		String jpql = "Select p.productnumber from Product p";
		return entityManager.createQuery(jpql, String.class).getResultList();
	}

	@Override
	@Transactional
	public Product findById(Integer id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	@Transactional
	public Product findByProductNumber(String productnumber) {
		String jpql = "Select p from Product p WHERE p.productnumber =: productnumber";
		return entityManager.createQuery(jpql, Product.class).setParameter("productnumber", productnumber).getSingleResult();
	}

	@Override
	@Transactional
	public List<Product> findByStyle(String style) {
		String jpql = "Select p from Product p WHERE p.style =: style";
		return entityManager.createQuery(jpql, Product.class).setParameter("style", style).getResultList();
	}

	@Override
	@Transactional
	public List<?> findByDateRange(LocalDate sellstartdate, LocalDate sellenddate) {
		String jpql = "SELECT p, COUNT(*) FROM Producto p, Specialofferproduct s WHERE s.id.productid = p.productid AND s.sellstartdate BETWEEN :sellstartdate AND :sellenddate AND s.sellenddate BETWEEN :sellstartdate AND :sellenddate ORDER BY p.productnumber ASC";
		return entityManager.createQuery(jpql).setParameter("sellstartdate", sellstartdate).setParameter("sellenddate", sellenddate).getResultList();
	}
}
