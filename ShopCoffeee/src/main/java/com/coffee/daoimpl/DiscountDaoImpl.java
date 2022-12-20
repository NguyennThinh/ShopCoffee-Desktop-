package com.coffee.daoimpl;

import java.util.List;

import com.coffee.dao.DiscountDao;
import com.coffee.entity.Discount;

import jakarta.persistence.EntityManager;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class DiscountDaoImpl implements DiscountDao {
	private EntityManager entityManager;
	/**
	 * 
	 */
	public DiscountDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		
		
		return entityManager.createQuery("select d from Discount d").getResultList();
	}

}
