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

}
