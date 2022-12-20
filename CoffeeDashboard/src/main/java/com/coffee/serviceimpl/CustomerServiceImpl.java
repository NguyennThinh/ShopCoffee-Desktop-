package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.CustomerDao;
import com.coffee.daoimpl.CustomerDaoImpl;
import com.coffee.entity.Customer;
import com.coffee.service.CustomerService;

/**
 * @author Nguyen Phuc Thinh
 */
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao;

	/**
	 * 
	 */
	public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new CustomerDaoImpl();
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public boolean save(Customer t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Customer t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

	@Override
	public Customer findByID(Long ID) {
		// TODO Auto-generated method stub
		return dao.findByID(ID);
	}

}
