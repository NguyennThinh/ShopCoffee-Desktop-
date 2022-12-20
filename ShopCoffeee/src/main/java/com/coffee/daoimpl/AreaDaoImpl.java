package com.coffee.daoimpl;

import java.util.List;

import com.coffee.dao.AreaDao;
import com.coffee.entity.Area;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class AreaDaoImpl implements AreaDao {

	private EntityManager entityManager;
	/**
	 * 
	 */
	public AreaDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> getAll() {
		// TODO Auto-generated method stub

		
		try {
			

			entityManager.getTransaction().begin();
			Query query = entityManager.createQuery("from Area a", Area.class);

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
