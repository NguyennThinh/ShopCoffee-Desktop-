package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.TableDao;
import com.coffee.daoimpl.TableDaoImpl;
import com.coffee.entity.Area;
import com.coffee.entity.CoffeeTable;
import com.coffee.service.TableService;

/**
 * @author Nguyen Phuc Thinh
 */
public class TableServiceImpl implements TableService{

	private TableDao dao;
	
	/**
	 * 
	 */
	public TableServiceImpl() {
		// TODO Auto-generated constructor stub
	dao = new TableDaoImpl();
	}
	
	@Override
	public List<Area> listArea() {
		// TODO Auto-generated method stub
		return dao.listArea();
	}

	@Override
	public List<CoffeeTable> listTable() {
		// TODO Auto-generated method stub
		return dao.listTable();
	}

	@Override
	public boolean addTable(Area area) {
		// TODO Auto-generated method stub
		return dao.addTable(area);
	}

	@Override
	public boolean addArea(Area area) {
		// TODO Auto-generated method stub
		return dao.addArea(area);
	}

	@Override
	public boolean updateTable(CoffeeTable coffeeTable) {
		// TODO Auto-generated method stub
		return dao.updateTable(coffeeTable);
	}

	@Override
	public boolean updateArea(Area area) {
		// TODO Auto-generated method stub
		return dao.updateArea(area);
	}

	@Override
	public boolean deleteTable(CoffeeTable coffeeTable) {
		// TODO Auto-generated method stub
		return dao.deleteTable(coffeeTable);
	}

	@Override
	public boolean deleteArea(Area area) {
		// TODO Auto-generated method stub
		return dao.deleteArea(area);
	}

}
