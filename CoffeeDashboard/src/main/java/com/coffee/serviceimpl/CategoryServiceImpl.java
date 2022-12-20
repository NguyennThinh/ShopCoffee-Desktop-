package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.CategoryDao;
import com.coffee.daoimpl.CategoryDaoImpl;
import com.coffee.entity.Category;
import com.coffee.service.CategoryService;

/**
 * @author Nguyen Phuc Thinh
 */
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao dao;

	/**
	 * 
	 */
	public CategoryServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new CategoryDaoImpl();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public boolean save(Category t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Category t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

	@Override
	public Category findByID(String categoryId) {
		// TODO Auto-generated method stub
		return dao.findByID(categoryId);
	}
}
