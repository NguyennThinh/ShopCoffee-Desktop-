package com.coffee.serviceimpl;

import java.util.List;
import java.util.Map;

import com.coffee.dao.BillDao;
import com.coffee.daoimpl.BillDaoImpl;
import com.coffee.entity.Bill;
import com.coffee.entity.Customer;
import com.coffee.service.BillService;

/**
 * @author Nguyen Phuc Thinh
 */
public class BillServiceImpl implements BillService {

	private BillDao dao;

	/**
	 * 
	 */
	public BillServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new BillDaoImpl();
	}

	@Override
	public List<Bill> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public boolean save(Bill t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Bill bill) {
		// TODO Auto-generated method stub
		return dao.delete(bill);
	}

	@Override
	public Bill findByID(String bill_id) {
		// TODO Auto-generated method stub
		return dao.findByID(bill_id);
	}

	@Override
	public Map<Customer, Double> topCustomer() {
		// TODO Auto-generated method stub
		return dao.topCustomer();
	}

	@Override
	public Map<Integer, Double> statisicalByYear(int year) {
		// TODO Auto-generated method stub
		return dao.statisicalByYear(year);
	}

	@Override
	public Map<Integer, Double> statisicalByMonth(int year, int month, int day) {
		// TODO Auto-generated method stub
		return dao.statisicalByMonth(year, month,day);
	}
}
