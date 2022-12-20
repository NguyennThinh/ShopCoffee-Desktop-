package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * @author Nguyen Phuc Thinh
 */
public class JpaUtil {
	public static JpaUtil instance;
	private EntityManagerFactory entityManagerFactory;
	
	
	/**
	 * 
	 */
	public JpaUtil() {
		// TODO Auto-generated constructor stub
		 entityManagerFactory = Persistence.createEntityManagerFactory("HanaCoffeeManager");
	}
	public static synchronized JpaUtil getInstance() {
		if(instance == null)
			instance = new JpaUtil();
		return instance;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
