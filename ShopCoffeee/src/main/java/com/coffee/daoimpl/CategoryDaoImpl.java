package com.coffee.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coffee.dao.CategoryDao;
import com.coffee.entity.Area;
import com.coffee.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.HibernateUtil;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class CategoryDaoImpl implements CategoryDao {

	private EntityManager entityManager;
	/**
	 * 
	 */
	public CategoryDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub



		try {

			entityManager.getTransaction().begin();;
			List<Category> arrCategories = entityManager.createQuery("from Category c", Category.class).getResultList();

			return arrCategories;
		} catch (Exception e) {
			e.printStackTrace();

			entityManager.getTransaction().rollback();
			return null;
			// TODO: handle exception
		} finally {
			entityManager.getTransaction().commit();
		}
	}

}
