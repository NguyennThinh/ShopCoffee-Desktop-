package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.CategoryDao;
import com.coffee.entity.Category;

import jakarta.persistence.EntityManager;

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
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Category> categories = entityManager.createQuery("select c from Category c").getResultList();
		if (categories != null)
			return categories;
		return null;
	}

	@Override
	public boolean save(Category t) {
		// TODO Auto-generated method stub
		try {

			entityManager.getTransaction().begin();
			entityManager.persist(t);

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
	public boolean delete(Category t) {
		// TODO Auto-generated method stub
		try {

			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));

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
	public Category findByID(String categoryId) {
		// TODO Auto-generated method stub
		Category category = entityManager.find(Category.class, categoryId);
		if (category != null)
			return category;

		return null;
	}
}
