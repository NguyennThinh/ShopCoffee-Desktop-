package com.coffee.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Nguyen Phuc Thinh
 */
public class JPAConfig {
	public static JPAConfig instance;
	private EntityManagerFactory entityManagerFactory;
	
	
	/**
	 * 
	 */
	public JPAConfig() {
		// TODO Auto-generated constructor stub
		 entityManagerFactory = Persistence.createEntityManagerFactory("CoffeeManager");
	}
	public static synchronized JPAConfig getInstance() {
		if(instance == null)
			instance = new JPAConfig();
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
