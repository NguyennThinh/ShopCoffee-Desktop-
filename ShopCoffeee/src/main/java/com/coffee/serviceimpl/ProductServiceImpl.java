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

}
