package com.coffee.service;

import java.util.List;

import com.coffee.entity.Customer;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CustomerService {

	List<Customer> getAll();

	boolean save(Customer customer);

	boolean delete(Customer customer);

	Customer findByID(Long ID);

}
