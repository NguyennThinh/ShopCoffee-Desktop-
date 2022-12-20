package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.DiscountDao;
import com.coffee.daoimpl.DiscountDaoImpl;
import com.coffee.entity.Discount;
import com.coffee.entity.Product;
import com.coffee.service.DiscountService;

/**
 * @author Nguyen Phuc Thinh
 */
public class DiscountServiceImpl implements DiscountService {

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

	@Override
	public boolean save(Discount t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Discount t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

	@Override
	public boolean update(Discount t, List<Product> products) {
		// TODO Auto-generated method stub
		return dao.update(t, products);
	}

	@Override
	public Discount findByID(String discountId) {
		// TODO Auto-generated method stub
		return dao.findByID(discountId);
	}

}
