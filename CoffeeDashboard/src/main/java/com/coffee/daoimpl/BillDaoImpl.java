package com.coffee.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coffee.config.JPAConfig;
import com.coffee.dao.BillDao;
import com.coffee.entity.Bill;
import com.coffee.entity.Customer;

import jakarta.persistence.EntityManager;

/**
 * @author Nguyen Phuc Thinh
 */
public class BillDaoImpl implements BillDao {

	private EntityManager em;

	/**
	 * 
	 */
	public BillDaoImpl() {
		// TODO Auto-generated constructor stub
		em = JPAConfig.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> getAll() {
		// TODO Auto-generated method stub

		return em.createQuery("select b from Bill b").getResultList();
	}

	@Override
	public boolean save(Bill t) {
		// TODO Auto-generated method stub

		try {
			em.getTransaction().begin();
			Bill bill = findByID(t.getBill_id());

			if (bill != null) {
				em.merge(t);
			} else {
				em.persist(t);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		} finally {
			em.getTransaction().commit();
		}

		return true;
	}

	@Override
	public boolean delete(Bill bill) {
		// TODO Auto-generated method stub
		try {

			em.getTransaction().begin();
			em.remove(em.contains(bill) ? bill : em.merge(bill));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			em.getTransaction().rollback();
			return false;
		} finally {
			em.getTransaction().commit();
		}
		return true;
	}

	@Override
	public Bill findByID(String bill_id) {
		// TODO Auto-generated method stub
		Bill bill = em.find(Bill.class, bill_id);

		if (bill != null)
			return bill;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Customer, Double> topCustomer() {
		// TODO Auto-generated method stub
		Map<Customer, Double> map = new HashMap<>();

		String sql = "SELECT b.customer_id, sum(b.total_price) AS Tong\r\n"
				+ "						FROM Bills b INNER JOIN Customers u\r\n"
				+ "						ON b.customer_id = u.customer_id\r\n"
				+ "						WHERE b.bill_status = N'Đã hoàn thành'\r\n"
				+ "						GROUP BY b.customer_id, u.customer_name,u.email, u.phone\r\n"
				+ "						ORDER BY Tong DESC";

		em.getTransaction().begin();

		List<Object[]> list = em.createNativeQuery(sql).getResultList();

		for (Object[] obj : list) {
			Long cusID = (Long) obj[0];
			Customer dept = em.find(Customer.class, cusID);
			double num = (double) obj[1];

			map.put(dept, num);
		}

		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Double> statisicalByYear(int year) {
		// TODO Auto-generated method stub

		Map<Integer, Double> map = new HashMap<>();
		String sql = "\r\n" + "SELECT \r\n" + "    (MONTH(pay_date)) AS month, SUM(total_price) AS \"Tổng tiền\"\r\n"
				+ "FROM\r\n" + "    Bills\r\n" + "\r\n" + "WHERE  bill_status =N'Đã hoàn thành' and pay_date > '" + year + "' and pay_date <'" + (year + 1)
				+ "'\r\n" + "GROUP BY MONTH(pay_date)\r\n" + "ORDER BY month ASC";

		em.getTransaction().begin();

		List<Object[]> list = em.createNativeQuery(sql).getResultList();

		for (Object[] obj : list) {
			int month = (int) obj[0];

			double total = (double) obj[1];

			map.put(month, total);
		}

		if (list != null)
			return map;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Double> statisicalByMonth(int year, int month, int day) {
		// TODO Auto-generated method stub

		Map<Integer, Double> map = new HashMap<>();

		String sql = "SELECT \r\n" + "    (DAY(pay_date)) AS day, SUM(total_price) AS \"Tổng tiền\"\r\n" + "FROM\r\n"
				+ "    Bills\r\n" + "\r\n" + "WHERE pay_date BETWEEN  '" + year + "-" + month + "-01 00:00:00' and '"
				+ year + "-" + month + "-" + day + " 23:59:59' and bill_status =N'Đã hoàn thành' \r\n" + "GROUP BY DAY(pay_date)\r\n"
				+ "ORDER BY day ASC";
		em.getTransaction().begin();

		List<Object[]> list = em.createNativeQuery(sql).getResultList();

		for (Object[] obj : list) {
			int date = (int) obj[0];

			double total = (double) obj[1];

			map.put(date, total);
		}
		if (list != null)
			return map;

		return null;
	}

}
