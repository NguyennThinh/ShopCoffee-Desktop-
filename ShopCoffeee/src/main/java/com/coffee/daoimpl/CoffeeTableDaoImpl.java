package com.coffee.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.coffee.dao.CoffeeTableDao;
import com.coffee.entity.Area;
import com.coffee.entity.CoffeeTable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.HibernateUtil;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class CoffeeTableDaoImpl implements CoffeeTableDao{

	private EntityManager entityManager;
	/**
	 * 
	 */
	public CoffeeTableDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public boolean openTable(CoffeeTable table) {
		// TODO Auto-generated method stub
		

		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.merge(table);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean closeTable(CoffeeTable table) {
		// TODO Auto-generated method stub

		
		try {
			
			entityManager.getTransaction().begin();
			entityManager.merge(table);

			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoffeeTable> getTableActive() {
		// TODO Auto-generated method stub

		String jpql = "Select c from CoffeeTable c where c.status = true";
		try {

			entityManager.getTransaction().begin();;
			Query query = entityManager.createQuery(jpql, CoffeeTable.class);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			
			entityManager.getTransaction().rollback();
			return null;
			// TODO: handle exception
		}finally {
			entityManager.getTransaction().commit();
		}
	}

}
