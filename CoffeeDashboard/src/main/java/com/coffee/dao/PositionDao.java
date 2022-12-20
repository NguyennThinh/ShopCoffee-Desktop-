package com.coffee.dao;

import java.util.List;

import com.coffee.entity.Position;

/**
 * @author Nguyen Phuc Thinh
 */
public interface PositionDao {

	List<Position> getAll();

	boolean save(Position position);

	boolean delete(Position position);

	Position findByID(String position_id);

}
