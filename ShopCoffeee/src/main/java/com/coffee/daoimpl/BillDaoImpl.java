package com.coffee.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.coffee.dao.BillDao;
import com.coffee.entity.Bill;
import com.coffee.entity.BillDetail;
import com.coffee.entity.CoffeeTable;
import com.coffee.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import util.HibernateUtil;
import util.JpaUtil;

/**
 * @author Nguyen Phuc Thinh
 */
public class BillDaoImpl implements BillDao {
	private EntityManager entityManager;
	/**
	 * 
	 */
	public BillDaoImpl() {
		// TODO Auto-generated constructor stub

		entityManager = JpaUtil.getInstance().getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Bill findBillByTable(CoffeeTable table) {
		// TODO Auto-generated method stub

	
		entityManager.getTransaction().begin();
		String jpql = "select b from Bill b where b.table.id=:table_id and b.createDate=:open_time";
		Query query = entityManager.createQuery(jpql, Bill.class);
		query.setParameter("table_id", table.getId());
		query.setParameter("open_time", table.getOpenTime());

		Bill bill = (Bill) query.getResultStream().findFirst().orElse(null);

		entityManager.getTransaction().commit();
		;
		return bill;
	}

	@Override
	public boolean creatBill(Bill bill) {
		// TODO Auto-generated method stub


		try {
			entityManager.getTransaction().begin();
			entityManager.persist(bill);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			entityManager.getTransaction().rollback();

			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean updateBillDetail(BillDetail billDetail) {
		// TODO Auto-generated method stub



		try {
			String jpql = "update BillDetail b set b.amount =:amount, b.total =:total where b.detail_id = :detail_id ";

			entityManager.getTransaction().begin();;
			Query query = entityManager.createQuery(jpql);
			query.setParameter("amount", billDetail.getAmount());
			query.setParameter("total", billDetail.getTotal());
			query.setParameter("detail_id", billDetail.getDetail_id());

			int result = query.executeUpdate();
			if (result != 0)
				return true;

			return false;
		} catch (Exception e) {
			e.printStackTrace();

			entityManager.getTransaction().rollback();
			return false;
			// TODO: handle exception
		} finally {
			entityManager.getTransaction().commit();
		}

	}

	@Override
	public boolean addDetailToBill(Bill bill) {
		// TODO Auto-generated method stub



		try {
			entityManager.getTransaction().begin();
			entityManager.merge(bill);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean joinBill(Bill bill, List<Bill> arrBills) {


		try {
			entityManager.getTransaction().begin();

			arrBills.forEach(b -> {
				b.getBillDetails().forEach(bd -> entityManager.remove(bd));
				entityManager.remove(b);
			});

			bill.getBillDetails().forEach(b -> {
				System.out.println(b);
				String sql = "select b from BillDetail b  where b.product='"+b.getProduct().getProduct_id()+"' and b.bill='"+b.getBill().getBill_id()+"'";
				Query query = entityManager.createQuery(sql);
			//	BillDetail detail = entityManager.find(BillDetail.class, b.getDetail_id());
				BillDetail detail = (BillDetail) query.getResultStream().findFirst().orElse(null);
				if (detail != null) {
					detail.setAmount(b.getAmount());
					detail.setTotal(b.getTotal());
					entityManager.merge(detail);
				} else {
					b.setBill(bill);
					bill.getBillDetails().add(b);
					entityManager.merge(bill);
				}
			});


		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}

		return true;

	}

	@Override
	public boolean removeBillDetail(BillDetail billDetail) {
		// TODO Auto-generated method stub
	
		try {

			entityManager.getTransaction().begin();

			entityManager.remove(billDetail);

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
	public boolean payBill(Bill bill, Customer customer) {
		// TODO Auto-generated method stub

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(bill);
			
			if(customer != null) {
				entityManager.merge(customer);
			}

			CoffeeTable coffeeTable = entityManager.find(CoffeeTable.class, bill.getTable().getId());
			if(coffeeTable !=null) {
				coffeeTable.setStatus(false);
				entityManager.merge(coffeeTable);
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
	public boolean addDetailToBillSplit(List<BillDetail> billDetails) {
		// TODO Auto-generated method stub

		try {

			entityManager.getTransaction().begin();

			billDetails.forEach(b -> {
				entityManager.merge(b);
			});

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
	public boolean deleteBill(Bill bill) {
		// TODO Auto-generated method stub


		try {
			entityManager.getTransaction().begin();

			CoffeeTable coffeeTable = entityManager.find(CoffeeTable.class, bill.getTable().getId());

			if (coffeeTable != null) {
				coffeeTable.setStatus(false);
				entityManager.merge(coffeeTable);
			}

			entityManager.merge(bill);

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			entityManager.getTransaction().commit();
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> getBillForDay() {
		// TODO Auto-generated method stub
		String sql = "select b from Bill b \r\n"
				+ "where cast(b.createDate as Date) = cast(getdate() as Date)";


		try {
			entityManager.getTransaction().begin();

			List<Bill> arrBills =	entityManager.createQuery(sql).getResultList();

			return arrBills;

		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			entityManager.getTransaction().commit();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> getBillByBartend() {
		// TODO Auto-generated method stub
		String sql = "select b from Bill b where b.billStatus =:billStatus";
		
		Query query = entityManager.createQuery(sql).setParameter("billStatus", "Chờ pha chế");
		
		List<Bill> arrBills = query.getResultList();
		
		if(arrBills != null)
			return arrBills;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> getBillByShift(Date date) {
		// TODO Auto-generated method stub
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String sql = "SELECT b FROM Bill b WHERE b.payDate >:payDate";

		List<Bill> arrBills = entityManager.createQuery(sql).setParameter("payDate", date).getResultList();

		if (arrBills != null)
			return arrBills;

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Customer, Double> topCustomerByShift(Date date) {
		// TODO Auto-generated method stub
		Map<Customer, Double> map = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String sql = "SELECT b.customer_id, sum(b.total_price) AS Tong\r\n"
				+ "								FROM Bills b INNER JOIN Customers u\r\n"
				+ "								ON b.customer_id = u.customer_id\r\n"
				+ "								WHERE b.bill_status = N'Đã hoàn thành'\r\n"
				+ "									  and b.pay_date >:payDate \r\n"
				+ "								GROUP BY b.customer_id, u.customer_name,u.email, u.phone\r\n"
				+ "								ORDER BY Tong DESC";

		entityManager.getTransaction().begin();

		List<Object[]> list = entityManager.createNativeQuery(sql).setParameter("payDate", date).getResultList();

		for (Object[] obj : list) {
			Long cusID = (Long) obj[0];
			Customer dept = entityManager.find(Customer.class, cusID);
			double num = (double) obj[1];

			map.put(dept, num);
		}

		return map;
	}

}
