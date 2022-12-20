package com.coffee.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coffee.dao.ProductDao;
import com.coffee.entity.Category;
import com.coffee.entity.Product;

import jakarta.persistence.EntityManager;
import util.HibernateUtil;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class ProductDaoImpl implements ProductDao {
	private EntityManager entityManager;
	/**
	 * 
	 */
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub


		try {

			entityManager.getTransaction().begin();
			List<Product> arrProducts = entityManager.createQuery("from Product p", Product.class).getResultList();

			return arrProducts;
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
