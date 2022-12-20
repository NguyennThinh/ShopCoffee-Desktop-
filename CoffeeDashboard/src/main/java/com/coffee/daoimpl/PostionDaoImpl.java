package com.coffee.daoimpl;

import java.util.List;

import com.coffee.config.JPAConfig;
import com.coffee.dao.PositionDao;
import com.coffee.entity.Position;

import jakarta.persistence.EntityManager;

/**
 * @author Nguyen Phuc Thinh
 */
public class PostionDaoImpl implements PositionDao {

	private EntityManager entityManager;

	/**
	 * 
	 */
	public PostionDaoImpl() {
		// TODO Auto-generated constructor stub
		entityManager = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getAll() {
		// TODO Auto-generated method stub

		List<Position> arrPositions = entityManager.createQuery("select p from Position p").getResultList();

		if (arrPositions != null)
			return arrPositions;
		return null;
	}

	@Override
	public boolean save(Position t) {
		// TODO Auto-generated method stub
		try {
			Position position = findByID(t.getPosition_id());
			entityManager.getTransaction().begin();
			if (position == null) {
				entityManager.persist(t);
			} else {
				entityManager.merge(t);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public boolean delete(Position t) {
		// TODO Auto-generated method stub
		try {

			entityManager.getTransaction().begin();
			entityManager.remove(entityManager.contains(t) ? t : entityManager.merge(t));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entityManager.getTransaction().rollback();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}
		return true;
	}

	@Override
	public Position findByID(String position_id) {
		// TODO Auto-generated method stub
		Position position = entityManager.find(Position.class, position_id);
		if (position != null)
			return position;
		return null;
	}
}
