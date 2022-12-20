package com.coffee.service;

import java.util.List;

import com.coffee.entity.Position;

/**
 * @author Nguyen Phuc Thinh
 */
public interface PositionService {

	List<Position> getAll();

	boolean save(Position position);

	boolean delete(Position position);

	Position findByID(String position_id);

}
