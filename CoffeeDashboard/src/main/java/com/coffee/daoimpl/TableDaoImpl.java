package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.TableDao;
import com.coffee.entity.Area;
import com.coffee.entity.CoffeeTable;

import jakarta.persistence.EntityManager;

/**
 * @author Nguyen Phuc Thinh
 */
public class TableDaoImpl implements TableDao {

	private EntityManager entityManager;

	/**
	 * 
	 */
	public TableDaoImpl() {
		// TODO Auto-generated constructor stub
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> listArea() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select a from Area a").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoffeeTable> listTable() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select t from CoffeeTable t").getResultList();

	}

	@Override
	public boolean addTable(Area area) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.merge(area);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean addArea(Area area) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.persist(area);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean updateTable(CoffeeTable coffeeTable) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.merge(coffeeTable);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;

	}

	@Override
	public boolean updateArea(Area area) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.merge(area);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean deleteTable(CoffeeTable coffeeTable) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.remove(coffeeTable);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean deleteArea(Area area) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.remove(area);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

}
