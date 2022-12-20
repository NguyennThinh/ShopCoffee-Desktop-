package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.EmployeeDao;
import com.coffee.daoimpl.EmployeeDaoImpl;
import com.coffee.entity.Employee;
import com.coffee.service.EmployeeService;

/**
 * @author Nguyen Phuc Thinh
 */
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao dao;

	/**
	 * 
	 */
	public EmployeeServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new EmployeeDaoImpl();
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public Employee findByID(Long employeeId) {
		// TODO Auto-generated method stub
		return dao.findByID(employeeId);
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public boolean save(Employee employee) {
		// TODO Auto-generated method stub
		return dao.save(employee);
	}

	@Override
	public boolean changePass(String pass, Long id) {
		// TODO Auto-generated method stub
		return dao.changePass(pass, id);
	}

	@Override
	public Employee Login(Long id) {
		// TODO Auto-generated method stub
		return dao.Login(id);
	}

}
