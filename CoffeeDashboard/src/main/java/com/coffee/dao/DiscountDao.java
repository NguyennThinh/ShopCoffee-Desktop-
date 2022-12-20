package com.coffee.dao;

import java.util.List;

import com.coffee.entity.Discount;
import com.coffee.entity.Product;

/**
 * @author Nguyen Phuc Thinh
 */
public interface DiscountDao {

	List<Discount> getAll();

	boolean save(Discount discount);

	boolean delete(Discount discount);

	boolean update(Discount t, List<Product> products);

	Discount findByID(String discountId);

}
