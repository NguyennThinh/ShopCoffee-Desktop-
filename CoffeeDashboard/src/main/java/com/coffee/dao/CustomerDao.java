package com.coffee.dao;

import java.util.List;

import com.coffee.entity.Customer;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CustomerDao {

	List<Customer> getAll();

	boolean save(Customer customer);

	boolean delete(Customer customer);

	Customer findByID(Long ID);

}
