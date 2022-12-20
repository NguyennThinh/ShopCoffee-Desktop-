package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.CoffeeTableDao;
import com.coffee.daoimpl.CoffeeTableDaoImpl;
import com.coffee.entity.CoffeeTable;
import com.coffee.service.CoffeeTableService;

/**
 * @author Nguyen Phuc Thinh
 */
public class CoffeeTableServiceImpl implements CoffeeTableService{

	private	CoffeeTableDao dao;
	
	/**
	 * 
	 */
	public CoffeeTableServiceImpl() {
		// TODO Auto-generated constructor stub
	dao = new CoffeeTableDaoImpl();
	}
	
	@Override
	public boolean openTable(CoffeeTable table) {
		// TODO Auto-generated method stub
		return dao.openTable(table);
	}

	@Override
	public List<CoffeeTable> getTableActive() {
		// TODO Auto-generated method stub
		return dao.getTableActive();
	}

	@Override
	public boolean closeTable(CoffeeTable table) {
		// TODO Auto-generated method stub
		return dao.closeTable(table);
	}

}
