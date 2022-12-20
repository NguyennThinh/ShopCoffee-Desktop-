package com.coffee.service;

import java.util.List;

import com.coffee.entity.Employee;

/**
 * @author Nguyen Phuc Thinh
 */
public interface EmployeeService {

	List<Employee> getAll();

	boolean save(Employee employee);

	Employee findByID(Long employeeId);

	boolean delete(Long id);

	boolean changePass(String pass, Long id);
	
	Employee Login(Long id);
}
