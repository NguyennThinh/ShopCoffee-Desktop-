package com.coffee.service;

import java.util.List;
import java.util.Map;

import com.coffee.entity.Bill;
import com.coffee.entity.Customer;


/**
 * @author Nguyen Phuc Thinh
 */
public interface BillService {

	List<Bill> getAll() ;

	boolean save(Bill bill) ;

	boolean delete(Bill bill) ;

	Bill findByID(String bill_id) ;
	 
	Map<Customer, Double> topCustomer();
	
	Map<Integer, Double> statisicalByYear(int year);
	
	Map<Integer, Double> statisicalByMonth(int year ,int month, int day);
}
