package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.coffee.entity.Account;
import com.coffee.entity.Area;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.Category;
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;
import com.coffee.entity.Discount;
import com.coffee.entity.Employee;
import com.coffee.entity.Position;
import com.coffee.entity.Product;

public class HibernateUtil {

	private static HibernateUtil instance = null;
	
	private SessionFactory sessionFactory;

	private HibernateUtil() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.configure()
				.build();

		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(Account.class)
				.addAnnotatedClass(Area.class)
				.addAnnotatedClass(Bill.class)
				.addAnnotatedClass(BillDetail.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(CoffeeTable.class)
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Discount.class)
				.addAnnotatedClass(Employee.class)
				.addAnnotatedClass(Position.class)
				.addAnnotatedClass(Product.class)
				.getMetadataBuilder()
				.build();

		sessionFactory = metadata
				.getSessionFactoryBuilder()
				.build();

	}

	public static HibernateUtil getInstance() {
		if(instance == null)
			instance = new HibernateUtil();
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}	
