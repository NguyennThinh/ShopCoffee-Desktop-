package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.ProductDao;
import com.coffee.daoimpl.ProductDaoImpl;
import com.coffee.entity.Product;
import com.coffee.service.ProductService;

/**
 * @author Nguyen Phuc Thinh
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao dao;

	/**
	 * 
	 */
	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new ProductDaoImpl();
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public boolean save(Product t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Product t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

	@Override
	public Product findByID(String productId) {
		// TODO Auto-generated method stub
		return dao.findByID(productId);
	}
}
