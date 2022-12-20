package com.coffee.dao;

import java.util.List;

import com.coffee.entity.CoffeeTable;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CoffeeTableDao {

	boolean openTable(CoffeeTable table);
	
	boolean closeTable(CoffeeTable table);
	
	List<CoffeeTable> getTableActive();

}
