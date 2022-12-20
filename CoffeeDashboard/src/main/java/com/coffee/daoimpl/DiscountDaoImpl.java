package com.coffee.daoimpl;

import java.util.ArrayList;
import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.DiscountDao;
import com.coffee.entity.Discount;
import com.coffee.entity.Product;

import jakarta.persistence.EntityManager;

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
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		List<Discount> arrDiscounts = entityManager.createQuery("select d from Discount d").getResultList();

		if (arrDiscounts != null)
			return arrDiscounts;
		return null;
	}

	@Override
	public boolean save(Discount t) {
		// TODO Auto-generated method stub
		try {
			Discount discount = findByID(t.getDiscountID());
			entityManager.getTransaction().begin();
			if (discount == null) {
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
	public boolean delete(Discount t) {
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
	public boolean update(Discount t, List<Product> arrProducts) {
		// TODO Auto-generated method stub
		try {

			entityManager.getTransaction().begin();
			Discount discount = entityManager.find(Discount.class, t.getDiscountID());

			List<Product> arrList = new ArrayList<>(discount.getProducts());

			for (Product p : arrList) {
				p.setDiscount(null);
				entityManager.merge(p);
			}

			for (Product p : arrProducts) {
				p.setDiscount(discount);
				entityManager.merge(p);
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
	public Discount findByID(String discountId) {
		// TODO Auto-generated method stub
		Discount discount = entityManager.find(Discount.class, discountId);
		if (discount != null)
			return discount;
		return null;
	}

}
