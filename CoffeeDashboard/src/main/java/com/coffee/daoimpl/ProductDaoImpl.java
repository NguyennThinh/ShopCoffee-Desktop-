package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.ProductDao;
import com.coffee.entity.Product;

import jakarta.persistence.EntityManager;

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
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		List<Product> arrProducts = entityManager.createQuery("select p from Product p").getResultList();
		if (arrProducts == null)
			return null;
		return arrProducts;
	}

	@Override
	public boolean save(Product t) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();
			Product product = findByID(t.getProduct_id());
			if (product == null) {
				entityManager.persist(t);
			} else {
				entityManager.merge(t);
			}

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
	public boolean delete(Product t) {
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
	public Product findByID(String productId) {
		// TODO Auto-generated method stub
		Product product = entityManager.find(Product.class, productId);
		return product;
	}

}
