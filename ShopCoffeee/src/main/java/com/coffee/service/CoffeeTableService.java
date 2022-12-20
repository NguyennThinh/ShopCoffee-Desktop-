package com.coffee.service;

import java.util.List;

import com.coffee.entity.CoffeeTable;

/**
 * @author Nguyen Phuc Thinh
 */
public interface CoffeeTableService {

	boolean openTable(CoffeeTable table);
	
	boolean closeTable(CoffeeTable table);
	
	List<CoffeeTable> getTableActive();

}
