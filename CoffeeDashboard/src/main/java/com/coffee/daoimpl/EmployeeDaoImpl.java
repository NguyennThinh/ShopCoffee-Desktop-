package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.EmployeeDao;
import com.coffee.entity.Account;
import com.coffee.entity.Employee;

import jakarta.persistence.EntityManager;

/**
 * @author Nguyen Phuc Thinh
 */
public class EmployeeDaoImpl implements EmployeeDao {

	private EntityManager entityManager;

	/**
	 * 
	 */
	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@Override
	public boolean save(Employee employee) {
		// TODO Auto-generated method stub
		try {
			entityManager.getTransaction().begin();
			Employee emp = entityManager.find(Employee.class, employee.getId());
			if (emp != null) {
				entityManager.merge(employee);
			} else {

				entityManager.persist(employee);
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
	public List<Employee> getAll() {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		List<Employee> arrEmployees = entityManager.createQuery("select e from Employee e").getResultList();

		if (arrEmployees != null)
			return arrEmployees;

		return null;
	}

	@Override
	public Employee findByID(Long employeeId) {
		// TODO Auto-generated method stub
		Employee employee = entityManager.find(Employee.class, employeeId);

		if (employee != null)
			return employee;
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		Employee employee = findByID(id);

		if (employee != null)
			try {
				entityManager.getTransaction().begin();
				entityManager.remove(employee);

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
	public boolean changePass(String pass, Long id) {
		// TODO Auto-generated method stub

		Account account = entityManager.find(Account.class, id);

		if (account != null)
			try {
				entityManager.getTransaction().begin();
				account.setPassword(pass);
				entityManager.merge(account);
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
	public Employee Login(Long id) {
		// TODO Auto-generated method stub
		Employee employee = entityManager.find(Employee.class, id);
		if(employee!= null)
			return employee;
		
		return null;
	}

}
