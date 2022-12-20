package com.coffee.serviceimpl;

import java.util.Map;

import com.coffee.dao.EmployeeDao;
import com.coffee.daoimpl.EmployeeDaoImpl;
import com.coffee.entity.Account;
import com.coffee.entity.Employee;
import com.coffee.service.EmployeeService;

/**
 * @author Nguyen Phuc Thinh
 */
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDao dao;
	
	
	/**
	 * 
	 */
	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
	dao = new EmployeeDaoImpl();
	}
	

	@Override
	public Employee Login(Long id) {
		// TODO Auto-generated method stub
		return dao.Login(id);
	}

}
