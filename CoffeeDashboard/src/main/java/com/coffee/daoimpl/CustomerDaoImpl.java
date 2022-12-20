package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.CustomerDao;
import com.coffee.entity.Customer;

import jakarta.persistence.EntityManager;

/**
 * @author Nguyen Phuc Thinh
 */
public class CustomerDaoImpl implements CustomerDao {

	private EntityManager entityManager;

	/**
	 * 
	 */
	public CustomerDaoImpl() {
		// TODO Auto-generated constructor stub
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		List<Customer> arrCustomers = entityManager.createQuery("select c from Customer c").getResultList();
		if (arrCustomers != null)
			return arrCustomers;
		return null;
	}

	@Override
	public boolean save(Customer t) {
		// TODO Auto-generated method stub

		try {
			entityManager.getTransaction().begin();

			Customer customer = findByID(t.getId());

			if (customer != null) {
				entityManager.merge(t);
			} else {
				entityManager.persist(t);
			}
	

		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();

			return false;
		}finally {
			entityManager.getTransaction().commit();
		}

		return true;
	}

	@Override
	public boolean delete(Customer t) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();

			entityManager.remove(t);



		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();

			return false;
		}finally {
			entityManager.getTransaction().commit();
		}

		return true;
	}

	@Override
	public Customer findByID(Long ID) {
		// TODO Auto-generated method stub
		Customer customer = entityManager.find(Customer.class, ID);
		if (customer != null)
			return customer;
		return null;
	}

}
