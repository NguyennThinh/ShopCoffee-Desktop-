package com.coffee.daoimpl;

import java.util.HashMap;
import java.util.Map;

import com.coffee.dao.EmployeeDao;
import com.coffee.entity.Account;
import com.coffee.entity.Employee;

import jakarta.persistence.EntityManager;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class EmployeeDaoImpl implements EmployeeDao{
	private EntityManager entityManager;
	/**
	 * 
	 */
	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
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
