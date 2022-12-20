package com.coffee.dao;

import java.util.Map;

import com.coffee.entity.Account;
import com.coffee.entity.Employee;

/**
 * @author Nguyen Phuc Thinh
 */
public interface EmployeeDao {
	Employee Login(Long id);
}
