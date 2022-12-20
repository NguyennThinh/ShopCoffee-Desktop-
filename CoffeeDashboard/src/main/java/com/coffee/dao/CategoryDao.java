package com.coffee.dao;

import java.util.List;

import com.coffee.entity.Category;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CategoryDao {

	List<Category> getAll();

	boolean save(Category category);

	boolean delete(Category category);

	Category findByID(String categoryId);

}
