package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.DiscountDao;
import com.coffee.daoimpl.DiscountDaoImpl;
import com.coffee.entity.Discount;
import com.coffee.service.DiscountService;

/**
 * @author Nguyen Phuc Thinh
 */
public class DiscountServiceImpl implements DiscountService{
private DiscountDao dao;

/**
 * 
 */
public DiscountServiceImpl() {
	// TODO Auto-generated constructor stub
	dao = new DiscountDaoImpl();
}
	
	@Override
	public List<Discount> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
