package com.coffee.service;

import java.util.List;

import com.coffee.entity.Discount;
import com.coffee.entity.Product;

/**
 * @author Nguyen Phuc Thinh
 */
public interface DiscountService {
	List<Discount> getAll();

	boolean save(Discount discount);

	boolean delete(Discount discount);

	boolean update(Discount discount, List<Product> products);

	Discount findByID(String discountId);

}
