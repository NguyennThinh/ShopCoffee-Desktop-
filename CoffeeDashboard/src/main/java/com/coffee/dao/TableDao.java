package com.coffee.dao;

import java.util.List;
import com.coffee.entity.Area;
import com.coffee.entity.CoffeeTable;

/**
 * @author Nguyen Phuc Thinh
 */
public interface TableDao {
	
	List<Area> listArea();

	List<CoffeeTable> listTable();
	
	boolean addTable(Area area);
	
	boolean addArea(Area area);
	
	boolean updateTable(CoffeeTable coffeeTable);
	
	boolean updateArea(Area area);
	
boolean deleteTable(CoffeeTable coffeeTable);
	
	boolean deleteArea(Area area);
}
