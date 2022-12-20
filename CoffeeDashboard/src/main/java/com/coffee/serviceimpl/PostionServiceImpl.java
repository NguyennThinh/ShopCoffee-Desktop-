package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.PositionDao;
import com.coffee.daoimpl.PostionDaoImpl;
import com.coffee.entity.Position;
import com.coffee.service.PositionService;

/**
 * @author Nguyen Phuc Thinh
 */
public class PostionServiceImpl implements PositionService {

	private PositionDao dao;

	/**
	 * 
	 */
	public PostionServiceImpl() {
		// TODO Auto-generated constructor stub
		dao = new PostionDaoImpl();
	}

	@Override
	public List<Position> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public boolean save(Position t) {
		// TODO Auto-generated method stub
		return dao.save(t);
	}

	@Override
	public boolean delete(Position t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

	@Override
	public Position findByID(String position_id) {
		// TODO Auto-generated method stub
		return dao.findByID(position_id);
	}

}
