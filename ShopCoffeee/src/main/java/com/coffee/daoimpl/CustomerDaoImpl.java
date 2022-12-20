package com.coffee.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.coffee.dao.CustomerDao;
import com.coffee.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.HibernateUtil;
import util.JpaUtil;

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

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		String jpql="select c from Customer c";
		
		
		
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public boolean save(Customer customer) {
		// TODO Auto-generated method stub
		
		try {
			entityManager.getTransaction().begin();
			
			Customer c = entityManager.find(Customer.class, customer.getId());
			
			if(c!= null) {
				entityManager.merge(customer);
			}else {
				entityManager.persist(customer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}finally {
			entityManager.getTransaction().commit();
		}
		
		return true;
	}

	@Override
	public boolean delete(Customer customer) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();
			
			entityManager.remove(customer);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		}finally {
			entityManager.getTransaction().commit();
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer findByPhone(Long Phone) {
		// TODO Auto-generated method stub



		try {

			entityManager.getTransaction().begin();;
			Query query = entityManager.createNamedQuery("find Customer by phone", Customer.class);
			query.setParameter("phone", Phone);
			Customer customer = (Customer) query.getResultStream().findFirst().orElse(null);
			return customer;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return null;
		} finally {
			entityManager.getTransaction().commit();
		}

	}

}
