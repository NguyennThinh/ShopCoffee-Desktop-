package com.coffee.serviceimpl;

import java.util.List;

import com.coffee.dao.AreaDao;
import com.coffee.daoimpl.AreaDaoImpl;
import com.coffee.entity.Area;
import com.coffee.service.AreaService;

/**
 * @author Nguyen Phuc Thinh
 */
public class AreaServiceImpl implements AreaService{

	private AreaDao dao;
	
	/**
	 * 
	 */
	public AreaServiceImpl() {
		// TODO Auto-generated constructor stub
	dao = new AreaDaoImpl();
	}
	
	@Override
	public List<Area> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

}
