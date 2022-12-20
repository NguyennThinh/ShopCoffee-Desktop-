package com.coffee.service;

import java.util.List;

import com.coffee.entity.Category;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CategoryService {

	List<Category> getAll();

	boolean save(Category category);

	boolean delete(Category category);

	Category findByID(String categoryId);

}
