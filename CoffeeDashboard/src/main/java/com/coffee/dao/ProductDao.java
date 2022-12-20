package com.coffee.dao;

import java.util.List;

import com.coffee.entity.Product;

/**
 * @author Nguyen Phuc Thinh
 */
public interface ProductDao {

	List<Product> getAll();

	boolean save(Product product);

	boolean delete(Product product);

	Product findByID(String productId);
	 
	 

}
